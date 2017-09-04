package fr.iiie.android.simpsonsquotes.data.network.rest.client;

import android.content.Context;

import java.io.IOException;

import fr.iiie.android.simpsonsquotes.data.R;
import fr.iiie.android.simpsonsquotes.data.app.App;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class SessionRequestInterceptor implements Interceptor
{
    final private String sAuthHeaderName;

    public SessionRequestInterceptor(final Context context)
    {
        sAuthHeaderName = context.getResources().getString(R.string.auth_header);
    }

    @Override
    public Response intercept(Chain chain) throws IOException
    {
        final Request request = chain.request().newBuilder()
                .addHeader(sAuthHeaderName, App.getAuthToken())
                .build();

        return chain.proceed(request);
    }
}
