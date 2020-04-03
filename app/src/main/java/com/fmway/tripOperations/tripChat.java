package com.fmway.tripOperations;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.fmway.R;
import com.fmway.models.chat.ChatList;
import com.fmway.models.chat.Chat;
import com.fmway.models.chat.ParseChat;
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
    private ChatList chatList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_chat);

        final String tripId = "123";
        final String userId = "123";

        discussionList = (ListView)findViewById(R.id.discussionList);

        chatList = new ChatList(adapterMessageList, this);
        discussionList.setAdapter(chatList);

        message = (EditText)findViewById(R.id.message);

        listMessages(tripId);

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
        adapterMessageList = null;
        if(messageList.size()>0){
            for (ParseObject listItem: messageList) {
                adapterMessageList.add(new Chat(listItem.getString(parseChat.tripId)
                                                ,listItem.getString(parseChat.userId)
                                                ,listItem.getString(parseChat.message)));

                chatList.notifyDataSetChanged();
            }
        }
    }
}
