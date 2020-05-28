package com.fmway.operations.commonActivities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.fmway.R;
import com.fmway.models.user.UserParseDefinitions;
import com.fmway.models.user.UserTypes;
import com.fmway.operations.admin.AdminActivity;
import com.fmway.operations.driver.DriverActivity;
import com.fmway.operations.passenger.PassengerActivity;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseRelation;
import com.parse.ParseRole;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

public class SignUpActivityMain extends AppCompatActivity {

    private EditText nameText, surnameText, emailText , phoneText, usernameText, passwordText;

    private UserParseDefinitions definitions = new UserParseDefinitions();

    private UserTypes roles;

    /**
     * activity constructor
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity_main);
        nameText = findViewById(R.id.user_name);
        surnameText = findViewById(R.id.user_surname);
        emailText = findViewById(R.id.user_email);
        phoneText = findViewById(R.id.user_phone);
        usernameText = findViewById(R.id.user_username);
        passwordText = findViewById(R.id.user_password);

    }

    /**
     * records the new user to the database
     * according to given information in the fields
     * and redirects to the activity according to user role
     * @param view
     */
    public void signUp (View view) {
        final ParseUser user = new ParseUser();
        userSignUp(
                user
                ,nameText.getText().toString()
                ,surnameText.getText().toString()
                ,emailText.getText().toString()
                ,phoneText.getText().toString()
                ,usernameText.getText().toString()
                ,passwordText.getText().toString()

        );
        ParseACL roleACL = new ParseACL();
        roleACL.setPublicReadAccess(true);
        ParseRole role = new ParseRole("User", roleACL);
        role.saveInBackground();
        ParseUser[] usersToAddRole = new ParseUser[1];
        usersToAddRole[0] = user;
        role.getUsers().add(user);
        role.add("users",user);
        role.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {

            }
        });
        role.saveInBackground();
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e!= null) {
                    Toast.makeText(getApplicationContext()
                                    ,e.getLocalizedMessage()
                                    ,Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext()
                                    ,"New User Created!"
                                    ,Toast.LENGTH_LONG).show();
                    String userType=user.getString(definitions.getUserTypeKey());
                    if (userType.equals(roles.ADMIN.getUserType())){
                        Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                        startActivity(intent);
                    } else if (userType.equals(roles.PASSENGER.getUserType())){
                        Intent intent = new Intent(getApplicationContext(), PassengerActivity.class);
                        startActivity(intent);
                    } else if (userType.equals(roles.DRIVER.getUserType())){
                        Intent intent = new Intent(getApplicationContext(), DriverActivity.class);
                        startActivity(intent);
                    }
                }
            }


        });
    }

    /**
     * this class helps to create user on database
     * its created separately because for testing
     * every user is given passenger role as default
     * @param user variable is inherits the ParseUser class
     * @param name
     * @param surname
     * @param email
     * @param phone
     * @param username
     * @param password
     */
    public void userSignUp(
            ParseUser user
            ,String name
            ,String surname
            ,String email
            ,String phone
            ,String username
            ,String password
    ){
        user.put(definitions.getNameKey(), name);
        user.put(definitions.getSurnameKey(), surname);
        user.setEmail(emailText.getText().toString());
        user.put(definitions.getPhoneKey(), phone);
        user.setUsername(username);
        user.setPassword(password);
        user.put(definitions.getUserTypeKey(), roles.PASSENGER.getUserType());
    }
}




