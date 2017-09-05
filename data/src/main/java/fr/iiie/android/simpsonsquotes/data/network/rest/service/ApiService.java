package fr.iiie.android.simpsonsquotes.data.network.rest.service;

import java.util.List;

import fr.iiie.android.simpsonsquotes.data.model.QuoteResultModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService
{
    @GET("search")
    Call<List<QuoteResultModel>> getSearchResponse(@Query("q") String query);
}
