package com.mutall.billviewer.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;
import com.mutall.billviewer.Activity.ListFragment;
import com.mutall.billviewer.Model.Sms;
import com.mutall.billviewer.R;
import com.mutall.billviewer.Util.Constants;
import com.mutall.billviewer.Util.SmsThread;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    Toolbar toolbar;
    Button request, inbox;
    String[] dialog_items;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.MY_PERMISSIONS_REQUEST_READ_SMS || requestCode == Constants.MY_PERMISSIONS_REQUEST_SEND_SMS) {
            if (resultCode == RESULT_CANCELED) {
                try {
                    showSnack("Cant use without sms permission");
                    wait(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    this.finish();
                }

            }
        }
    }

    private void showSnack(String message) {
        Snackbar.make(findViewById(R.id.coordinator), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        request = findViewById(R.id.request);
        inbox = findViewById(R.id.inbox);
        request.setOnClickListener(this);
        inbox.setOnClickListener(this);
        dialog_items = getResources().getStringArray(R.array.dialog_items);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.request:
                Log.d(TAG, "onClick: request clicked");
                SmsThread thread = new SmsThread();
                thread.run();
                break;
            case R.id.inbox:
                showInbox();
                break;
        }
    }

    private void showInbox() {
        Log.d(TAG, "onClick: inbox clicked");
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.dialog_title);
        dialog.setItems(R.array.dialog_items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String num = dialog_items[i];
                List array = getInboxMessages(num);
                Intent intent = new Intent(MainActivity.this, ListFragment.class);
                intent.putParcelableArrayListExtra(Constants.SMS_LIST, (ArrayList<? extends Parcelable>) array);
                intent.putExtra(Constants.BUTTON, "upload");
                startActivity(intent);
                Log.d(TAG, "onClick: " + array.toString());
            }
        });
        dialog.show();
    }

    private List<Sms> getInboxMessages(String number) {
        Log.d(TAG, "getInboxMessages: start method");
        List<Sms> array = new ArrayList<>();
        //this is the query string passed to the URI
        final String inboxQueryString = "content://sms/inbox";

        //Create an instance of URI passing in the querystring
        Uri uri = Uri.parse(inboxQueryString);

        //get selection arguments
        String[] selectionArgs = new String[]{"address", "body", "date"};
        String whereClause = "\'" + number + "\'";

        //get the cursor using the uri and selection args
        Cursor cursor = getContentResolver().query(uri, selectionArgs, "address=" + whereClause, null, null);
        cursor.moveToFirst();
        try {
            do {
                String num = cursor.getString(cursor.getColumnIndex("address"));
                String body = cursor.getString(cursor.getColumnIndex("body"));
                Sms sms = new Sms(num, body);
                array.add(sms);

            } while (cursor.moveToNext());
        } catch (CursorIndexOutOfBoundsException e) {
            e.printStackTrace();
            showSnack("Number not found");
        }
        return array;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_SMS},
                    Constants.MY_PERMISSIONS_REQUEST_READ_SMS);
        }


        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    Constants.MY_PERMISSIONS_REQUEST_SEND_SMS);
        }

    }
}
