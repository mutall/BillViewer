package com.mutall.billviewer.Activity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mutall.billviewer.Adapter.CardAdapter;
import com.mutall.billviewer.Model.CardItem;
import com.mutall.billviewer.R;
import com.mutall.billviewer.Util.Constants;
import com.mutall.billviewer.Util.SmsThread;

import java.util.List;

public class ListFragment extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "ListFragment";
    List<CardItem> cardItemList;
    RecyclerView recyclerView;
    Button button;
    String button_name;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        button = findViewById(R.id.button);

        if(getIntent().hasExtra(Constants.SMS_LIST)){
            cardItemList = getIntent().getParcelableArrayListExtra(Constants.SMS_LIST);
            Log.d(TAG, "onCreate: "+cardItemList.toString());
        }
        button_name = getIntent().getStringExtra(Constants.BUTTON);
        button.setText(button_name);
        button.setOnClickListener(this);
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        CardAdapter adapter = new CardAdapter(cardItemList);
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
//                SmsThread thread = new SmsThread(list, handler);
//                    thread.run();

                break;
            default:
                this.finish();
        }
    }
}