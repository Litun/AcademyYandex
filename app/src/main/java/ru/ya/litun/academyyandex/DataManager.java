package ru.ya.litun.academyyandex;

import android.util.SparseArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.ya.litun.academyyandex.api.ApiManager;
import ru.ya.litun.academyyandex.model.Artist;

/**
 * Created by Litun on 21.04.2016.
 */
public class DataManager implements ApiManager.ArtistsListener, ApiManager.FailureListener {
    private ApiManager apiManager;
    private DataListener listener;
    private UpdateFailureListener failureListener;
    private Map<Integer, Artist> cachedArtists = new HashMap<>();

    public DataManager() {
        apiManager = new ApiManager();
        apiManager.setArtistsListener(this);
        apiManager.setFailureListener(this);
    }

    public List<Artist> getCachedArtists() {
        //TODO: fix order
        return new ArrayList<>(cachedArtists.values());
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
        cachedArtists.clear();
        for (Artist artist : artists) {
            cachedArtists.put(artist.getId(), artist);
        }
        if (listener != null)
            listener.onArtistsUpdated(getCachedArtists());
    }

    public Artist getArtist(int artistId) {
        return cachedArtists.get(artistId);
    }

    public void clearCache() {
        cachedArtists = null;
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
