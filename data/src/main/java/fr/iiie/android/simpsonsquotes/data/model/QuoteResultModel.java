package fr.iiie.android.simpsonsquotes.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuoteResultModel
{
    // TODO 3ie coding style : each curly bracket on its own line
    @Expose
    @SerializedName("Id")
    int id;

    @Expose
    @SerializedName("Episode")
    String episode;

    @Expose
    @SerializedName("Timestamp")
    int timestamp;


    public QuoteResultModel()
    {

    }

    public int getId()
    {
        return id;
    }

    public String getEpisode()
    {
        return episode;
    }

    public int getTimestamp()
    {
        return timestamp;
    }
}
