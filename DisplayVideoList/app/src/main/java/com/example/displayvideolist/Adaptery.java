package com.example.displayvideolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

import java.util.List;

public class Adaptery extends RecyclerView.Adapter<Adaptery.MyViewHolder>{
    private final Context mContext;

    private final List<MovieModelClass> mData;

    public Adaptery(Context mContext, List<MovieModelClass> mData) {
        this.mContext = mContext;
        this.mData = mData;
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
        return new MyViewHolder(v);
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
    }

    /**
     * @return
     */
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView description;
        ImageView thumb;

        TextView subtitle;
        TextView title;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.description_txt);
            subtitle = itemView.findViewById(R.id.subtitle_txt);
            title = itemView.findViewById(R.id.title_txt);
            thumb = itemView.findViewById(R.id.thumb_movie);

        }
    }


}
