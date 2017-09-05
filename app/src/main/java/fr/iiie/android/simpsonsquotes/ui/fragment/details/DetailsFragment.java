package fr.iiie.android.simpsonsquotes.ui.fragment.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
    ImageView largeImage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        final View rootView = inflater.inflate(R.layout.fragment_details, container, false);

        ButterKnife.bind(this, rootView);

        String episode = this.getArguments().getString("episode");
        String timestamp = this.getArguments().getString("timestamp");
        String id = this.getArguments().getString("id");

        episodeText.setText("Episode: " + episode);
        timestampText.setText("Timestamp" + timestamp);
        idText.setText("Id: " + id);

        String image_url = "https://frinkiac.com/img/" + episode + "/" + timestamp + "/large.jpg";
        Glide.with(this).load(image_url).into(largeImage);
        return rootView;
    }
}
