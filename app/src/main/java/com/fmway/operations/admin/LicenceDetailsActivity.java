package com.fmway.operations.admin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fmway.R;
import com.fmway.operations.commonActivities.SignUpLoginActivity;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class LicenceDetailsActivity extends AppCompatActivity {


    private Bitmap userImage;
    private ImageView uImage;
    private Button approveButton;
    private Button declineButton;

    private String objectID;
    private String userID;

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

    /**
     * passenger add trip page
     * activity constructor
     * also takes objectID by intent
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.licence_details);

        Intent iin = getIntent();
        Bundle b = iin.getExtras();

        if (b != null) {
            objectID = (String) b.get("objectID");
            userID = (String) b.get("userID");
        }


        uImage = findViewById(R.id.customview_imageView);
        approveButton = findViewById(R.id.approveButton);
        declineButton = findViewById(R.id.declineButton);

        downloadImage();

        approveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               ParseQuery<ParseObject> query= ParseQuery.getQuery("Licences");
               query.whereEqualTo("userID",userID);
               query.getInBackground(objectID, new GetCallback<ParseObject>() {
                   @Override
                   public void done(ParseObject object, ParseException e) {
                       object.put("isApproved","true");

                       object.saveInBackground(new SaveCallback() {
                           @Override
                           public void done(ParseException e) {
                               if (e != null) {
                                   Toast.makeText(getApplicationContext()
                                           ,e.getLocalizedMessage()
                                           ,Toast.LENGTH_LONG).show();

                               } else {
                                   Toast.makeText(getApplicationContext()
                                           ,"Usser edited."
                                           ,Toast.LENGTH_LONG).show();
                                   Intent intent = new Intent(getApplicationContext()
                                           ,AdminActivity.class);
                                   intent.putExtra("userID",userID);
                                   startActivity(intent);
                               }
                           }
                       });
                   }
               });

                }



            });



        declineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                startActivity(intent);
            }
        });



    }


    public void downloadImage() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Licences");
        query.whereEqualTo("userID", userID);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    for (ParseObject object : objects) {
                        Log.i("info", "image found!");
                        ParseFile file = (ParseFile) object.get("image");
                        file.getDataInBackground(new GetDataCallback() {
                            @Override
                            public void done(byte[] data, ParseException e) {
                                if (e == null) {
                                    Bitmap bitmapImage = BitmapFactory.decodeByteArray(data, 0, data.length);
                                    ImageView image = new ImageView(getApplicationContext());
                                    uImage.setImageBitmap(bitmapImage);

                                } else {
                                    Log.i("info", e.getMessage());
                                }
                            }
                        });
                    }
                }
            }
        });
    }


}
