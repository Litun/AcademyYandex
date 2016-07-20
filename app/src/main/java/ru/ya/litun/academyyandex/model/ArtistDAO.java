package ru.ya.litun.academyyandex.model;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Litun on 20.07.2016.
 */
public class ArtistDAO extends BaseDaoImpl<Artist, Integer> {

    public ArtistDAO(ConnectionSource connectionSource, Class<Artist> artistClass)
            throws SQLException {
        super(connectionSource, artistClass);
    }

    public List<Artist> getAllArtists() throws SQLException {
        List<Artist> artists = this.queryForAll();
        return artists;
    }

    public Artist getArtist(int id) {
        try {
            Artist artist = queryForId(id);
            return artist;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
