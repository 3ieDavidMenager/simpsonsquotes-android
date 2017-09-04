package fr.iiie.android.simpsonsquotes.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyCustomModel
{
    @Expose
    @SerializedName("userId")
    int userId;

    @Expose
    @SerializedName("id")
    int id;

    @Expose
    @SerializedName("title")
    String sTitle;

    @Expose
    @SerializedName("body")
    String sBody;

    public MyCustomModel()
    {
        // required empty public constructor
    }

    public int getUserId()
    {
        return userId;
    }

    public int getId()
    {
        return id;
    }

    public String getTitle()
    {
        return sTitle;
    }

    public String getBody()
    {
        return sBody;
    }
}
