package com.example.code97mvvm.ui.users.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.example.code97mvvm.ui.users.models.UsersModel;
import com.example.code97mvvm.ui.users.activities.UserDetails;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder> {

    private List<UsersModel> usersModelList;
    private Context context;

    public UsersAdapter(List<UsersModel> usersModelList, Context context) {
        this.usersModelList = usersModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_layout, parent, false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        String input = usersModelList.get(position).getTitle();
        String output = input.substring(0, 1).toUpperCase() + input.substring(1);
        holder.title.setText(output);
        holder.firstName.setText(usersModelList.get(position).getFirstName());
        holder.lastName.setText(usersModelList.get(position).getLastName());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(16));
        Glide.with(context).load(usersModelList.get(position).getPicture()).apply(requestOptions).into(holder.picture);

        holder.setClick(position);


    }

    @Override
    public int getItemCount() {
        return usersModelList.size();
    }

    class UsersViewHolder extends RecyclerView.ViewHolder {
        TextView firstName, lastName, title;
        CircleImageView picture;


        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvTitle);
            firstName = itemView.findViewById(R.id.tvFirstName);
            lastName = itemView.findViewById(R.id.tvLastName);
            picture = itemView.findViewById(R.id.ivPicture);


        }

        private void setClick(int position) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, UserDetails.class);
                    intent.putExtra("KEY", usersModelList.get(position).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
