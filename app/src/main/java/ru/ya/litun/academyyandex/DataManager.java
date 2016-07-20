package ru.ya.litun.academyyandex;

import android.os.AsyncTask;

import java.sql.SQLException;
import java.util.List;

import ru.ya.litun.academyyandex.api.ApiManager;
import ru.ya.litun.academyyandex.model.Artist;
import ru.ya.litun.academyyandex.model.DatabaseHelper;

/**
 * Allow data access.
 * Only one way to get data.
 * Created by Litun on 21.04.2016.
 */
public class DataManager implements ApiManager.ArtistsListener, ApiManager.FailureListener {
    private ApiManager apiManager;
    private DataListener listener;
    private UpdateFailureListener failureListener;

    public DataManager() {
        apiManager = new ApiManager();
        apiManager.setArtistsListener(this);
        apiManager.setFailureListener(this);
    }

    public List<Artist> getCachedArtists() {
        return App.getHelper().getArtists();
    }

    public void requestArtistsUpdate() {
        apiManager.requestArtists();
    }

    public void requestArtistsUpdate(DataListener listener) {
        setListener(listener);
        requestArtistsUpdate();
    }

    public void setListener(DataListener listener) {
        this.listener = listener;
    }

    public void unregisterListeners() {
        listener = null;
        failureListener = null;
    }

    @Override
    public void onArtistsLoaded(List<Artist> artists) {
        final DatabaseHelper helper = App.getHelper();
        new AsyncTask<List<Artist>, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(List<Artist>... params) {
                return helper.updateArtists(params[0]);
            }

            @Override
            protected void onPostExecute(Boolean success) {
                if (success && listener != null)
                    listener.onArtistsUpdated(getCachedArtists());
            }
        }.execute(artists);
    }

    public Artist getArtist(int artistId) {
        try {
            Artist artist = App.getHelper().getArtistDAO().getArtist(artistId);
            return artist;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setFailureListener(UpdateFailureListener failureListener) {
        this.failureListener = failureListener;
    }

    @Override
    public void onFailure(Throwable t) {
        if (failureListener != null)
            failureListener.onFailure();
    }

    public interface DataListener {
        void onArtistsUpdated(List<Artist> artists);
    }

    public interface UpdateFailureListener {
        void onFailure();
    }
}
