package fr.iiie.android.simpsonsquotes.data.bus;

import java.util.List;

import fr.iiie.android.simpsonsquotes.data.model.QuoteSearchModel;

public class SearchDataReadyEvent
{
    private List<QuoteSearchModel> myQuoteResultsListModel;
    private String error_msg;

    public SearchDataReadyEvent(List<QuoteSearchModel> myQuoteResultsListModel)
    {
        this.myQuoteResultsListModel = myQuoteResultsListModel;
    }

    public SearchDataReadyEvent(List<QuoteSearchModel> myQuoteResultsListModel, String error_msg)
    {
        this.myQuoteResultsListModel = myQuoteResultsListModel;
        this.error_msg = error_msg;
    }

    public String getError_msg()
    {
        return error_msg;
    }

    public List<QuoteSearchModel> getMyQuoteResultsListModel()
    {
        return myQuoteResultsListModel;
    }
}
