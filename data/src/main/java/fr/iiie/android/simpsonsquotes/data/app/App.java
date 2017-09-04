package fr.iiie.android.simpsonsquotes.data.app;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Point;
import android.preference.PreferenceManager;

import com.activeandroid.app.Application;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Field;

import fr.iiie.android.simpsonsquotes.data.R;
import fr.iiie.android.simpsonsquotes.data.network.broadcast.NetworkBroadcastReceiver;
import fr.iiie.android.simpsonsquotes.data.network.rest.client.RestClient;
import fr.iiie.android.simpsonsquotes.tools.Analytics;

public class App extends Application
{
    private static Boolean bIsConnected = false;
    private static RestClient restClient;
    private static NetworkBroadcastReceiver networkBroadcastReceiver;
    private static SharedPreferences sharedPreferences;
    private static Resources resources;
    private static String sAuthHeaderName;
    private static Point screenSize;

    private static Boolean bDebugEnabled;
    private static EventBus appBus;
    private static EventBus coreBus;

    @Override
    public void onCreate()
    {
        super.onCreate();
        bDebugEnabled = getBuildConfigDebug();

        restClient = new RestClient(this);
        networkBroadcastReceiver = new NetworkBroadcastReceiver();
        resources = getResources();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        bIsConnected = NetworkBroadcastReceiver.isConnected(this);
        sAuthHeaderName = getString(R.string.auth_header);

        appBus = EventBus.builder().sendNoSubscriberEvent(false).logNoSubscriberMessages(bDebugEnabled).build();
        coreBus = EventBus.builder().sendNoSubscriberEvent(false).logNoSubscriberMessages(bDebugEnabled).build();

        Analytics.initializeTracker(this, bDebugEnabled);
    }

    public static Boolean isConnected()
    {

        return bIsConnected;
    }

    public static void setIsConnected(Boolean bConnected)
    {
        App.bIsConnected = bConnected;
    }

    public static RestClient getRestClient()
    {

        return restClient;
    }

    public static SharedPreferences getSharedPreferences()
    {

        return sharedPreferences;
    }

    public static NetworkBroadcastReceiver getNetworkBroadcastReceiver()
    {

        return networkBroadcastReceiver;
    }

    public static Boolean isDebugEnabled()
    {

        return bDebugEnabled;
    }

    public static void setDebugEnabled(Boolean bDebugEnabled)
    {
        App.bDebugEnabled = bDebugEnabled;
    }

    public static EventBus getAppBus()
    {

        return appBus;
    }

    public static EventBus getCoreBus()
    {

        return coreBus;
    }

    public static Resources getAppResources()
    {
        return resources;
    }

    public static String getAuthToken()
    {

        return App.getSharedPreferences().getString(sAuthHeaderName, "");
    }

    // use this method instead of BuildConfig.DEBUG flag, which works only in top-level module (app)
    private Boolean getBuildConfigDebug()
    {
        try
        {
            Class<?> clazz = Class.forName(this.getPackageName() + ".BuildConfig");
            Field field = clazz.getField("DEBUG");

            return (Boolean) field.get(null);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (NoSuchFieldException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }

        return false;
    }

    public static Point getScreenSize()
    {
        return screenSize;
    }

    public static void setScreenSize(Point screenSize)
    {
        App.screenSize = screenSize;
    }

    public static void setAuthToken(String sUserToken)
    {
        App.getSharedPreferences().edit().putString(sAuthHeaderName, sUserToken).apply();
    }
}
