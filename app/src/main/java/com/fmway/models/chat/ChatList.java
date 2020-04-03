package com.fmway.models.chat;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fmway.R;

import java.util.ArrayList;

public class ChatList  extends ArrayAdapter<Chat> {
    private ArrayList<Chat> messageList;

    private Activity context;

    private TextView messageBody;

    public ChatList(ArrayList<Chat> messageList
                    , Activity context){
        super(context, 0, messageList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.customview_listtrips, parent, false);
        }

        // Lookup view for data population
        messageBody = (TextView) convertView.findViewById(R.id.messageBody);

        // Populate the data into the template view using the data object
        messageBody.setText(messageList.get(position).getBody());

        // Return the completed view to render on screen
        return convertView;
    }
}
