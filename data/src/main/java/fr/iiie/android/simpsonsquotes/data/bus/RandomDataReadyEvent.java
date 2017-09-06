package fr.iiie.android.simpsonsquotes.data.bus;

import org.parceler.Parcel;

import fr.iiie.android.simpsonsquotes.data.model.RandomQuoteModel;

// TODO RandomDataReadyEvent is a parcel ?
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
        // TODO use comments in empty constructor
    }

    public RandomQuoteModel getRandomQuoteModel()
    {
        return randomQuoteModel;
    }
}
