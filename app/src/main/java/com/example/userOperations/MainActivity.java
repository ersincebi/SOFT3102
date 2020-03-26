
/*

package com.example.signuplogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);


        //database-------------------------------------------------------------

   /*     ParseObject object = new ParseObject("Passenger");

        object.put("name","Emre Can");
        object.put("surname","Rua");

        object.saveInBackground(new SaveCallback() {

            @Override
            public void done(ParseException e) {
                if (e!=null){
                    Toast.makeText(getApplicationContext(), e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Passenger kaydedildi !",Toast.LENGTH_LONG).show();
                }
            }
        });

*/
/*
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Passenger");
        query.getInBackground("MQdCMqvuzD", new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e!=null){
                      e.printStackTrace();
                    }else {
                       String passangerName = object.getString("name");
                       String passangerSurname=object.getString("surname");

                    System.out.println("Passanger name: "+ passangerName );
                    System.out.println("Passanger surname: "+ passangerSurname );
}
    }
});
*/


            //user---------------------------------------(passanger, driver veya admin)

        /*
        ParseUser user = new ParseUser();
        user.setUsername("emrecanrua1 ");
        user.setPassword("şifre1234");

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e!=null){
                    e.printStackTrace();
                }else {
                    Toast.makeText(MainActivity.this, "Yeni Kullanıcı Oluşturuldu!", Toast.LENGTH_SHORT).show();
                }
            }
        });
*/
/*
ParseUser.logInInBackground("emrecanrua1 ", "şifre1234", new LogInCallback() {
    @Override
    public void done(ParseUser user, ParseException e) {
if (e!=null){
    e.printStackTrace();
    Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();  //geçersiz isim şifre
}else{
    Toast.makeText(MainActivity.this, "Giriş Yapıldı -> "+ user.getUsername(),Toast.LENGTH_SHORT).show();
}
    }
});

*/



/*
    }
}       */