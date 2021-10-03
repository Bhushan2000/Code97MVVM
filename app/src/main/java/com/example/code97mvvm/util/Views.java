package com.example.code97mvvm.util;


import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class Views {

    private static String TAG = AppCompatActivity.ACTIVITY_SERVICE;


    public static void showSnackBarLong(View v, String message) {
        Snackbar.make(v, message, Snackbar.LENGTH_LONG).show();

    }
    public static void showSnackBarShort(View v, String message) {
        Snackbar.make(v, message, Snackbar.LENGTH_SHORT).show();

    }
    public static void showToastLong(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
    public static void showToastShort(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}
