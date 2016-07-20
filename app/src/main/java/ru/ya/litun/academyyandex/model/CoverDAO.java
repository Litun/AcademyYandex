package ru.ya.litun.academyyandex.model;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Litun on 20.07.2016.
 */
public class CoverDAO extends BaseDaoImpl<Cover, Integer> {
    protected CoverDAO(ConnectionSource connectionSource, Class<Cover> dataClass)
            throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<Cover> getAllCovers() throws SQLException {
        return this.queryForAll();
    }
}
