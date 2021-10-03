package com.example.code97mvvm.ui.tags.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.code97mvvm.R;
import com.example.code97mvvm.ui.tags.model.RootTags;

public class TagsAdapter extends RecyclerView.Adapter<TagsAdapter.TagsViewHolder> {

    RootTags list;

    public TagsAdapter(RootTags list) {
        this.list = list;
    }

    @NonNull
    @Override
    public TagsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tags_layout, parent, false);
        return new TagsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TagsViewHolder holder, int position) {


        StringBuilder stringBuilder = new StringBuilder();
        for (String details : list.getData()) {
            stringBuilder.append(details + "\n");
        }
//
        holder.tags.setText(stringBuilder.toString());


    }

    @Override
    public int getItemCount() {
        return list.data.size();
    }

    class TagsViewHolder extends RecyclerView.ViewHolder {
        TextView tags;


        public TagsViewHolder(@NonNull View itemView) {
            super(itemView);


            tags = itemView.findViewById(R.id.tvTags);



        }
    }
}
