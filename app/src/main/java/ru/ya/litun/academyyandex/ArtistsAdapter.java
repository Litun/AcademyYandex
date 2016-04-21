package ru.ya.litun.academyyandex;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ru.ya.litun.academyyandex.model.Artist;

/**
 * Created by Litun on 21.04.2016.
 */
public class ArtistsAdapter extends RecyclerView.Adapter<ArtistsAdapter.ArtistViewHolder> {

    private Context context;
    private List<Artist> artists;

    public ArtistsAdapter(Context context, List<Artist> artists) {
        this.context = context;
        this.artists = artists;
    }

    public ArtistsAdapter(Context context) {
        this(context, new ArrayList<Artist>());
    }

    public void newList(List<Artist> artists) {
        this.artists = artists;
        notifyDataSetChanged();
    }

    @Override
    public ArtistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ArtistViewHolder holder;
        View v = LayoutInflater.from(context)
                .inflate(R.layout.artist_item, parent, false);
        holder = new ArtistViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ArtistViewHolder holder, int position) {
        Artist artist = artists.get(position);
        holder.name.setText(artist.getName());
        holder.genres.setText(StringFormatUtils.concatWithCommas(artist.getGenres()));
        holder.info.setText(StringFormatUtils.formatAlbumsAndSongs(artist.getAlbums(), artist.getTracks()));
        Picasso.with(context)
                .load(artist.getSmallCover())
                .placeholder(R.drawable.placeholder)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }

    static class ArtistViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView name;
        private TextView genres;
        private TextView info;

        public ArtistViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.image);
            name = (TextView) itemView.findViewById(R.id.name);
            genres = (TextView) itemView.findViewById(R.id.genres);
            info = (TextView) itemView.findViewById(R.id.info);
        }
    }
}
