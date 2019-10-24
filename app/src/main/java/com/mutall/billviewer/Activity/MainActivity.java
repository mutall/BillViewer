package com.mutall.billviewer.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.mutall.billviewer.Activity.ListFragment;
import com.mutall.billviewer.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    Toolbar toolbar;
    Button request,inbox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        request = findViewById(R.id.request);
        inbox = findViewById(R.id.inbox);
        request.setOnClickListener(this);
        inbox.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.request:
                Log.d(TAG, "onClick: request clicked");
            break;
            case R.id.inbox:
                Log.d(TAG, "onClick: inbox clicked");
            break;
        }
    }
}
