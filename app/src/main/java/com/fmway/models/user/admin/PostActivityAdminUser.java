package com.fmway.models.user.admin;

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
import com.fmway.models.user.User;

public class PostActivityAdminUser extends ArrayAdapter<User> {
    private final ArrayList<User> userList;

    private final Activity context;

    /**
     * takes the user type array list and lists the users
     * @param userList the arraylist contains all the data for populating
     * @param context
     */
    public PostActivityAdminUser(ArrayList<User> userList
                                ,Activity context){
        super(context,R.layout.customview_listusers,userList);
        this.userList=userList;
        this.context=context;
    }

    /**
     * instant interacting with the user interface
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=this.context.getLayoutInflater();
        View customView=layoutInflater.inflate(R.layout.customview_listusers,null,true);

        // Lookup view for data population
        TextView objectIdText=customView.findViewById(R.id.customView_objectid);
        TextView usernameText=customView.findViewById(R.id.customView_username);
        TextView EmailText=customView.findViewById(R.id.customView_email);
        TextView NameText=customView.findViewById(R.id.customView_name);
        TextView SurnameText=customView.findViewById(R.id.customView_surname);

        // Populate the data into the template view using the data object
        objectIdText.setText(userList.get(position).getObjectId());
        usernameText.setText(userList.get(position).getUsername());
        EmailText.setText(userList.get(position).getEmail());
        NameText.setText(userList.get(position).getName());
        SurnameText.setText(userList.get(position).getSurname());

        // Return the completed view to render on screen
        return customView;
    }
}
