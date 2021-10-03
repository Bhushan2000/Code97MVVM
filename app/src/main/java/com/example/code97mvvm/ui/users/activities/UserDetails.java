package com.example.code97mvvm.ui.users.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.code97mvvm.R;
import com.example.code97mvvm.ui.users.viewModel.UsersViewModel;
import com.example.code97mvvm.ui.users.models.RootUsers;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.util.Locale;

public class UserDetails extends AppCompatActivity {
    TextView name, email, dob, location, gender, phone, updated, tvCityCountry,tvId;
    ImageView picture;
    UsersViewModel usersViewModel;


    FloatingActionButton mAddAlarmFab, mAddPersonFab;
    ExtendedFloatingActionButton mAddFab;
    TextView addAlarmActionText, addPersonActionText;
    // to check whether sub FABs are visible or not
    Boolean isAllFabsVisible;

    RelativeLayout relativeLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        // showing the back button in action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("");


        Intent intent = getIntent();
        String id = intent.getStringExtra("KEY");


        name = findViewById(R.id.tvName);
        email = findViewById(R.id.tvEmail);
        dob = findViewById(R.id.tvDateOfBirth);
        location = findViewById(R.id.tvLocation);
        gender = findViewById(R.id.tvGender);
        phone = findViewById(R.id.tvPhone);
        updated = findViewById(R.id.tvRegisteredDate);
        picture = findViewById(R.id.ivPicture);
        tvCityCountry = findViewById(R.id.tvCityCountry);
        tvId = findViewById(R.id.tvId);
        relativeLayout = findViewById(R.id.relativeLayout);
        usersViewModel = new ViewModelProvider(this).get(UsersViewModel.class);

        usersViewModel.getUserById(id).observe(this, new Observer<RootUsers>() {
            @Override
            public void onChanged(RootUsers root) {

                 relativeLayout.setVisibility(View.GONE);

                name.setText(root.getTitle().substring(0,1).toUpperCase(Locale.ROOT) +root.getTitle().substring(1)+ ". "+ root.getFirstName() + " " + root.getLastName());
                email.setText(root.email);


                getSupportActionBar().setTitle(root.getTitle().substring(0,1).toUpperCase(Locale.ROOT) +root.getTitle().substring(1)+ ". "+ root.getFirstName() + " " + root.getLastName());

                tvId.setText( "ID: " + root.getId());
                dob.setText(DateFormat.getDateTimeInstance().format(root.dateOfBirth));
                tvCityCountry.setText(root.getLocation().getCity() + ", " + root.getLocation().getCountry());
                location.setText(root.location.getStreet() + " " + root.location.getCity() + ", " + root.getLocation().getCountry());
                gender.setText(root.gender);
                phone.setText(root.phone);
                updated.setText(DateFormat.getDateTimeInstance().format(root.updatedDate));
                RequestOptions requestOptions = new RequestOptions();
                requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(16));
                Glide.with(getApplicationContext()).load(root.picture).apply(requestOptions).into(picture);

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {

            this.finish();

            return true;


        }

        return super.onOptionsItemSelected(item);
    }
}