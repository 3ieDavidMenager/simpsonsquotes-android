package fr.iiie.android.simpsonsquotes.data.bus;

import java.util.List;

import fr.iiie.android.simpsonsquotes.data.model.QuoteResultModel;

public class SearchDataReadyEvent
{
    private List<QuoteResultModel> myQuoteResultsListModel;

    public SearchDataReadyEvent(List<QuoteResultModel> myQuoteResultsListModel)
    {

        this.myQuoteResultsListModel = myQuoteResultsListModel;
    }

    public List<QuoteResultModel> getMyQuoteResultsListModel()
    {
        return myQuoteResultsListModel;
    }
}
