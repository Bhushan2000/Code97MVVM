package com.example.code97mvvm;


import static com.example.code97mvvm.util.Constants.COMMENTS;
import static com.example.code97mvvm.util.Constants.CREATE;
import static com.example.code97mvvm.util.Constants.DELETE;
import static com.example.code97mvvm.util.Constants.UPDATE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.util.StringUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.code97mvvm.ui.comments.viewModel.CommentsViewModel;
import com.example.code97mvvm.ui.posts.viewModel.PostsViewModel;
import com.example.code97mvvm.ui.users.createUser.CreateResponse;
import com.example.code97mvvm.ui.users.updateUser.Root;
import com.example.code97mvvm.ui.users.viewModel.UsersViewModel;
import com.example.code97mvvm.util.Views;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class CrudActivity extends AppCompatActivity {
    private static final String TAG = "CrudActivity";
    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText id;
    private String tag0;
    private String tag1;
    private String tag2;

    private RelativeLayout relativeLayout, relative;


    private Button btn_cancel;
    private Button btn_okay;
    private UsersViewModel usersViewModel;
    private TextView tvTitle;

    private RelativeLayout relativelayout;
    private String value;
    private String label;

    private PostsViewModel postsViewModel;
    private CommentsViewModel commentsModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);

        Bundle extras = getIntent().getExtras();
        value = extras.getString("KEY");
        label = extras.getString("LABEL");


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle(value);

        initViews();

        initViewModels();

        tvTitle.setText(value + " " + label);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        setUpActivityAccordingToLabel();


    }

    private void initViewModels() {
        usersViewModel = new ViewModelProvider(this).get(UsersViewModel.class);
        postsViewModel = new ViewModelProvider(this).get(PostsViewModel.class);
        commentsModel = new ViewModelProvider(this).get(CommentsViewModel.class);

    }

    private void initViews() {
        tvTitle = findViewById(R.id.tvTitle);
        firstName = (EditText) findViewById(R.id.tvFirstName);
        lastName = (EditText) findViewById(R.id.tvLastName);
        email = (EditText) findViewById(R.id.etEmail);
        id = (EditText) findViewById(R.id.tvId);


        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_okay = (Button) findViewById(R.id.btn_okay);


        relativelayout = findViewById(R.id.relativeLayout);
        relative = findViewById(R.id.relative);
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

    private void setUpActivityAccordingToLabel() {

        switch (label) {
            case "Posts":

                posts();

                break;

            case "Users":

                users();

                break;

            case "Comments":

                comments();

                break;
            case "Tags":

                Tags();

                break;
            default:
                break;
        }
    }

    private void Tags() {
        if (value.matches(COMMENTS)) {

            Snackbar.make(relative,"Comming soon",Snackbar.LENGTH_LONG).show();

        }
    }

    @Override
    protected void onDestroy() {
        relative.setVisibility(View.VISIBLE);
        super.onDestroy();
    }

    public String concatStringsWSep(Iterable<String> strings, String separator) {
        StringBuilder sb = new StringBuilder();
        String sep = "";
        for (String s : strings) {
            sb.append(sep).append(s);
            sep = separator;
        }
        return sb.toString();
    }

    private void posts() {
        if (value.matches(CREATE)) {
            Snackbar.make(relative, "Coming soon", Snackbar.LENGTH_LONG).show();
            relative.setVisibility(View.GONE);
        } else if (value.matches(UPDATE)) {

            id.setVisibility(View.VISIBLE);
            firstName.setVisibility(View.VISIBLE);
            lastName.setVisibility(View.VISIBLE);
            email.setVisibility(View.VISIBLE);


            // here previous views use for another task

            // likes
            firstName.setHint("Enter likes");
            // tags
            lastName.setHint("Enter tags");
            // Text
            email.setHint("Enter Text");


            btn_okay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (id.getText().toString().matches("")) {
                        Snackbar.make(btn_okay, "ID can't be blank or wrong ID provided", Snackbar.LENGTH_LONG).show();

                    } else {
                        Map<String, String> map = new HashMap<>();

                        map.put("likes", firstName.getText().toString());
                        map.put("text", email.getText().toString());

                        // for tags

                        String tagsString = lastName.getText().toString();

                        String[] tagsArray = tagsString.split(",");


                        try {
                            for (int i = 0; i < tagsArray.length; i++) {

                                if (i == 0) {
                                    tag0 = tagsArray[i].trim();

                                } else if (i == 1) {
                                    tag1 = tagsArray[1].trim();

                                } else {
                                    tag2 = tagsArray[2].trim();

                                }
                            }

                        } catch (Exception e) {

                            Views.showSnackBarLong(btn_okay, e.getMessage());
                        }


                        postsViewModel.updatingPost(id.getText().toString(),
                                firstName.getText().toString(),
                                email.getText().toString(),
                                tag0, tag1, tag2).
                                observe(CrudActivity.this, new Observer<com.example.code97mvvm.ui.posts.updatePosts.Root>() {
                                    @Override
                                    public void onChanged(com.example.code97mvvm.ui.posts.updatePosts.Root root) {


                                        Snackbar.make(btn_okay, "Post Updated", Snackbar.LENGTH_LONG).show();


                                    }
                                });

                    }
                }
            });


        } else if (value.matches(DELETE)) {
            id.setVisibility(View.GONE);
            firstName.setVisibility(View.GONE);
            lastName.setVisibility(View.GONE);
            email.setVisibility(View.GONE);

            firstName.setVisibility(View.GONE);
            lastName.setVisibility(View.GONE);
            email.setVisibility(View.GONE);

            id.setVisibility(View.VISIBLE);

            btn_okay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!id.getText().toString().matches("")) {
                        postsViewModel.deletePost(id.getText().toString()).observe(CrudActivity.this, new Observer<com.example.code97mvvm.ui.posts.deletePosts.Root>() {
                            @Override
                            public void onChanged(com.example.code97mvvm.ui.posts.deletePosts.Root root) {

                                Snackbar.make(btn_okay, "Post deleted", Snackbar.LENGTH_LONG).show();

                            }
                        });

                    } else {
                        Snackbar.make(btn_okay, "ID can't be blank or wrong ID provided", Snackbar.LENGTH_LONG).show();

                    }
                }
            });


        }
    }

    private void users() {
        if (value.matches(UPDATE)) {

            id.setVisibility(View.VISIBLE);
            firstName.setVisibility(View.VISIBLE);
            lastName.setVisibility(View.VISIBLE);
            email.setVisibility(View.GONE);


            btn_okay.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {


                    if (id.getText().toString().matches("")) {
                        Snackbar.make(v, "id should not blank", Snackbar.LENGTH_SHORT).show();
                        return;
                    } else {

                        usersViewModel.updateUser(id.getText().toString(), firstName.getText().toString(), lastName.getText().toString())
                                .observe(CrudActivity.this, new Observer<Root>() {
                                    @Override
                                    public void onChanged(Root root) {
                                        relativelayout.setVisibility(View.VISIBLE);

                                        if (root != null) {
                                            relativelayout.setVisibility(View.GONE);

                                            Toast.makeText(getApplicationContext(), "User Updated!", Toast.LENGTH_LONG).show();

                                            firstName.setText("");
                                            lastName.setText("");
                                            email.setText("");
                                            id.setText("");

                                            root = null;


                                        } else {
                                            relativelayout.setVisibility(View.GONE);
                                            Toast.makeText(getApplicationContext(), "Error While creating user", Toast.LENGTH_LONG).show();

                                        }
                                    }
                                });
                    }

                }
            });


        } else if (value.matches(CREATE)) {

            id.setVisibility(View.GONE);
            firstName.setVisibility(View.VISIBLE);
            lastName.setVisibility(View.VISIBLE);
            email.setVisibility(View.VISIBLE);


            btn_okay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                    if (firstName.getText().toString().matches("")) {
                        Snackbar.make(btn_okay, "firstName should not blank \nand length should be 2 or more", Snackbar.LENGTH_SHORT).show();
                        return;
                    } else if (lastName.getText().toString().matches("")) {
                        Snackbar.make(btn_okay, "lastName should not blank \nand length should be 2 or more", Snackbar.LENGTH_SHORT).show();
                        return;
                    } else if (email.getText().toString().matches("")) {
                        Snackbar.make(btn_okay, "email should not blank", Snackbar.LENGTH_SHORT).show();
                        return;

                    } else if (!email.getText().toString().matches(emailPattern)) {
                        Snackbar.make(btn_okay, "Not a valid email", Snackbar.LENGTH_SHORT).show();
                        return;
                    }

//                else if (id.getText().toString().matches("")) {
//                    Snackbar.make(v, "id should not blank", Snackbar.LENGTH_SHORT).show();
//                    return;
//                }

                    else {


                        usersViewModel.createUser(firstName.getText().toString(), lastName.getText().toString(), email.getText().toString()).observe(CrudActivity.this, new Observer<CreateResponse>() {
                            @Override
                            public void onChanged(CreateResponse createResponse) {
                                relativelayout.setVisibility(View.VISIBLE);


                                if (createResponse != null) {


                                    relativelayout.setVisibility(View.GONE);


                                    Toast.makeText(getApplicationContext(), "User Created Successfully", Toast.LENGTH_LONG).show();


                                    firstName.setText("");
                                    lastName.setText("");
                                    email.setText("");


                                    createResponse = null;


                                } else {
                                    relativelayout.setVisibility(View.GONE);

                                    Toast.makeText(getApplicationContext(), "Error While creating user", Toast.LENGTH_LONG).show();

                                }


                            }

                        });
                    }

                }
            });


        } else {
            firstName.setVisibility(View.GONE);
            lastName.setVisibility(View.GONE);
            email.setVisibility(View.GONE);

            btn_okay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (id.getText().toString().matches("")) {
                        Snackbar.make(v, "id should not blank", Snackbar.LENGTH_SHORT).show();

                    } else {


                        usersViewModel.deleteUser(id.getText().toString()).observe(CrudActivity.this, new Observer<com.example.code97mvvm.ui.users.deleteUser.Root>() {
                            @Override
                            public void onChanged(com.example.code97mvvm.ui.users.deleteUser.Root root) {
                                relativelayout.setVisibility(View.VISIBLE);

                                if (root != null) {
                                    relativelayout.setVisibility(View.GONE);


                                    Toast.makeText(getApplicationContext(), "User Deleted!!", Toast.LENGTH_LONG).show();
                                    id.setText("");


                                    root = null;


                                } else {
                                    relativelayout.setVisibility(View.GONE);

                                    Toast.makeText(getApplicationContext(), "Error While deleting user", Toast.LENGTH_LONG).show();

                                }

                            }
                        });
                    }

                }
            });


        }

    }

    private void comments() {
        if (value.matches(COMMENTS)) {

           Snackbar.make(relative,"Comming soon",Snackbar.LENGTH_LONG).show();

        }
    }

}