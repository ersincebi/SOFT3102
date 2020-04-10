package com.fmway.tripOperations;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.fmway.R;
import com.fmway.models.chat.ChatList;
import com.fmway.models.chat.Chat;
import com.fmway.models.chat.ChatParseDefinitions;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

public class tripChat extends AppCompatActivity {

    private String tripId = "";
    private String personId = "";

    private ListView discussionList;
    private EditText message;
    private Button send;

    private ArrayList<Chat> adapterMessageList;

    private ChatParseDefinitions definitions = new ChatParseDefinitions();
    private ChatList chatList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_chat);

        tripId = getIntent().getStringExtra("tripId");
        personId = getIntent().getStringExtra("personId");

        adapterMessageList = new ArrayList<>();

        send = (Button)findViewById(R.id.send);
        discussionList = (ListView)findViewById(R.id.discussionList);
        message = (EditText)findViewById(R.id.message);

        chatList = new ChatList(adapterMessageList, this);
        discussionList.setAdapter(chatList);

        listMessages(tripId);
    }

    public void sendMessage(View view){
        String messageFromUser = String.valueOf(message.getText());

        if(messageFromUser.equals("")){
            Toast.makeText(getApplicationContext(), "This field cannot be empty!", Toast.LENGTH_LONG).show();
        }
        else {
            ParseObject parseObject = new ParseObject(definitions.getClassName());

            parseObject.put(definitions.getTripIdKey(), tripId);
            parseObject.put(definitions.getPersonIdKey(), personId);
            parseObject.put(definitions.getMessageKey(), messageFromUser);

            parseObject.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e != null) {
                        Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Message sent.", Toast.LENGTH_LONG).show();

                        adapterMessageList.clear();

                        listMessages(tripId);
                    }
                }
            });
        }
    }

    public void listMessages(String takeTripId){
        ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery(definitions.getClassName());

        parseQuery.whereEqualTo(definitions.getTripIdKey(), takeTripId);

        parseQuery.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    if(e!=null){
                        Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                    }else{
                        if(objects.size()>0){
                            for(ParseObject object: objects){
                                adapterMessageList.add(new Chat(object.getString(definitions.getTripIdKey())
                                                                ,object.getString(definitions.getPersonIdKey())
                                                                ,object.getString(definitions.getMessageKey())));

                                chatList.notifyDataSetChanged();
                            }
                        }
                    }
                }
            }
        );
    }
}
