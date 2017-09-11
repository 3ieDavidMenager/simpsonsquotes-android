package fr.iiie.android.simpsonsquotes.data.bus;

import fr.iiie.android.simpsonsquotes.data.model.RandomQuoteModel;

public class RandomDataReadyEvent
{
    private RandomQuoteModel randomQuoteModel;
    private String error_msg;

    public RandomDataReadyEvent(RandomQuoteModel randomQuoteModel)
    {
        this.randomQuoteModel = randomQuoteModel;
    }

    public RandomDataReadyEvent(RandomQuoteModel randomQuoteModel, String error_msg)
    {
        this.randomQuoteModel = randomQuoteModel;
        this.error_msg = error_msg;
    }

    public String getError_msg()
    {
        return error_msg;
    }

    public RandomDataReadyEvent()
    {
        //Mandatory Constructor
    }

    public RandomQuoteModel getRandomQuoteModel()
    {
        return randomQuoteModel;
    }
}
