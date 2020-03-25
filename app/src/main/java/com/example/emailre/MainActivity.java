package com.example.emailre;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import javax.mail.Folder;
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
        if (!passwordEdit.getText().toString().isEmpty()) PASSWORD = passwordEdit.getText().toString();
        if (!portEdit.getText().toString().isEmpty()) PORT = portEdit.getText().toString();
        if (!serverEdit.getText().toString().isEmpty()) HOST = serverEdit.getText().toString();
        new AsyncRequestLoginPOP3().execute();
    }

    class AsyncRequestLoginPOP3 extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... arg) {
            String status;
            try {


                Properties props = new Properties();
                props.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                props.put("mail.pop3.socketFactory.fallback", "false");
                props.put("mail.pop3.socketFactory.port", PORT);
                props.put("mail.pop3.port", PORT);
                props.put("mail.pop3.host", HOST);
                props.put("mail.store.protocol", "pop3");
                Authenticator auth = new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USERNAME, PASSWORD);
                    }
                };
                session = Session.getDefaultInstance(props, auth);
                store = session.getStore();
                store.connect();
                status = "Connect is " + store.isConnected();
                    Folder inbox = store.getFolder("INBOX");
                    inbox.open(Folder.READ_ONLY);
                status += " Message Count -  " + inbox.getMessageCount() + ":::";
                inbox.close(false);
                store.close();
            } catch (Exception e) {
                status = "Error Connect " + e.getStackTrace();
                e.printStackTrace();
            }

            return status;
        }

        @Override
        protected void onPostExecute(String s) {
            statusView.setText(s);
        }
    }
}

