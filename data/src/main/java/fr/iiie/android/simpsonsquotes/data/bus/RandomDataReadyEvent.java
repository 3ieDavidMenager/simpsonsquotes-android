package fr.iiie.android.simpsonsquotes.data.bus;

import fr.iiie.android.simpsonsquotes.data.model.RandomQuoteModel;

public class RandomDataReadyEvent
{
    private RandomQuoteModel randomQuoteModel;

    public RandomDataReadyEvent(RandomQuoteModel randomQuoteModel)
    {
        this.randomQuoteModel = randomQuoteModel;
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
