package com.fmway.operations.commonActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fmway.R;
import com.fmway.models.user.User;
import com.fmway.models.user.UserParseDefinitions;
import com.parse.FindCallback;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class Profile extends AppCompatActivity {
    private TextView fullName;
    private TextView role;
    private TextView balance;

    private String userID;

    private UserParseDefinitions definitions = new UserParseDefinitions();
    private User user;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        fullName = findViewById(R.id.fullName);
        role = findViewById(R.id.role);
        balance = findViewById(R.id.balance);

        Intent intent= getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle!=null)
            userID = (String) bundle.get("userID");

        getUser();
    }

    /**
     * Fills users info into page
     */
    public void getUser(){
        ParseQuery<ParseUser> parseQuery = ParseUser.getQuery();

        parseQuery.whereEqualTo(definitions.getObjectIdKey(), userID);

        parseQuery.findInBackground(
            new FindCallback<ParseUser>() {
                @Override
                public void done(List<ParseUser> objects, ParseException e) {
                    if(e!=null){
                        Toast.makeText(getApplicationContext()
                                ,e.getLocalizedMessage()
                                ,Toast.LENGTH_LONG).show();
                    }else{
                        if(objects.size()>0){
                            fullName.setText(
                                    objects.get(0).get(definitions.getNameKey()) + " " +
                                            objects.get(0).get(definitions.getSurnameKey())
                            );
                            role.setText(
                                    objects.get(0).get(definitions.getUserTypeKey()) + ""
                            );
                            balance.setText(
                                    objects.get(0).get(definitions.getBalanceKey()) + " ₺"
                            );
                        }
                    }
                }
            }
        );
    }

    /**
     * button action for opening payment activity
     * @param view
     */
    public void balance(View view){
        Intent intent = new Intent(getApplicationContext(), Payment.class);
        intent.putExtra("userID",userID);
        startActivity(intent);
    }

    /**
     * button action for opening payment activity
     * @param view
     */
    public void myJoinedTrips(View view){
        Intent intent = new Intent(getApplicationContext(), listMyTrips.class);
        intent.putExtra("userID", userID);
        startActivity(intent);
    }
}
