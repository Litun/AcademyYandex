package ru.ya.litun.academyyandex.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Litun on 20.07.2016.
 */
@DatabaseTable(tableName = "genres")
public class Genre {
    @DatabaseField(generatedId = true)
    int id;
    @DatabaseField(dataType = DataType.STRING)
    String name;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    Artist artist;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
