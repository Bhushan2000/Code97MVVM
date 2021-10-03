package com.example.code97mvvm.ui.comments.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.code97mvvm.R;
import com.example.code97mvvm.ui.comments.model.Data;

import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder> {

    List<Data> list;
    Context context;


    public CommentsAdapter(List<Data> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public CommentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comments_layout, parent, false);
        return new CommentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsViewHolder holder, int position) {
        holder.name.setText(list.get(position).getOwner().getTitle().substring(0, 1).toUpperCase(Locale.ROOT)
                + list.get(position).getOwner().getTitle().substring(1)
                + "."
                + " " + list.get(position).getOwner().getFirstName()
                + " " + list.get(position).getOwner().getLastName());
        holder.publishedDate.setText(list.get(position).getPublishDate());
        holder.message.setText(list.get(position).getMessage());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(16));
        Glide.with(context).load(list.get(position).getOwner().getPicture()).apply(requestOptions).into(holder.picture);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class CommentsViewHolder extends RecyclerView.ViewHolder {
        TextView name, publishedDate, message;
        CircleImageView picture;


        public CommentsViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tvName);

            publishedDate = itemView.findViewById(R.id.tvPublishedDate);

            message = itemView.findViewById(R.id.tvMessage);

            picture = itemView.findViewById(R.id.circularImageView);


        }
    }
}
