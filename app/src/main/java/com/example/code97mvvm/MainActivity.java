package com.example.code97mvvm;

import static android.content.ContentValues.TAG;

import static com.example.code97mvvm.util.Constants.isAllFabsVisible;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.code97mvvm.databinding.ActivityMainBinding;
import com.example.code97mvvm.ui.posts.PostsFragment;
import com.example.code97mvvm.ui.users.adapter.UsersAdapter;
import com.example.code97mvvm.ui.users.createUser.CreateResponse;
import com.example.code97mvvm.ui.users.models.UsersResponse;
import com.example.code97mvvm.ui.users.viewModel.UsersViewModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    // to check whether sub FABs are visible or not

    UsersViewModel usersViewModel;

    // fragment id
    String label;

    NavController navController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setSupportActionBar(binding.appBarMain.toolbar);


        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_posts, R.id.nav_users, R.id.nav_comments, R.id.nav_tags)
                .setOpenableLayout(drawer)
                .build();

        View view = navigationView.getHeaderView(0);
        ImageView imageView = view.findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.pbg);


        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                Log.e(TAG, "onDestinationChanged: " + destination.getLabel());

                label = destination.getLabel().toString();

            }
        });


        // texts as GONE
        binding.appBarMain.floatingActionButtonCreate.setVisibility(View.GONE);
        binding.appBarMain.floatingActionButtonDelete.setVisibility(View.GONE);
        binding.appBarMain.floatingActionButtonUpdate.setVisibility(View.GONE);

        binding.appBarMain.tvCreateText.setVisibility(View.GONE);
        binding.appBarMain.tvUpdateText.setVisibility(View.GONE);
        binding.appBarMain.tvDeleteText.setVisibility(View.GONE);

        // make the boolean variable as false, as all the
        // action name texts and all the sub FABs are
        // invisible
        isAllFabsVisible = false;
        // Set the Extended floating action button to
        // shrinked state initially
        binding.appBarMain.fab.shrink();
        // We will make all the FABs and action name texts
        // visible only when Parent FAB button is clicked So
        // we have to handle the Parent FAB button first, by
        // using setOnClickListener you can see below


        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!isAllFabsVisible) {
                    showFab();
                } else {
                    hideFab();
                }
            }
        });

        binding.appBarMain.floatingActionButtonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                gotoCrudActivity(v);



            }
        });
        binding.appBarMain.floatingActionButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoCrudActivity(v);



            }
        });
        binding.appBarMain.floatingActionButtonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gotoCrudActivity(v);


            }
        });


    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if (isAllFabsVisible) {

            hideFab();

        } else {

            super.onBackPressed();
        }
    }

    private void gotoCrudActivity(View v) {
        Intent intent = new Intent(getApplicationContext(), CrudActivity.class);
        intent.putExtra("KEY", v==binding.appBarMain.floatingActionButtonUpdate? "Update": (v==binding.appBarMain.floatingActionButtonCreate? "Create":"Delete"));
        intent.putExtra("LABEL", label);
        startActivity(intent);
    }

    private void hideFab() {
        // when isAllFabsVisible becomes
        // true make all the action name
        // texts and FABs GONE.
        binding.appBarMain.floatingActionButtonCreate.hide();
        binding.appBarMain.floatingActionButtonDelete.hide();
        binding.appBarMain.floatingActionButtonUpdate.hide();

        binding.appBarMain.tvCreateText.setVisibility(View.GONE);
        binding.appBarMain.tvUpdateText.setVisibility(View.GONE);
        binding.appBarMain.tvDeleteText.setVisibility(View.GONE);

        // Set the FAB to shrink after user
        // closes all the sub FABs
        binding.appBarMain.fab.shrink();
        // make the boolean variable false
        // as we have set the sub FABs
        // visibility to GONE
        isAllFabsVisible = false;

    }

    private void showFab() {
        // when isAllFabsVisible becomes
        // true make all the action name
        // texts and FABs VISIBLE.

        binding.appBarMain.floatingActionButtonCreate.show();
        binding.appBarMain.floatingActionButtonDelete.show();
        binding.appBarMain.floatingActionButtonUpdate.show();

        binding.appBarMain.tvCreateText.setVisibility(View.VISIBLE);
        binding.appBarMain.tvUpdateText.setVisibility(View.VISIBLE);
        binding.appBarMain.tvDeleteText.setVisibility(View.VISIBLE);

        // Now extend the parent FAB, as
        // user clicks on the shrinked
        // parent FAB
        binding.appBarMain.fab.extend();
        // make the boolean variable true as
        // we have set the sub FABs
        // visibility to GONE
        isAllFabsVisible = true;
    }

    private void setUpOnClick(){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        };
    }


    @Override
    protected void onPause() {
        super.onPause();
        hideFab();
    }


}
