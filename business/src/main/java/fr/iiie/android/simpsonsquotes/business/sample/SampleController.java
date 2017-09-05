package fr.iiie.android.simpsonsquotes.business.sample;

import org.greenrobot.eventbus.Subscribe;

import fr.iiie.android.simpsonsquotes.data.app.App;
import fr.iiie.android.simpsonsquotes.data.request.WsRequest;
import fr.iiie.android.simpsonsquotes.business.Controller;

public class SampleController extends Controller
{

    public SampleController()
    {
        // Required empty constructor
    }

    public void getWsData()
    {
        WsRequest.getWsData();
    }

    @Subscribe
    public void onWsDataReadyEvent(WsDataReadyEvent event)
    {
        App.getAppBus().post(event);
    }
}
