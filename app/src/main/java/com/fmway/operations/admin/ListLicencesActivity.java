package com.fmway.operations.admin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fmway.R;
import com.fmway.operations.commonActivities.SignUpLoginActivity;
import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class ListLicencesActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> objectIDFromParse;
    ArrayList<String> usernamesFromParse;
    ArrayList<String> userCommentsFromParse;
    ArrayList<String> userIDFromParse;
    private String objectID;
    private String userID;
    PostActivityListLicence postActivityLicenseList;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    /**
     * logout button activity
     *
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
                                , e.getLocalizedMessage()
                                , Toast.LENGTH_LONG).show();
                    } else {
                        Intent intent = new Intent(getApplicationContext()
                                , SignUpLoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.list_licence_activity);

        listView = findViewById(R.id.listLicencesList);

        objectIDFromParse=new ArrayList<>();
        userIDFromParse=new ArrayList<>();
        usernamesFromParse = new ArrayList<>();
        userCommentsFromParse = new ArrayList<>();

        postActivityLicenseList = new PostActivityListLicence(usernamesFromParse,objectIDFromParse,userIDFromParse,userCommentsFromParse,this);
        listView.setAdapter(postActivityLicenseList);


        download();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                objectID = ((TextView) view.findViewById(R.id.customview_objectID)).getText().toString();
                userID=((TextView) view.findViewById(R.id.customview_userID)).getText().toString();

                Intent myIntent= new Intent(ListLicencesActivity.this
                        ,LicenceDetailsActivity.class);
                myIntent.putExtra("objectID", objectID);
                myIntent.putExtra("userID",userID);
                startActivity(myIntent);
            }
        });



    }
    public void download(){

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Licences");  //license yazılacakmış db böyle açılmış.
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e != null) {
                    Toast.makeText(getApplicationContext()
                            , e.getLocalizedMessage()
                            , Toast.LENGTH_LONG).show();

                } else {
                    if (objects.size() > 0) {
                        for (ParseObject object : objects) {


                            objectIDFromParse.add(object.getObjectId());
                            usernamesFromParse.add(object.getString("username"));
                            userCommentsFromParse.add(object.getString("upload"));
                            userIDFromParse.add(object.getString("userID"));


                            postActivityLicenseList.notifyDataSetChanged();

                        }
                    }
                }
            }
                            });




                        }

                    }
