package com.fmway.operations.commonActivities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.fmway.R;
import com.fmway.models.user.UserParseDefinitions;
import com.parse.GetCallback;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class profileSettings extends AppCompatActivity {
    private ImageView profileImage;
    private EditText name;
    private EditText surname;
    private EditText phone;

    private String userID;

    private Bitmap chosenImage;

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
     * @return returns the selected option item
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
     * persons trip listing page
     * activity constructor
     * also takes userID by intent
     * and makes button handling for
     * opening required trip
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);

        profileImage = findViewById(R.id.profileImage);
        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        phone = findViewById(R.id.phone);

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
                            , e.getLocalizedMessage()
                            , Toast.LENGTH_LONG).show();
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
                    name.setText(
                            object.getString(definitions.getNameKey())
                    );
                    surname.setText(
                            object.getString(definitions.getSurnameKey())
                    );
                    phone.setText(
                            object.getString(definitions.getPhoneKey())
                    );
                }
            }
        });
    }

    /**
     * open file choose file dialog
     * @param view
     */
    public void chooseImage (View view){
        Intent intent = new Intent (Intent.ACTION_PICK
                                    ,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,1);
    }

    /**
     * when activated takes filled form info
     *      and updates the database for desired user
     * @param view
     */
    public void updateProfile(View view){
        ParseQuery<ParseObject> query= ParseQuery.getQuery(definitions.getClassName());
        query.getInBackground(userID, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                chosenImage.compress(Bitmap.CompressFormat.PNG, 50,byteArrayOutputStream);
                byte[] bytes = byteArrayOutputStream.toByteArray();
                editUser(
                        object
                        ,new ParseFile (definitions.getImageKey(), bytes)
                        ,name.getText().toString()
                        ,surname.getText().toString()
                        ,phone.getText().toString()
                );
                object.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e != null) {
                            Toast.makeText(getApplicationContext()
                                            ,e.getLocalizedMessage()
                                            ,Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext()
                                            ,"Profile edited."
                                            ,Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }
                });
            }
        });
    }

    /**
     * this class helps to edit profile editing on database
     * its created separately because for testing
     *
     * @param object variable is inherits the ParseObject class
     * @param parseFile
     * @param name
     * @param surname
     * @param phone
     */
    public void editUser(
            ParseObject object
            ,ParseFile parseFile
            ,String name
            ,String surname
            ,String phone
    ){
        object.put(definitions.getImageKey(), parseFile);
        object.put(definitions.getNameKey(), name);
        object.put(definitions.getSurnameKey(), surname);
        object.put(definitions.getPhoneKey(), phone);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri imageData = data.getData();
            try {
                if (Build.VERSION.SDK_INT >= 28){
                    ImageDecoder.Source source = ImageDecoder
                            .createSource(this.getContentResolver()
                                            ,imageData);
                    chosenImage = ImageDecoder.decodeBitmap(source);
                    profileImage.setImageBitmap(chosenImage);
                }else {
                    chosenImage = MediaStore.Images.Media.getBitmap(this.getContentResolver()
                                                                    ,imageData);
                    profileImage.setImageBitmap(chosenImage);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
