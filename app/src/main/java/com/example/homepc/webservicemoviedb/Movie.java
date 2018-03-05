package com.example.homepc.webservicemoviedb;

import java.util.ArrayList;
import java.util.List;

/**
 * This is our Model class, which handles how we read data
 */

public class Movie {
    private String mName,mVotes,mId;
    public static List<Movie> movieList = new ArrayList<>();


    public Movie(String mName, String mVotes, String mId) {
        this.mName = mName;
        this.mVotes = mVotes;
        this.mId = mId;

    }

    public String getmName() {
        return this.mName;
    }


    public String getmVotes() {
        return this.mVotes;
    }



    public String getmId() {
        return this.mId;
    }


}
