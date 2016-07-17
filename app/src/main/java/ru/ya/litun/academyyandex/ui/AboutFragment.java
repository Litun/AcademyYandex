package ru.ya.litun.academyyandex.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ru.ya.litun.academyyandex.App;
import ru.ya.litun.academyyandex.R;
import ru.ya.litun.academyyandex.StringFormatUtils;
import ru.ya.litun.academyyandex.model.Artist;

public class AboutFragment extends ToolbarFragment {

    public static final String ID_KEY = "artist-id";
    private ImageView image;
    private TextView genres;
    private TextView info;
    private TextView biography;

    public AboutFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        image = (ImageView) view.findViewById(R.id.image);
        genres = (TextView) view.findViewById(R.id.genres);
        info = (TextView) view.findViewById(R.id.albums);
        biography = (TextView) view.findViewById(R.id.biography);

        Bundle arguments = getArguments();
        int artistId = arguments.getInt(ID_KEY, -1);
        Artist artist = App.getDataManager().getArtist(artistId);

        Picasso.with(getContext())
                .load(artist.getBigCover())
                .placeholder(R.drawable.placeholder)
                .into(image);
        genres.setText(StringFormatUtils.concatWithCommas(artist.getGenres()));
        info.setText(StringFormatUtils.formatAlbumsAndSongs(artist.getAlbums(), artist.getTracks()));
        biography.setText(artist.getDescription());

        setToolbar((Toolbar) view.findViewById(R.id.toolbar));
        setTitle(artist.getName());
        upButon(true);

        return view;
    }
}
