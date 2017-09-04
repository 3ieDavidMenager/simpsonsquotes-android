package fr.iiie.android.simpsonsquotes.business;

import org.greenrobot.eventbus.Subscribe;

import fr.iiie.android.simpsonsquotes.data.app.App;
import fr.iiie.android.simpsonsquotes.data.bus.DummyEvent;

public abstract class Controller
{
    public Controller()
    {
        // Required empty constructor
    }

    public void resume()
    {
        if (!App.getCoreBus().isRegistered(this))
        {
            App.getCoreBus().register(this);
        }
    }

    public void pause()
    {
        if (App.getCoreBus().isRegistered(this))
        {
            App.getCoreBus().unregister(this);
        }
    }

    @Subscribe
    public void onDummyEvent(DummyEvent event)
    {
        // prevent eventbus exception when not subscriber
    }
}
