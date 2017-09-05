package fr.iiie.android.simpsonsquotes.data.bus;

import java.util.List;

import fr.iiie.android.simpsonsquotes.data.model.QuoteSearchModel;

public class SearchDataReadyEvent
{
    private List<QuoteSearchModel> myQuoteResultsListModel;

    public SearchDataReadyEvent(List<QuoteSearchModel> myQuoteResultsListModel)
    {

        this.myQuoteResultsListModel = myQuoteResultsListModel;
    }

    public List<QuoteSearchModel> getMyQuoteResultsListModel()
    {
        return myQuoteResultsListModel;
    }
}
