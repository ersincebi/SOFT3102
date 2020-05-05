package com.fmway.operations.admin;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fmway.R;

import java.util.ArrayList;


public class PostActivityIssueList extends ArrayAdapter<String> {

    private final ArrayList<String> username;
    private final ArrayList<String> userComment;
    private final ArrayList<Bitmap> userImage;
    private final Activity context;

    public PostActivityIssueList (ArrayList<String> username,ArrayList<String> userComment,ArrayList<Bitmap> userImage,Activity context) {


        super(context,R.layout.customview_driverlicense,username);
        this.username=username;
        this.userComment=userComment;
        this.userImage=userImage;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View customView = layoutInflater.inflate(R.layout.customview_driverlicense, null,true);
        TextView usernameText = customView.findViewById(R.id.customview_usernameText);
        TextView commentText = customView.findViewById(R.id.customview_commentText);
        ImageView imageView = customView.findViewById(R.id.customview_imageView);

        usernameText.setText(username.get(position));
        imageView.setImageBitmap(userImage.get(position));
        commentText.setText(userComment.get(position));


        return customView;
    }
}

