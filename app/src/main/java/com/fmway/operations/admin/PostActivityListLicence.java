package com.fmway.operations.admin;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fmway.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PostActivityListLicence extends ArrayAdapter<String> {

    private final ArrayList<String> username;
    private final ArrayList<String> objectID;
    private final ArrayList<String> userID;
    private final ArrayList<String> userComment;

    private final Activity context;

    public PostActivityListLicence (ArrayList<String> username,ArrayList<String> objectID,ArrayList<String> userID,ArrayList<String> userComment,Activity context) {


        super(context, R.layout.customview_list_licences,username);
        this.username=username;
        this.objectID=objectID;
        this.userID=userID;
        this.userComment=userComment;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View customView = layoutInflater.inflate(R.layout.customview_list_licences, null,true);
        TextView usernameText = customView.findViewById(R.id.customview_userName);
        TextView userIDText= customView.findViewById(R.id.customview_userID);
        TextView objectIdText=customView.findViewById(R.id.customview_objectID);
        TextView commentText = customView.findViewById(R.id.customview_comment);

        usernameText.setText(username.get(position));
        userIDText.setText(userID.get(position));
        objectIdText.setText(objectID.get(position));
        commentText.setText(userComment.get(position));


        return customView;
    }
}

