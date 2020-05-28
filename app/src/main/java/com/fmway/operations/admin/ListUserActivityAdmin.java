package com.fmway.operations.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.fmway.R;
import com.fmway.models.user.User;
import com.fmway.models.user.UserParseDefinitions;
import com.fmway.models.user.admin.PostActivityAdminUser;
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

    private com.fmway.models.user.admin.PostActivityAdminUser PostActivityAdminUser;

    private UserParseDefinitions definitions = new UserParseDefinitions();

    /**
     * menu option creator handler
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    /**
     * logout button activity
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.Logout) {
            ParseUser.logOutInBackground(new LogOutCallback() {
                @Override
                public void done(ParseException e) {
                    if (e != null) {
                        Toast.makeText(getApplicationContext()
                                        ,e.getLocalizedMessage()
                                        ,Toast.LENGTH_LONG).show();
                    } else {
                        Intent intent = new Intent(getApplicationContext()
                                                    ,SignUpLoginActivity.class);
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

    /**
     * admin user listing page
     * activity constructor
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listuser_activity);

        listView = findViewById(R.id.listUserList);


        userList= new ArrayList<>();

        PostActivityAdminUser= new PostActivityAdminUser(userList,this);

        download();
        listView.setAdapter(PostActivityAdminUser);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent myIntent= new Intent(ListUserActivityAdmin.this
                                            ,UserDetailsAdminActivity.class);
                myIntent.putExtra(definitions.getObjectIdKey(), selected);
                startActivity(myIntent);
            }
        });
    }

    /**
     * searches for the trip details on database
     * and fills the fields
     */
    public void download(){
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
            if(e!=null){
                Toast.makeText(getApplicationContext()
                                ,e.getLocalizedMessage()
                                ,Toast.LENGTH_LONG).show();

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
