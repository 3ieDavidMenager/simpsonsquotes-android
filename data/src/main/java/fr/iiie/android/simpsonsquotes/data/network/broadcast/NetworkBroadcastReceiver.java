package fr.iiie.android.simpsonsquotes.data.network.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import fr.iiie.android.simpsonsquotes.data.app.App;

public class NetworkBroadcastReceiver extends BroadcastReceiver
{
    public NetworkBroadcastReceiver()
    {
        // Required empty constructor
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION))
        {
            App.setIsConnected(isConnected(context));
        }
    }

    static public Boolean isConnected(Context context)
    {
        final NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

        return (activeNetworkInfo != null && activeNetworkInfo.isConnected());
    }
}
