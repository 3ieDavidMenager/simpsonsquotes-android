package fr.iiie.android.simpsonsquotes.data.bus;

import java.util.List;

import fr.iiie.android.simpsonsquotes.data.model.QuoteResult;

public class SearchDataReadyEvent
{
    private List<QuoteResult> myQuoteResultsList;

    public SearchDataReadyEvent(List<QuoteResult> myQuoteResultsList)
    {

        this.myQuoteResultsList = myQuoteResultsList;
    }

    public List<QuoteResult> getMyQuoteResultsList()
    {
        return myQuoteResultsList;
    }
}
