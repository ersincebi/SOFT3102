package com.fmway.operations.commonActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fmway.R;
import com.fmway.models.trip.TripParseDefinitions;
import com.fmway.models.user.User;
import com.fmway.models.user.UserParseDefinitions;
import com.fmway.models.user.UserTypes;
import com.fmway.models.user.passenger.RatingParseDefinitions;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class Profile extends AppCompatActivity {
    private ImageView profileImage;
    private TextView fullName;
    private TextView role;
    private TextView balance;

    private String userID;
    private String userRole;

    private TripParseDefinitions tripParseDefinitions = new TripParseDefinitions();
    private RatingParseDefinitions ratingParseDefinitions = new RatingParseDefinitions();
    private UserParseDefinitions definitions = new UserParseDefinitions();
    private User user;
    private UserTypes userTypes;
    private float rating = 0;
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

        profileImage = findViewById(R.id.profileImage);
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
        parseQuery.getInBackground(userID, new GetCallback<ParseUser>() {
            @Override
            public void done(ParseUser object, ParseException e) {
                if (e != null) {
                    Toast.makeText(getApplicationContext()
                            ,e.getLocalizedMessage()
                            ,Toast.LENGTH_LONG).show();
                } else {
                    byte [] imageData = object.getBytes(definitions.getImageKey());
                    if(imageData != null){
                        Bitmap bitmap = BitmapFactory.decodeByteArray(imageData
                                                                        ,0
                                                                        ,imageData.length);
                        Bitmap emptyBitmap = Bitmap.createBitmap(bitmap.getWidth()
                                                                    ,bitmap.getHeight()
                                                                    ,bitmap.getConfig());

                        if(!bitmap.sameAs(emptyBitmap))
                            profileImage.setImageBitmap(
                                    bitmap
                            );
                    }
                    userRole = object.getString(definitions.getUserTypeKey());
                    fullName.setText(
                            object.get(definitions.getNameKey()) + " " +
                                    object.get(definitions.getSurnameKey())
                    );
                    role.setText(
                            userRole
                    );
                    balance.setText(
                            object.get(definitions.getBalanceKey()) + " ₺"
                    );

                    if(userRole.equals(userTypes.DRIVER.getUserType()))
                        getUserRating();
                    }
                }
            }
        );
    }

    /**
     * find all trips created by users
     */
    public void getUserRating(){
        ParseQuery<ParseObject> parseQuery =
                ParseQuery.getQuery(tripParseDefinitions.getClassName());

        parseQuery.whereEqualTo(tripParseDefinitions.getTripCreatedByKey(), userID);

        parseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e!=null){
                    Toast.makeText(getApplicationContext()
                            ,e.getLocalizedMessage()
                            ,Toast.LENGTH_LONG).show();
                }else{
                    if(objects.size()>0){
                        for (ParseObject item: objects) {
                            calculateRating(item.getObjectId());
                        }

                        role.setText(
                                role.getText() + " Rating: " + rating
                        );
                    }
                }
            }
        });
    }

    /**
     * find and calculate all the rating
     * @return
     */
    public void calculateRating(String tripID){
        ParseQuery<ParseObject> parseQuery =
                ParseQuery.getQuery(ratingParseDefinitions.getClassName());

        parseQuery.whereEqualTo(ratingParseDefinitions.getUserIdKey(), tripID);

        parseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e!=null){
                    Toast.makeText(getApplicationContext()
                            ,e.getLocalizedMessage()
                            ,Toast.LENGTH_LONG).show();
                }else{
                    int len = objects.size();
                    if(objects.size()>0){
                        int sum = 0;
                        for (ParseObject item: objects) {
                            sum += item.getDouble(ratingParseDefinitions.getRatingValueKey());
                        }
                        rating += sum / len;
                    }
                }
            }
        });
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

    /**
     * button action for opening settings activity
     * @param view
     */
    public void openSettings(View view){
        Intent intent = new Intent(getApplicationContext(), profileSettings.class);
        intent.putExtra("userID", userID);
        startActivity(intent);
    }
}
