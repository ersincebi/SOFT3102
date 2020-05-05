package com.fmway.operations.admin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.fmway.R;
import com.fmway.models.user.User;
import com.fmway.models.user.UserParseDefinitions;
import com.fmway.models.user.UserTypes;
import com.fmway.operations.commonActivities.SignUpLoginActivity;
import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class IssueListActivity extends AppCompatActivity {


    ListView  listView;
    ArrayList<String> usernamesFromParse;
    ArrayList<String> userCommentsFromParse;
    ArrayList<Bitmap> userImageFromParse;
    PostActivityIssueList postActivityIssueList;





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);

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
        setContentView(R.layout.activity_driverlicenselist);  // aynı customView mantığı

        listView = findViewById(R.id.listView);

        usernamesFromParse = new ArrayList<>();
        userCommentsFromParse = new ArrayList<>();
        userImageFromParse = new ArrayList<>();

        postActivityIssueList = new PostActivityIssueList (usernamesFromParse,userCommentsFromParse,userImageFromParse,this);
        listView.setAdapter(postActivityIssueList);

        download();


    }

    public void download(){

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Issues");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e!=null){
                    Toast.makeText(getApplicationContext()
                            ,e.getLocalizedMessage()
                            ,Toast.LENGTH_LONG).show();

                }else{

                    if(objects.size()>0){
                        for(final ParseObject object: objects){

                            ParseFile parseFile = (ParseFile) object.get("image");

                            parseFile.getDataInBackground(new GetDataCallback() {
                                @Override
                                public void done(byte[] data, ParseException e) {
                                    if (e == null && data != null){

                                        Bitmap bitmap = BitmapFactory.decodeByteArray(data,0,data.length);

                                        userImageFromParse.add(bitmap);
                                        usernamesFromParse.add(object.getString("username"));
                                        userCommentsFromParse.add(object.getString("upload"));
                                        postActivityIssueList.notifyDataSetChanged();

                                    }
                                }
                            });




                        }
                    }
                }
            }


        });
    }


}

