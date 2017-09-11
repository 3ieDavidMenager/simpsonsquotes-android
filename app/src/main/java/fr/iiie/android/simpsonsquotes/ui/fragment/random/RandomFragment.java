package fr.iiie.android.simpsonsquotes.ui.fragment.random;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.iiie.android.simpsonsquotes.R;
import fr.iiie.android.simpsonsquotes.bus.SnackEvent;
import fr.iiie.android.simpsonsquotes.business.search.SearchController;
import fr.iiie.android.simpsonsquotes.data.app.App;
import fr.iiie.android.simpsonsquotes.data.bus.RandomDataReadyEvent;
import fr.iiie.android.simpsonsquotes.data.model.QuoteSearchModel;
import fr.iiie.android.simpsonsquotes.data.model.RandomQuoteModel;
import fr.iiie.android.simpsonsquotes.data.request.SearchRequest;


public class RandomFragment extends Fragment
{

    RandomQuoteModel randomData;

    private SearchController searchController;

    @BindView(R.id.fragment_random_titleText)
    TextView episodeTitleText;

    @BindView(R.id.fragment_random_episodeText)
    TextView episodeNumberText;

    @BindView(R.id.fragment_random_largeImage)
    ImageView episodeImage;

    @BindView(R.id.fragment_random_subtitlesText)
    TextView episodeSubtitlesText;

    @BindView(R.id.fragment_random_progressBar)
    ProgressBar randomProgressBar;

    public RandomFragment()
    {
        //Mandatory constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        final View rootView = inflater.inflate(R.layout.fragment_random, container, false);
        ButterKnife.bind(this, rootView);
        if (savedInstanceState == null)
            SearchRequest.getRandomQuote();
        else
        {
            RandomQuoteModel quoteModel = Parcels.unwrap(savedInstanceState.getParcelable("randomData"));
            onRandomDataReadyEvent(new RandomDataReadyEvent(quoteModel));
        }
        searchController = new SearchController();

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putParcelable("randomData", Parcels.wrap(randomData));
    }

    @Override
    public void onResume()
    {
        super.onResume();
        searchController.resume();
        if (!App.getAppBus().isRegistered(this))
        {
            App.getAppBus().register(this);
        }
    }

    @Override
    public void onPause()
    {
        searchController.pause();
        if (App.getAppBus().isRegistered(this))
        {
            App.getAppBus().unregister(this);
        }
        super.onPause();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRandomDataReadyEvent(RandomDataReadyEvent event)
    {
        if (event.getRandomQuoteModel() != null)
        {
            final String large_image = "large.jpg";
            this.randomData = event.getRandomQuoteModel();
            RandomQuoteModel response = event.getRandomQuoteModel();
            RandomQuoteModel.Episode episode = response.getEpisode();
            episodeTitleText.setText(episode.getTitle());
            episodeNumberText.setText(getString(R.string.episode_number, episode.getSeason(), episode.getEpisodeNumber()));

            QuoteSearchModel frame = response.getFrame();

            String image_url = getString(R.string.img_url, frame.getEpisode(), Integer.toString(frame.getTimestamp()), large_image);
            Glide.with(this)
                    .load(image_url)
                    .listener(new RequestListener<Drawable>()
                    {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource)
                        {
                            randomProgressBar.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource)
                        {
                            randomProgressBar.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(episodeImage);

            String subtitlesString = "";
            List<RandomQuoteModel.Subtitles> subtitles = response.getSubtitles();

            for (RandomQuoteModel.Subtitles sub : subtitles)
            {
                subtitlesString += sub.getContent() + "\n";
            }

            episodeSubtitlesText.setText(subtitlesString);
        }
        else
        {
            App.getAppBus().post(new SnackEvent(event.getError_msg()));
        }
    }

}
