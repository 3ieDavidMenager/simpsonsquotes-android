package fr.iiie.android.simpsonsquotes.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RandomQuoteModel
{
    @SerializedName("Episode")
    Episode episode;

    @Expose
    @SerializedName("Frame")
    QuoteSearchModel frame;

    @Expose
    @SerializedName("Subtitles")
    List<Subtitles> subtitles;

    @Expose
    @SerializedName("Nearby")
    List<QuoteSearchModel> nearby;

    private class Subtitles
    {
        @Expose
        @SerializedName("Id")
        int id;

        @Expose
        @SerializedName("RepresentativeTimeStamp")
        int representativeTimeStamp;

        @Expose
        @SerializedName("Episode")
        String episode;

        @Expose
        @SerializedName("StartTimeStamp")
        int startTimeStamp;

        @Expose
        @SerializedName("EndTimeStamp")
        int endTimeStamp;

        @Expose
        @SerializedName("Content")
        String content;

        @Expose
        @SerializedName("Language")
        String language;

    }

    private class Episode
    {
        @Expose
        @SerializedName("Id")
        int id;

        @Expose
        @SerializedName("Key")
        String key;

        @Expose
        @SerializedName("Season")
        int season;

        @Expose
        @SerializedName("EpisodeNumber")
        int episodeNumber;

        @Expose
        @SerializedName("Title")
        String title;

        @Expose
        @SerializedName("Director")
        String director;

        @Expose
        @SerializedName("Writer")
        String writer;

        @Expose
        @SerializedName("OriginalAirDate")
        String originalAirDate;

        @Expose
        @SerializedName("WikiLink")
        String wikilink;

        public int getId()
        {
            return id;
        }

        public String getKey()
        {
            return key;
        }

        public int getSeason()
        {
            return season;
        }

        public int getEpisodeNumber()
        {
            return episodeNumber;
        }

        public String getTitle()
        {
            return title;
        }

        public String getDirector()
        {
            return director;
        }

        public String getWriter()
        {
            return writer;
        }

        public String getOriginalAirDate()
        {
            return originalAirDate;
        }

        public String getWikilink()
        {
            return wikilink;
        }
    }
}
