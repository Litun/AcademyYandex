package ru.ya.litun.academyyandex.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Litun on 21.04.2016.
 */
public class Artist {
    int id;
    String name;
    List<String> genres = new ArrayList<>();
    int tracks;
    int albums;
    String link;
    String description;
    Cover cover;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSmallCover() {
        return cover.small;
    }

    public String getBigCover() {
        return cover.big;
    }

    public List<String> getGenres() {
        return genres;
    }

    public int getAlbums() {
        return albums;
    }

    public int getTracks() {
        return tracks;
    }

    public String getDescription() {
        return description;
    }
}
