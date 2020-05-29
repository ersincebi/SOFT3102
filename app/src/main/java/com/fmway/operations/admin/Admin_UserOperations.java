package com.fmway.operations.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fmway.R;
import com.fmway.operations.commonActivities.SignUpLoginActivity;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class Admin_UserOperations extends AppCompatActivity {
    private Button addAdminButton;
    private Button editUserButton;
    private Button approveDriversButton;
    private String userID;

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
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__user_operations);
        editUserButton=findViewById(R.id.editProfilesButton);
        addAdminButton=findViewById(R.id.addAdminButton);
        approveDriversButton=findViewById(R.id.approveDriverButton);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
            userID =(String) b.get("userID");
    }

    public void addDriver(View view){
        Intent intent = new Intent(getApplicationContext(), AddDriverActivity.class);
        startActivity(intent);
    }

    public void addAdmin(View view){
        Intent intent = new Intent(getApplicationContext(), AddAdminActivity.class);
        startActivity(intent);
    }

    public void editUser(View view){
        Intent intent = new Intent(getApplicationContext(), ListUserActivityAdmin.class);
        startActivity(intent);
    }
}
