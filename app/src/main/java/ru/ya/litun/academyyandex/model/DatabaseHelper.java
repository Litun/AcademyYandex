package ru.ya.litun.academyyandex.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.WorkerThread;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

import ru.ya.litun.academyyandex.App;

/**
 * Created by Litun on 20.07.2016.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String TAG = DatabaseHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "artists.db";

    private static final int DATABASE_VERSION = 1;

    private ArtistDAO artistDAO = null;
    private GenreGAO genreGAO = null;
    private CoverDAO coverDAO = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Artist.class);
            TableUtils.createTable(connectionSource, Genre.class);
            TableUtils.createTable(connectionSource, Cover.class);
        } catch (SQLException e) {
            Log.e(TAG, "error creating db " + DATABASE_NAME);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVer,
                          int newVer) {
        try {
            TableUtils.dropTable(connectionSource, Artist.class, true);
            TableUtils.dropTable(connectionSource, Genre.class, true);
            TableUtils.dropTable(connectionSource, Cover.class, true);
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            Log.e(TAG, "error upgrading db " + DATABASE_NAME + "from ver " + oldVer);
            throw new RuntimeException(e);
        }
    }

    @WorkerThread
    public boolean updateArtists(List<Artist> artists) {
        try {
            for (Artist a : artists) {
                ArtistDAO artistDAO = getArtistDAO();
                if (artistDAO.idExists(a.getId()))
                    continue;
                artistDAO.create(a);
                for (Genre g : a.getGenres()) {
                    g.setArtist(a);
                    getGenreDAO().create(g);
                }
                getCoverDAO().create(a.getCover());
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Artist> getArtists() {
        List<Artist> allArtists = null;
        try {
            allArtists = App.getHelper().getArtistDAO().getAllArtists();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allArtists;
    }

    public ArtistDAO getArtistDAO() throws SQLException {
        if (artistDAO == null) {
            artistDAO = new ArtistDAO(getConnectionSource(), Artist.class);
        }
        return artistDAO;
    }

    public GenreGAO getGenreDAO() throws SQLException {
        if (genreGAO == null) {
            genreGAO = new GenreGAO(getConnectionSource(), Genre.class);
        }
        return genreGAO;
    }

    public CoverDAO getCoverDAO() throws SQLException {
        if (coverDAO == null) {
            coverDAO = new CoverDAO(getConnectionSource(), Cover.class);
        }
        return coverDAO;
    }

    @Override
    public void close() {
        super.close();
        artistDAO = null;
        genreGAO = null;
        coverDAO = null;
    }
}
