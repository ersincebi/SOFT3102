package com.fmway.operations.admin;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
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

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

 public class AdminActivity extends AppCompatActivity {


    private Button editUserButton;
    private Button editTripsButton;
    private Button addAdminButton;
    private Button approveDriversButton;
    private String userID;
    private  Button userOperations;
    Context context = this;

     /**
      * menu option creator handler
      * @param menu
      * @return
      */
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
                                                    ,SignUpLoginActivity.class);
                         startActivity(intent);
                         finish();
                     }
                 }
             });
         }
         return super.onOptionsItemSelected(item);
     }

     /**
      * admin main page
      * activity constructor
      * also takes userID by intent
      * @param savedInstanceState
      */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        editTripsButton=findViewById(R.id.editTripsButton);
        editUserButton=findViewById(R.id.editProfilesButton);
        addAdminButton=findViewById(R.id.addAdminButton);
        approveDriversButton=findViewById(R.id.approveDriverButton);
        userOperations = findViewById(R.id.userOperations);


        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
            userID =(String) b.get("userID");

    }

     /**
      * button action for opening list trip activity
      * @param view
      */
    public void editTrips(View view){
        Intent intent = new Intent(getApplicationContext(), ListTripActivityAdmin.class);
        startActivity(intent);
    }

     /**
      * button action for opening list user activity
      * @param view
      */
     public void editUser(View view){
         Intent intent = new Intent(getApplicationContext(), ListUserActivityAdmin.class);
         startActivity(intent);
     }

     /**
      * button action for opening add admin activity
      * @param view
      */
     public void addAdmin(View view){
         Intent intent = new Intent(getApplicationContext(), AddAdminActivity.class);
         startActivity(intent);
     }

     /**
      * button action for opening add admin activity
      * @param view
      */
     public void addDriver(View view){
         Intent intent = new Intent(getApplicationContext(), AddDriverActivity.class);
         startActivity(intent);
     }



     /**
      * button action for opening add admin activity
      * @param view
      */
     public void listDriverLicense(View view){
         Intent intent = new Intent(getApplicationContext(), DriverLicenseListActivity.class);
         startActivity(intent);
     }

     public void listIssue(View view){
         Intent intent = new Intent(getApplicationContext(), IssueListActivity.class);
         startActivity(intent);
     }
     public void userOperations(View view){
         Intent intent = new Intent(getApplicationContext(), Admin_UserOperations.class);
         startActivity(intent);
     }


     public void listLicenceActivity(View view){
         Intent intent=new Intent(getApplicationContext(),ListLicencesActivity.class);
         startActivity(intent);
     }




     public boolean onKeyDown (int keyCode, KeyEvent event){
         if (keyCode == KeyEvent.KEYCODE_BACK){
             AlertDialog.Builder alert = new AlertDialog.Builder(context);
             alert.setTitle("Are you sure you want to end the session?")
                     .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialogInterface, int i) {
                             finish();
                         }

                     }).setNegativeButton ("No",new DialogInterface.OnClickListener(){

                 @Override
                 public void onClick(DialogInterface dialogInterface, int i) {
                     dialogInterface.cancel();
                 }

             }).create().show();
         }
         return super.onKeyDown(keyCode, event);
     }





}
