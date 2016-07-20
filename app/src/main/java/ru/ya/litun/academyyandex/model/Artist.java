package ru.ya.litun.academyyandex.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Litun on 21.04.2016.
 */
@DatabaseTable(tableName = "artists")
public class Artist {

    public static final String ID_COLUMN = "id";
    @DatabaseField(id = true, columnName = ID_COLUMN)
    int id;
    @DatabaseField(dataType = DataType.STRING)
    String name;
    @ForeignCollectionField(eager = true)
    Collection<Genre> genres = new ArrayList<>();
    @DatabaseField
    int tracks;
    @DatabaseField
    int albums;
    @DatabaseField
    String link;
    @DatabaseField(dataType = DataType.STRING)
    String description;
    @DatabaseField(foreign = true)
    Cover cover;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSmallCover() {
        return cover == null ? null : cover.small;
    }

    public Cover getCover() {
        return cover;
    }

    public String getBigCover() {
        return cover == null ? null : cover.big;
    }

    public Collection<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Collection<Genre> genres) {
        this.genres = genres;
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
