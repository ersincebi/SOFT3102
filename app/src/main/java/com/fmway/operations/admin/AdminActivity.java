package com.fmway.operations.admin;

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

import androidx.appcompat.app.AppCompatActivity;

 public class AdminActivity extends AppCompatActivity {

    Button editUserButton;
    Button editTripsButton;
    Button addAdminButton;
    Button approveDriversButton;


     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
         MenuInflater menuInflater = getMenuInflater();
         menuInflater.inflate(R.menu.main_menu,menu);

         return super.onCreateOptionsMenu(menu);
     }
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
        setContentView(R.layout.activity_admin);


        editTripsButton=findViewById(R.id.editTripsButton);
        editUserButton=findViewById(R.id.editProfilesButton);
        addAdminButton=findViewById(R.id.addAdminButton);
        approveDriversButton=findViewById(R.id.approveDriverButton);


    }

    public void editTrips(View view){
        Intent intent = new Intent(getApplicationContext(), ListTripActivityAdmin.class);
        startActivity(intent);
    }

     public void editUser(View view){
         Intent intent = new Intent(getApplicationContext(), ListUserActivityAdmin.class);
         startActivity(intent);
     }
     public void addAdmin(View view){
         Intent intent = new Intent(getApplicationContext(), AddAdminActivity.class);
         startActivity(intent);
     }



}
