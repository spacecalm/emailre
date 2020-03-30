package com.example.emailre;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.emailre.data.MessageManager;

import javax.mail.Message;

public class MessageListActivity extends AppCompatActivity {
private static ListView list;
String[] headers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);
        list = findViewById(R.id.InboxList);
        CreateList();
    }
    public void CreateList(){
        ListAdapter adapter = new ArrayAdapter<String>(this, R.layout.simple_list_item_1, MessageManager.headers);
        list.setAdapter(adapter);

    }
}
