package com.example.displayvideolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.PlayerView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.displayvideolist.databinding.MovieItemBinding;

import java.util.List;

public class Adaptery extends RecyclerView.Adapter<Adaptery.MyViewHolder>{
    private static Context mContext;
   private static List<MovieModelClass> mData = null;

   private ExoPlayer m_exoplayer;

    public Adaptery(Context mContext, List<MovieModelClass> mData) {
        Adaptery.mContext = mContext;
        Adaptery.mData = mData;
    }

    /**
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        v = inflater.inflate(R.layout.movie_item,parent,false);
        MovieItemBinding binding = MovieItemBinding.inflate(inflater, parent, false);
        return new MyViewHolder(v, mContext);
        //return new MyViewHolder(binding.getRoot());
    }

    /**
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.title.setText(mData.get(position).getTitle());
        holder.description.setText(mData.get(position).getDescription());
        holder.subtitle.setText(mData.get(position).getSubtitle());
        Glide.with(mContext)
                .load(mData.get(position).getThumbnail())
                .into(holder.thumb);
        //holder.url.setText(mData.get(position).getUrl());
        holder.bindMedia(mData.get(position).getUrl());


        //holder.bind(position);




    }

    /**
     * @return
     */
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView description;
        ImageView thumb;

        TextView subtitle;
        TextView title;
        TextView url;

        PlayerView playerView;
        ExoPlayer m_exoPlayer;

        MovieItemBinding binding;



        public MyViewHolder(@NonNull View itemView, Context mContext) {
            super(itemView);
            description = itemView.findViewById(R.id.description_txt);
            subtitle = itemView.findViewById(R.id.subtitle_txt);
            title = itemView.findViewById(R.id.title_txt);
            thumb = itemView.findViewById(R.id.thumb_movie);
            url = itemView.findViewById(R.id.url_txt);
            playerView = itemView.findViewById(R.id.player_view);
            m_exoPlayer = new ExoPlayer.Builder(Adaptery.mContext).build();
            playerView.setPlayer(m_exoPlayer);
            //initializePlayer();


        }

        private void initializePlayer() {
            m_exoPlayer = new ExoPlayer.Builder(  mContext).build();
            playerView.setPlayer(m_exoPlayer);
            binding.playerView.setPlayer(m_exoPlayer);
        }
        void bindMedia(String mediaUri) {
            m_exoPlayer.setMediaItem(MediaItem.fromUri(mediaUri));
            m_exoPlayer.prepare();
            //m_exoPlayer.play();
        }

        public void bind(int position) {
            MovieModelClass movie = mData.get(position);
            binding.titleTxt.setText(movie.getTitle());
            binding.descriptionTxt.setText(movie.getDescription());
            binding.subtitleTxt.setText(movie.getSubtitle());
            Glide.with(mContext).load(movie.getThumbnail()).into(binding.thumbMovie);
            bindMedia(movie.getUrl());
        }

        // Release player when ViewHolder is recycled
        public void releasePlayer() {
            m_exoPlayer.release();
        }


        @Override
        public void onClick(View v) {
            // Start playback when item is clicked
            m_exoPlayer.play();

        }


    }


}
