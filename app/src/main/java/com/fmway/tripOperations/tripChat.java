package com.fmway.tripOperations;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.fmway.R;
import com.fmway.libs.chat.Chat;
import com.fmway.libs.chat.ParseChat;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;

public class tripChat extends AppCompatActivity {

    private ListView discussionList;
    private EditText message;
    private Button send;

    private List<ParseObject> messageList;
    private ArrayList<Chat> adapterMessageList;

    private ParseChat parseChat = new ParseChat();
    private Chat chat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_chat);

        final String tripId = "123";
        final String userId = "123";

        discussionList = (ListView)findViewById(R.id.discussionList);
        discussionList.setAdapter(chat);

        message = (EditText)findViewById(R.id.message);

        send = (Button)findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageFromUser = String.valueOf(message.getText());

                parseChat.setTripId(tripId);
                parseChat.setUserId(userId);
                parseChat.setMessage(messageFromUser);

                listMessages(tripId);
            }
        });
    }

    public void listMessages(String takeTripId){
        messageList = parseChat.getList(takeTripId);
        for (ParseObject listItem: messageList) {
            adapterMessageList.add(new Chat(listItem.getString(parseChat.tripId)
                                            ,listItem.getString(parseChat.userId)
                                            ,listItem.getString(parseChat.message)
                                            ,this));

            chat.notifyDataSetChanged();
        }

    }
}
