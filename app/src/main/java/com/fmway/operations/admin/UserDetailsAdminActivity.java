package com.fmway.operations.admin;

        import android.app.Activity;
        import android.app.DatePickerDialog;
        import android.app.TimePickerDialog;
        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.DatePicker;
        import android.widget.TextView;
        import android.widget.TimePicker;
        import android.widget.Toast;

        import com.fmway.R;
        import com.fmway.operations.commonActivities.SignUpLoginActivity;
        import com.parse.DeleteCallback;
        import com.parse.FindCallback;
        import com.parse.GetCallback;
        import com.parse.LogOutCallback;
        import com.parse.ParseException;
        import com.parse.ParseObject;
        import com.parse.ParseQuery;
        import com.parse.ParseUser;
        import com.parse.SaveCallback;

        import androidx.appcompat.app.AppCompatActivity;

        import java.util.Calendar;
        import java.util.List;

public class UserDetailsAdminActivity extends AppCompatActivity {


    Button blockUserButton;

    TextView usernameText;
    TextView emailText;
    TextView nameText;
    TextView surnameText;
    Context context = this;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

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
        setContentView(R.layout.activity_userdetailsadmin);




        blockUserButton = findViewById(R.id.block_user_button);


        blockUserButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                ParseQuery<ParseUser> query = ParseUser.getQuery();


                query.findInBackground(new FindCallback<ParseUser>() {
                    @Override
                    public void done(final List<ParseUser> object, ParseException e) {
                        if (e == null) {

                            object.get(0).deleteInBackground(new DeleteCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if (e == null) {
                                        Toast.makeText(getApplicationContext(), "User blocked.", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                });
            }
        });

    }
}