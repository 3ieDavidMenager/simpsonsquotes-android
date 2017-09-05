package fr.iiie.android.simpsonsquotes.business.search;

import org.greenrobot.eventbus.Subscribe;

import fr.iiie.android.simpsonsquotes.business.Controller;
import fr.iiie.android.simpsonsquotes.data.app.App;
import fr.iiie.android.simpsonsquotes.data.bus.SearchDataReadyEvent;
import fr.iiie.android.simpsonsquotes.data.request.SearchRequest;

public class SearchController extends Controller
{
    public SearchController()
    {

    }

    public void getSearchResponse(String query)
    {
        SearchRequest.getSearchResponse(query);
    }

    @Subscribe
    public void onSearchDataReadyEvent(SearchDataReadyEvent searchDataReadyEvent)
    {
        App.getAppBus().post(searchDataReadyEvent);
    }
}
