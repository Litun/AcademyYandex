package ru.ya.litun.academyyandex;

import java.util.ArrayList;
import java.util.List;

import ru.ya.litun.academyyandex.api.ApiManager;
import ru.ya.litun.academyyandex.model.Artist;

/**
 * Created by Litun on 21.04.2016.
 */
public class DataManager implements ApiManager.ArtistsListener {
    private ApiManager apiManager;
    private DataListener listener;

    public DataManager() {
        apiManager = new ApiManager();
        apiManager.setArtistsListener(this);
    }

    public List<Artist> getCachedArtists() {
        //TODO: implement
        return new ArrayList<>();
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

    public void unregisterListener() {
        listener = null;
    }

    @Override
    public void onArtistsLoaded(List<Artist> artists) {
        if (listener != null)
            listener.onArtistsUpdated(artists);
    }

    public interface DataListener {
        void onArtistsUpdated(List<Artist> artists);
    }
}
