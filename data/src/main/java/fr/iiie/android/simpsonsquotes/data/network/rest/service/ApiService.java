package fr.iiie.android.simpsonsquotes.data.network.rest.service;

import java.util.List;

import fr.iiie.android.simpsonsquotes.data.model.QuoteSearchModel;
import fr.iiie.android.simpsonsquotes.data.model.RandomQuoteModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService
{
    @GET("search")
    Call<List<QuoteSearchModel>> getSearchResponse(@Query("q") String query);

    @GET("random")
    Call<RandomQuoteModel> getRandomQuote();
}
