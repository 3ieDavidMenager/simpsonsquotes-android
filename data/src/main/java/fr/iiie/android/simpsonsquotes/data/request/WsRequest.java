package fr.iiie.android.simpsonsquotes.data.request;

import java.util.List;

import fr.iiie.android.simpsonsquotes.data.app.App;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WsRequest
{
    private static final Callback<List<MyCustomModel>> wsResponseCallback = new Callback<List<MyCustomModel>>()
    {

        @Override
        public void onResponse(Call<List<MyCustomModel>> call, Response<List<MyCustomModel>> response)
        {
            if (response.code() == 200)
            {
                App.getCoreBus().post(new WsDataReadyEvent(response.body()));
            }
        }

        @Override
        public void onFailure(Call<List<MyCustomModel>> call, Throwable t)
        {
            // null parameter means call to ws failed
            App.getCoreBus().post(new WsDataReadyEvent(null));
        }
    };

    public static void getWsData()
    {
        if (App.isConnected())
        {
            //App.getRestClient().getApiService().getWsResponse().enqueue(wsResponseCallback);
        }
        else
        {
            App.getCoreBus().post(new WsDataReadyEvent(null));
        }
    }
}
