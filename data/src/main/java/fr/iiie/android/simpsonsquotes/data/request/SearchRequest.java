package fr.iiie.android.simpsonsquotes.data.request;

import com.activeandroid.util.Log;

import java.util.List;

import fr.iiie.android.simpsonsquotes.data.app.App;
import fr.iiie.android.simpsonsquotes.data.model.QuoteResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchRequest
{

    private static final Callback<List<QuoteResult>> searchResponseCallback = new Callback<List<QuoteResult>>()
    {
        @Override
        public void onResponse(Call<List<QuoteResult>> call, Response<List<QuoteResult>> response)
        {
            if (response.code() == 200)
            {
                // TODO use TODO when adding TODOs xD
                //Handle bus
            }
        }

        @Override
        public void onFailure(Call<List<QuoteResult>> call, Throwable t)
        {

        }
    };

    public static void getSearchResponse(String query)
    {
        if (App.isConnected())
        {
            App.getRestClient().getApiService().getSearchResponse(query).enqueue(searchResponseCallback);
        }
    }

}
