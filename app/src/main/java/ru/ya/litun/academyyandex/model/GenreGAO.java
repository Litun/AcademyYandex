package ru.ya.litun.academyyandex.model;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Litun on 20.07.2016.
 */
public class GenreGAO extends BaseDaoImpl<Genre, Integer> {

    public GenreGAO(ConnectionSource connectionSource, Class<Genre> genreClass)
            throws SQLException {
        super(connectionSource, genreClass);
    }

    public List<Genre> getAllGenres() throws SQLException {
        return this.queryForAll();
    }
}
