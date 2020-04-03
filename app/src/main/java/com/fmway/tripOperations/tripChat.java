package com.fmway.tripOperations;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.fmway.R;

public class tripChat extends AppCompatActivity {

    private ListView discussionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_chat);

        discussionList = (ListView)findViewById(R.id.discussionList);

    }
}
