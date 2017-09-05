package fr.iiie.android.simpsonsquotes.ui.fragment.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        final View rootView = inflater.inflate(R.layout.fragment_details, container, false);
        ButterKnife.bind(this, rootView);
        idText.setText(this.getArguments().getString("id"));
        episodeText.setText(this.getArguments().getString("episode"));
        timestampText.setText(this.getArguments().getString("timestamp"));
        return rootView;
    }
}
