package fr.iiie.android.simpsonsquotes.data.request;

import java.util.List;

import fr.iiie.android.simpsonsquotes.data.app.App;
import fr.iiie.android.simpsonsquotes.data.bus.RandomDataReadyEvent;
import fr.iiie.android.simpsonsquotes.data.bus.SearchDataReadyEvent;
import fr.iiie.android.simpsonsquotes.data.model.QuoteSearchModel;
import fr.iiie.android.simpsonsquotes.data.model.RandomQuoteModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchRequest
{

    private static final Callback<List<QuoteSearchModel>> searchResponseCallback = new Callback<List<QuoteSearchModel>>()
    {
        @Override
        public void onResponse(Call<List<QuoteSearchModel>> call, Response<List<QuoteSearchModel>> response)
        {
            if (response.code() == 200)
            {
                App.getCoreBus().post(new SearchDataReadyEvent(response.body()));
            }
        }

        @Override
        public void onFailure(Call<List<QuoteSearchModel>> call, Throwable t)
        {
            App.getCoreBus().post(new SearchDataReadyEvent(null));
        }
    };

    public static void getSearchResponse(String query)
    {
        if (App.isConnected())
        {
            App.getRestClient().getApiService().getSearchResponse(query).enqueue(searchResponseCallback);
        }
        else
        {
            App.getCoreBus().post(new SearchDataReadyEvent(null));
        }
    }

    private static final Callback<RandomQuoteModel> randomResponseCallback = new Callback<RandomQuoteModel>()
    {
        @Override
        public void onResponse(Call<RandomQuoteModel> call, Response<RandomQuoteModel> response)
        {
            if (response.code() == 200)
            {
                App.getCoreBus().post(new RandomDataReadyEvent(response.body()));
            }
        }

        @Override
        public void onFailure(Call<RandomQuoteModel> call, Throwable t)
        {
            App.getCoreBus().post(new RandomDataReadyEvent(null));
        }
    };

    public static void getRandomQuote()
    {
        if (App.isConnected())
        {
            App.getRestClient().getApiService().getRandomQuote().enqueue(randomResponseCallback);
        }
        else
        {
            App.getCoreBus().post(new RandomDataReadyEvent(null));
        }
    }

}
