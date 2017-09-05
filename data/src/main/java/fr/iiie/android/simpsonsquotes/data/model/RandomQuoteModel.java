package fr.iiie.android.simpsonsquotes.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class RandomQuoteModel
{
    @Expose
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

    public Episode getEpisode()
    {
        return episode;
    }

    public QuoteSearchModel getFrame()
    {
        return frame;
    }

    public List<Subtitles> getSubtitles()
    {
        return subtitles;
    }

    public List<QuoteSearchModel> getNearby()
    {
        return nearby;
    }

    @Parcel
    public static class Subtitles
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

        public int getId()
        {
            return id;
        }

        public int getRepresentativeTimeStamp()
        {
            return representativeTimeStamp;
        }

        public String getEpisode()
        {
            return episode;
        }

        public int getStartTimeStamp()
        {
            return startTimeStamp;
        }

        public int getEndTimeStamp()
        {
            return endTimeStamp;
        }

        public String getContent()
        {
            return content;
        }

        public String getLanguage()
        {
            return language;
        }
    }

    @Parcel
    public static class Episode
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
