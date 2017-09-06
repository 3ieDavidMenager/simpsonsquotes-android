package fr.iiie.android.simpsonsquotes.ui.fragment.details;

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

        // TODO instead of concatenating multiple String objects, either use StringBuilder, or insert parameters in string resources
        String episode = getString(R.string.episode) + ": " + this.getArguments().getString("episode");
        String timestamp = getString(R.string.timestamp) + ": " + this.getArguments().getString("timestamp");
        String id = getString(R.string.id) + ": " + this.getArguments().getString("id");
        episodeText.setText(episode);
        timestampText.setText(timestamp);
        idText.setText(id);

        // TODO magical string
        String image_url = "https://frinkiac.com/img/" + this.getArguments().getString("episode") + "/" + this.getArguments().getString("timestamp") + "/large.jpg";
        Glide.with(this).load(image_url).into(episodeLargeImage);

        // TODO progressbar with no loading or no progress ?
        detailsProgressBar.setVisibility(View.GONE);
        return rootView;
    }
}
