package com.fmway.operations.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fmway.R;

public class Admin_UserOperations extends AppCompatActivity {
    private Button addAdminButton;
    private Button editUserButton;
    private Button approveDriversButton;
    private String userID;

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
