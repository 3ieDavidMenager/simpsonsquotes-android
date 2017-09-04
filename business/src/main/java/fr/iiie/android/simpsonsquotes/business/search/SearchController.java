package fr.iiie.android.simpsonsquotes.business.search;

import fr.iiie.android.simpsonsquotes.business.Controller;
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
}
