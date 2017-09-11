package fr.iiie.android.simpsonsquotes.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class QuoteSearchModel
{
    @Expose
    @SerializedName("Id")
    int id;

    @Expose
    @SerializedName("Episode")
    String episode;

    @Expose
    @SerializedName("Timestamp")
    int timestamp;

    public QuoteSearchModel(int id, String episode, int timestamp)
    {
        this.id = id;
        this.episode = episode;
        this.timestamp = timestamp;
    }

    public QuoteSearchModel()
    {
        //Mandatory constructor
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
