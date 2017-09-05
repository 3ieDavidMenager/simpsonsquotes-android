package fr.iiie.android.simpsonsquotes.data.bus;

import org.parceler.Parcel;

import fr.iiie.android.simpsonsquotes.data.model.RandomQuoteModel;

@Parcel
public class RandomDataReadyEvent
{
    private RandomQuoteModel randomQuoteModel;

    public RandomDataReadyEvent(RandomQuoteModel randomQuoteModel)
    {
        this.randomQuoteModel = randomQuoteModel;
    }

    public RandomDataReadyEvent()
    {

    }

    public RandomQuoteModel getRandomQuoteModel()
    {
        return randomQuoteModel;
    }
}
