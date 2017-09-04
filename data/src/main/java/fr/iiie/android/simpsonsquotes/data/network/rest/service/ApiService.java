package fr.iiie.android.simpsonsquotes.data.network.rest.service;

import java.util.List;

import fr.iiie.android.simpsonsquotes.data.model.MyCustomModel;
import fr.iiie.android.simpsonsquotes.data.model.QuoteResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService
{
    @GET("search")
    Call<List<QuoteResult>> getSearchResponse(@Query("q") String query);
}
