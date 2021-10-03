package com.example.code97mvvm.ui.posts.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.code97mvvm.R;
import com.example.code97mvvm.ui.posts.model.Datum;
import com.example.code97mvvm.ui.users.activities.UserDetails;

import java.text.DateFormat;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostViewHolder> {
    List<Datum> list;
    Context context;

    public PostsAdapter(List<Datum> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.posts_layout, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {

        Datum root = list.get(position);

        holder.tvId.setText(root.getId());

        holder.name.setText(root.getOwner().getTitle().substring(0,1).toUpperCase(Locale.ROOT) + root.getOwner().getTitle().substring(1) + "." + " " + root.getOwner().getFirstName() + " " + root.getOwner().getLastName());

        holder.date1.setText(DateFormat.getDateTimeInstance().format(root.getPublishDate()));


        holder.text.setText(root.getText());




        for (int i = 0; i < root.getTags().size(); i++) {
            if (i == 0) {

                holder.tags1.setText( "#"+ root.tags.get(i));

            } else if (i == 1) {
                holder.tags2.setText( "#"+ root.tags.get(i));

            } else {
                holder.tags3.setText( "#"+ root.tags.get(i));

            }

         }


        holder.likes.setText(String.valueOf(root.getLikes()));

        RequestOptions requestOptions = new RequestOptions();

        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(16));

        Glide.with(context).load(list.get(position).getOwner().getPicture()).apply(requestOptions).into(holder.picture);

        Glide.with(context).load(list.get(position).getImage()).apply(requestOptions).into(holder.postedImage);


        holder.picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = list.get(position).getOwner().getId();

                Intent intent = new Intent(context, UserDetails.class);
                intent.putExtra("KEY",id);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder {

        TextView name, date1,tvId, text, tags1, tags2, tags3, likes;
        CircleImageView picture;
        ImageView postedImage;


        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvName);

            date1 = itemView.findViewById(R.id.tvPublishDate);

            text = itemView.findViewById(R.id.tvText);

            tags1 = itemView.findViewById(R.id.tvTags1);

            tags2 = itemView.findViewById(R.id.tvTags2);

            tags3 = itemView.findViewById(R.id.tvTags3);

            tvId = itemView.findViewById(R.id.tvId);

            likes = itemView.findViewById(R.id.tvLikes);

            picture = itemView.findViewById(R.id.circularImageView);

            postedImage = itemView.findViewById(R.id.ivPostedImage);


        }
    }
}
