package fr.iiie.android.simpsonsquotes.ui.fragment.details;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.iiie.android.simpsonsquotes.R;

public class DetailsFragment extends Fragment
{
    @BindView(R.id.fragment_details_idText)
    TextView idText;

    @BindView(R.id.fragment_details_episodeText)
    TextView episodeText;

    @BindView(R.id.fragment_details_timestampText)
    TextView timestampText;

    @BindView(R.id.fragment_details_largeImage)
    ImageView episodeLargeImage;

    @BindView(R.id.fragment_details_progressBar)
    ProgressBar detailsProgressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        final View rootView = inflater.inflate(R.layout.fragment_details, container, false);

        ButterKnife.bind(this, rootView);

        Bundle arguments = this.getArguments();

        final String large_img = "large.jpg";
        final String episode = arguments.getString("episode");
        final String timestamp = arguments.getString("timestamp");

        String episode_value = getString(R.string.episode_value, episode);
        String timestamp_value = getString(R.string.timestamp_value, timestamp);
        String id = getString(R.string.id_value, arguments.getString("id"));
        episodeText.setText(episode_value);
        timestampText.setText(timestamp_value);
        idText.setText(id);

        String image_url = getString(R.string.img_url, episode, timestamp, large_img);
        Glide.with(this)
            .load(image_url)
                .listener(new RequestListener<Drawable>()
                {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource)
                    {
                        detailsProgressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource)
                    {
                        detailsProgressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
            .into(episodeLargeImage);

        return rootView;
    }
}
