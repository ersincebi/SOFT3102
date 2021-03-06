package com.fmway.operations.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fmway.R;
import com.fmway.models.user.UserParseDefinitions;
import com.fmway.models.user.UserTypes;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseRole;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import androidx.appcompat.app.AppCompatActivity;

public class AddAdminActivity extends AppCompatActivity {
    private EditText nameText, surnameText, emailText , phoneText, usernameText, passwordText;
    private Button addAdminButton;
    private ParseUser user;
    private UserParseDefinitions definitions = new UserParseDefinitions();
    private UserTypes roles;


    /**
     * activity constructor
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addadmin);

        nameText = findViewById(R.id.addAdminName);
        surnameText = findViewById(R.id.addAdminSurname);
        emailText = findViewById(R.id.addAdminEmail);
        phoneText = findViewById(R.id.addAdminPhone);
        usernameText = findViewById(R.id.addAdminUsername);
        passwordText = findViewById(R.id.addAdminPassword);
        addAdminButton=findViewById(R.id.addAdminButton);


    }


    /**
     * Button action for admin user creation
     * @param view
     */
    public void addAdminAccount (View view) {

        user = new ParseUser();


        addUserToDb(
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
        roleACL.setPublicWriteAccess(true);
        roleACL.setRoleWriteAccess("User",true);
        ParseRole role = new ParseRole("Admin", roleACL);
        role.saveInBackground();
        ParseUser[] usersToAddRole = new ParseUser[1];
        usersToAddRole[0] = user;
        user.setACL(roleACL);
        role.getUsers().add(user);
        for(ParseUser user : usersToAddRole){
            role.getUsers().add(user);
        }
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
                                    ,"New admin account is created!"
                                    ,Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    /**
     * this class helps to create user creation on database
     * its created separately because for testing
     *
     * @param user variable is for the ParseUser class
     * @param name
     * @param surname
     * @param email
     * @param phone
     * @param username
     * @param password
     */
    public void addUserToDb(
            ParseUser user
            ,String name
            ,String surname
            ,String email
            ,String phone
            ,String username
            ,String password
    ){
        user.put(definitions.getNameKey(),name);
        user.put(definitions.getSurnameKey(),surname);
        user.put(definitions.getEmailKey(),email);
        user.put(definitions.getPhoneKey(),phone);
        user.setUsername(username);
        user.setPassword(password);
        user.put(definitions.getUserTypeKey(),roles.ADMIN.getUserType());
    }
}

