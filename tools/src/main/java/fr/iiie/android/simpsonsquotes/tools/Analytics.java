package fr.iiie.android.simpsonsquotes.tools;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class Analytics
{
    public Analytics()
    {
        // Required empty constructor
    }

    private static Tracker analyticsTracker = null;

    public static synchronized void initializeTracker(Context context, Boolean bEnableDebug)
    {
        if (analyticsTracker == null)
        {
            final GoogleAnalytics googleAnalytics = GoogleAnalytics.getInstance(context);
            if (bEnableDebug)
            {
                googleAnalytics.setDryRun(true);
            }
            analyticsTracker = googleAnalytics.newTracker(R.xml.app_tracker);
        }
    }

    public static void sendAnalyticsScreen(String sScreenName)
    {
        if (analyticsTracker != null)
        {
            analyticsTracker.setScreenName(sScreenName);
            analyticsTracker.send(new HitBuilders.ScreenViewBuilder().build());
            analyticsTracker.setScreenName(null);
        }
        else
        {
            Log.w("GoogleAnalytics", "Enable to send message : tracker was not initialized");
        }
    }

    public static void sendAnalyticsEvent(String sScreenName, String sCategory, String sAction, String sLabel)
    {
        if (analyticsTracker != null)
        {
            analyticsTracker.setScreenName(sScreenName);
            analyticsTracker.send(new HitBuilders.EventBuilder()
                    .setCategory(sCategory)
                    .setAction(sAction)
                    .setLabel(sLabel)
                    .build());
            analyticsTracker.setScreenName(null);
        }
        else
        {
            Log.w("GoogleAnalytics", "Enable to send event : tracker was not initialized");
        }
    }
}
