package com.fmway.operations.admin;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.fmway.R;
import com.fmway.models.user.User;
import com.fmway.models.user.UserParseDefinitions;
import com.fmway.operations.commonActivities.SignUpLoginActivity;
import com.parse.FindCallback;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class ListUserActivityAdmin extends AppCompatActivity {

    private ListView listView;
    private ArrayList<User> userList;
    private String selected=null;


    private PostActivityAdminUser PostActivityAdminUser;

    private UserParseDefinitions definitions = new UserParseDefinitions();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.Logout) {
            ParseUser.logOutInBackground(new LogOutCallback() {
                @Override
                public void done(ParseException e) {
                    if (e != null) {
                        Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    } else {
                        Intent intent = new Intent(getApplicationContext(), SignUpLoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listuser_activity);

        listView = findViewById(R.id.listUserList);


        userList= new ArrayList<>();

        PostActivityAdminUser= new PostActivityAdminUser(userList,this);

        download();
        listView.setAdapter(PostActivityAdminUser);

    }
    public void download(){
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if(e!=null){
                    Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show();

                }else{

                    if(objects.size()>0){
                        for(ParseObject object: objects){

                            userList.add(new User(
                                    object.getObjectId()
                                    ,object.getString(definitions.getCreatedAtKey())
                                    ,object.getString(definitions.getNameKey())
                                    ,object.getString(definitions.getSurnameKey())
                                    ,object.getString(definitions.getUsernameKey())
                                    ,object.getString(definitions.getEmailKey())
                            ));

                            PostActivityAdminUser.notifyDataSetChanged();
                        }
                    }
                }
            }


        });
    }

}
