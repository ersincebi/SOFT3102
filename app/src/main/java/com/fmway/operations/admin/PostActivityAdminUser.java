package com.fmway.operations.admin;

import android.app.Activity;
import android.widget.ArrayAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fmway.R;

public class PostActivityAdminUser extends ArrayAdapter<String> {
    private final ArrayList<String> objectId;
    private final ArrayList<String> username;   //string
    private final ArrayList<String> Email;    //string
    private final ArrayList<String> Name;   //string
    private final ArrayList<String> Surname;    //string



    private final Activity context;

    public PostActivityAdminUser(ArrayList<String> objectId,ArrayList<String> username,ArrayList<String> Email, ArrayList<String> Name , ArrayList<String> Surname,
                         Activity context){


        super(context,R.layout.customview_listusers,objectId);

        this.objectId=objectId;
        this.username=username;
        this.Email=Email;
        this.Name=Name;
        this.Surname=Surname;

        this.context=context;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater=this.context.getLayoutInflater();
        View customView=layoutInflater.inflate(R.layout.customview_listusers,null,true);
        TextView objectIdText=customView.findViewById(R.id.customView_objectid);
        TextView usernameText=customView.findViewById(R.id.customView_username);
        TextView EmailText=customView.findViewById(R.id.customView_email);
        TextView NameText=customView.findViewById(R.id.customView_name);
        TextView SurnameText=customView.findViewById(R.id.customView_surname);


        objectIdText.setText(objectId.get(position));
        usernameText.setText(username.get(position));
        EmailText.setText(Email.get(position));
        NameText.setText(Name.get(position));
        SurnameText.setText(Surname.get(position));





        return customView;
    }
}
