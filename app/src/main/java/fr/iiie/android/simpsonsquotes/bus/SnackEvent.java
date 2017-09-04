package fr.iiie.android.simpsonsquotes.bus;

public class SnackEvent
{
    final private String sMessage;

    public SnackEvent(String sMessage)
    {
        this.sMessage = sMessage;
    }

    public String getMessage()
    {
        return sMessage;
    }
}
