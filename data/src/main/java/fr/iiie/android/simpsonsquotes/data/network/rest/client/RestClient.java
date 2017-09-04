package fr.iiie.android.simpsonsquotes.data.network.rest.client;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import fr.iiie.android.simpsonsquotes.data.R;
import fr.iiie.android.simpsonsquotes.data.app.App;
import fr.iiie.android.simpsonsquotes.data.network.rest.service.ApiService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient
{
    private final ApiService apiService;

    public RestClient(Context context)
    {
        final Executor executor = Executors.newFixedThreadPool(context.getResources().getInteger(R.integer.db_thread_pool_size));

        final OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder()
                .addInterceptor(new SessionRequestInterceptor(context));
        if (App.isDebugEnabled())
        {
            httpClientBuilder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        }

        final Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getResources().getString(R.string.ws_endpoint_url))
                .callbackExecutor(executor)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClientBuilder.build())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public ApiService getApiService()
    {
        return apiService;
    }
}
