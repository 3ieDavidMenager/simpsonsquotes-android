package fr.iiie.android.simpsonsquotes.ui.fragment.other;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import fr.iiie.android.simpsonsquotes.R;
import fr.iiie.android.simpsonsquotes.data.app.App;
import fr.iiie.android.simpsonsquotes.business.other.OtherController;

public class OtherFragment extends Fragment
{
    private OtherController otherController;

    public OtherFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final View rootView = inflater.inflate(R.layout.fragment_other, container, false);
        ButterKnife.bind(this, rootView);
        otherController = new OtherController();

        return rootView;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        otherController.resume();
        if (!App.getAppBus().isRegistered(this))
        {
            // TODO uncomment bus registration in real project (disabled only to avoid exception in when no subscribers is present)
            //App.getAppBus().register(this);
        }
    }

    @Override
    public void onPause()
    {
        otherController.pause();
        if (App.getAppBus().isRegistered(this))
        {
            App.getAppBus().unregister(this);
        }
        super.onPause();
    }

}
