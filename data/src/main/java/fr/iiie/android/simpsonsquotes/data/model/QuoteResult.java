package fr.iiie.android.simpsonsquotes.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// TODO 3ie coding style : each curly bracket on its own line
public class QuoteResult {

    @Expose
    @SerializedName("Id")
    int id;

    @Expose
    @SerializedName("Episode")
    String episode;

    @Expose
    @SerializedName("Timestamp")
    int timestamp;

    public QuoteResult()
    {
        // TODO When adding the "mandatory" empty constructor, do not leave it empty, put in a comment
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
