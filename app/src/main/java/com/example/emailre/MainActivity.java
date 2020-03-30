package com.example.emailre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.emailre.data.MessageManager;
import com.example.emailre.network.NetworkController;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Authenticator;
import javax.mail.Session;
import javax.mail.Store;

import java.util.Properties;


public class MainActivity extends AppCompatActivity {


    private TextView statusView;
    private Button loginButton;
    private EditText loginEdit, passwordEdit, serverEdit, portEdit;
    private Properties props;


    public String USERNAME = "lastestami@rambler.ru";
    public String PASSWORD = "monkeyJump144";
    public String HOST = "pop.rambler.ru";
    public String PORT = "995";

    Store store;
    Session session;
    Message[] messages;
    String[] headers;

    Folder inbox;

    String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        portEdit = findViewById(R.id.PortEdit);
        statusView = findViewById(R.id.ConnectStatus);
        loginButton = findViewById(R.id.LoginButton);
        loginEdit = findViewById(R.id.loginEdit);
        passwordEdit = findViewById(R.id.PasswordEdit);
        serverEdit = findViewById(R.id.HostEdit);
    }

    public void onLoginHandler(View view) {
        if (!loginEdit.getText().toString().isEmpty()) USERNAME = loginEdit.getText().toString();
        if (!passwordEdit.getText().toString().isEmpty())
            PASSWORD = passwordEdit.getText().toString();
        if (!portEdit.getText().toString().isEmpty()) PORT = portEdit.getText().toString();
        if (!serverEdit.getText().toString().isEmpty()) HOST = serverEdit.getText().toString();
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    store = NetworkController.OpenConnectionWithHostPOP3(HOST, PORT, USERNAME, PASSWORD);
                    inbox = store.getFolder("INBOX");
                    inbox.open(Folder.READ_ONLY);
                    messages = inbox.getMessages();
                    headers = new String[messages.length];
                    for (int i = 0; i < messages.length; i++) {
                        headers[i] = messages[i].getSubject().toString();
                    }
                    MessageManager.headers = headers;
                    Intent intent = new Intent("com.example.emailre.MessageListActivity");
                    startActivity(intent);
                } catch (Exception e) {
                    status = "Error Connect " + NetworkController.error;
                    e.printStackTrace();
                    statusView.setText(status);
                    return;
                }
            }
        }
        );
        thread.start();
    }

}

