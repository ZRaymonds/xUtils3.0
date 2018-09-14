package com.example.xutils.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.xutils.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

@ContentView(R.layout.activity_database)
public class DatabaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }
}
