package fr.iiie.android.simpsonsquotes.ui.fragment.random;

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

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.parceler.Parcels;

import java.io.Serializable;
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

    RandomDataReadyEvent event;

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
            RandomDataReadyEvent savedEvent = Parcels.unwrap(savedInstanceState.getParcelable("event"));
            onRandomDataReadyEvent(savedEvent);
        }
        searchController = new SearchController();

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putParcelable("event", Parcels.wrap(event));
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
            this.event = event;
            RandomQuoteModel response = event.getRandomQuoteModel();
            RandomQuoteModel.Episode episode = response.getEpisode();
            episodeTitleText.setText(episode.getTitle());
            // TODO magical strings
            episodeNumberText.setText("Season " + episode.getSeason() + ", Episode " + episode.getEpisodeNumber());

            QuoteSearchModel frame = response.getFrame();

            // TODO magical string
            String image_url = "https://frinkiac.com/img/" + frame.getEpisode() + "/" + frame.getTimestamp() + "/large.jpg";
            Glide.with(this).load(image_url).into(episodeImage);

            String subtitlesString = "";
            List<RandomQuoteModel.Subtitles> subtitles = response.getSubtitles();

            for (RandomQuoteModel.Subtitles sub : subtitles)
            {
                subtitlesString += sub.getContent() + "\n";
            }

            episodeSubtitlesText.setText(subtitlesString);
            randomProgressBar.setVisibility(View.GONE);
        }
        else
        {
            App.getAppBus().post(new SnackEvent("Error or no network"));
        }
    }

}
