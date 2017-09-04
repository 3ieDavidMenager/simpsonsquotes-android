package fr.iiie.android.simpsonsquotes.ui.fragment.sample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.iiie.android.simpsonsquotes.R;
import fr.iiie.android.simpsonsquotes.bus.SnackEvent;
import fr.iiie.android.simpsonsquotes.bus.SwitchFragmentEvent;
import fr.iiie.android.simpsonsquotes.data.app.App;
import fr.iiie.android.simpsonsquotes.data.bus.WsDataReadyEvent;
import fr.iiie.android.simpsonsquotes.business.sample.SampleController;
import fr.iiie.android.simpsonsquotes.tools.Analytics;
import fr.iiie.android.simpsonsquotes.ui.fragment.other.OtherFragment;

public class SampleFragment extends Fragment
{
    private SampleController sampleController;

    @OnClick(R.id.fragment_sample_button_snack)
    protected void onSnackButtonClick()
    {
        App.getAppBus().post(new SnackEvent("Sample snack"));
        Analytics.sendAnalyticsEvent("SampleFragmentScreen", "Snack", "Snack", "Snack");
    }

    @OnClick(R.id.fragment_sample_button_switch)
    protected void onSwitchButtonClick()
    {
        App.getAppBus().post(new SwitchFragmentEvent(new OtherFragment(), SwitchFragmentEvent.Direction.ALPHA, true, false, false));
    }

    @OnClick(R.id.fragment_sample_button_ws)
    protected void onWsButtonClick()
    {
        sampleController.getWsData();
    }

    public SampleFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final View rootView = inflater.inflate(R.layout.fragment_sample, container, false);
        ButterKnife.bind(this, rootView);
        sampleController = new SampleController();

        return rootView;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        sampleController.resume();
        if (!App.getAppBus().isRegistered(this))
        {
            App.getAppBus().register(this);
        }
        Analytics.sendAnalyticsScreen("SampleFragmentScreen");
    }

    @Override
    public void onPause()
    {
        sampleController.pause();
        if (App.getAppBus().isRegistered(this))
        {
            App.getAppBus().unregister(this);
        }
        super.onPause();
    }

    @Subscribe
    public void onWsDataReadyEvent(WsDataReadyEvent event)
    {
        if (event.getMyCustomModelList() != null)
        {
            App.getAppBus().post(new SnackEvent("Sample WS ok, size is : " + event.getMyCustomModelList().size()));
        }
        else
        {
            App.getAppBus().post(new SnackEvent("Error or no network"));
        }
    }
}
