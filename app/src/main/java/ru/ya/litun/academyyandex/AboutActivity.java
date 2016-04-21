package ru.ya.litun.academyyandex;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import ru.ya.litun.academyyandex.model.Artist;

public class AboutActivity extends AppCompatActivity {
    public static final String ID_KEY = "artist-id";
    private ImageView image;
    private TextView genres;
    private TextView info;
    private TextView biography;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        image = (ImageView) findViewById(R.id.image);
        genres = (TextView) findViewById(R.id.genres);
        info = (TextView) findViewById(R.id.albums);
        biography = (TextView) findViewById(R.id.biography);

        Intent intent = getIntent();
        int artistId = intent.getExtras().getInt(ID_KEY, -1);
        Artist artist = App.getDataManager().getArtist(artistId);


        getSupportActionBar().setTitle(artist.getName());
        Picasso.with(this)
                .load(artist.getBigCover())
                .placeholder(R.drawable.placeholder)
                .into(image);
        genres.setText(StringFormatUtils.concatWithCommas(artist.getGenres()));
        info.setText(StringFormatUtils.formatAlbumsAndSongs(artist.getAlbums(), artist.getTracks()));
        biography.setText(artist.getDescription());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
