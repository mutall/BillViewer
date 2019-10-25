package com.mutall.billviewer.Activity;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mutall.billviewer.Adapter.SmsAdapter;
import com.mutall.billviewer.Model.Sms;
import com.mutall.billviewer.R;
import com.mutall.billviewer.Util.Constants;
import java.util.List;

public class ListFragment extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "ListFragment";
    List<Sms> smsList;
    RecyclerView recyclerView;
    Button button;
    String button_name;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        button = findViewById(R.id.button);

        if(getIntent().hasExtra(Constants.SMS_LIST)){
            smsList = getIntent().getParcelableArrayListExtra(Constants.SMS_LIST);
            Log.d(TAG, "onCreate: "+smsList.toString());
        }
        button_name = getIntent().getStringExtra(Constants.BUTTON);
        button.setText(button_name);
        button.setOnClickListener(this);
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        SmsAdapter adapter = new SmsAdapter(smsList);
        recyclerView.setAdapter(adapter);

     }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick: "+button_name);
        switch (button_name){
            case "upload":
                Log.d(TAG, "onClick: uploaded data");
                break;
            case "send":
                Log.d(TAG, "onClick: data sent");
                break;
            default:
                this.finish();
        }
    }
}