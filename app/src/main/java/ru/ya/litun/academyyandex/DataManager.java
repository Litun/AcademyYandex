package ru.ya.litun.academyyandex;

import java.util.ArrayList;
import java.util.List;

import ru.ya.litun.academyyandex.model.Artist;

/**
 * Created by Litun on 21.04.2016.
 */
public class DataManager {
    private DataListener listener;

    public List<Artist> getCachedArtists() {
        //TODO: implement
        return new ArrayList<>();
    }

    public void requestUpdate(){
        //TODO: implement
    }

    public void requestUpdate(DataListener listener){
        setListener(listener);
        requestUpdate();
    }

    public void setListener(DataListener listener) {
        this.listener = listener;
    }

    public void unregisterListener() {
        listener = null;
    }

    public interface DataListener {
        void onArtistsUpdated(List<Artist> artists);
    }
}
