package com.example.emailre;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
private TextView statusView;
private Button loginButton;
private EditText loginEdit, passwordEdit, serverEdit, portEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
statusView = findViewById(R.id.ConnectStatus);
loginButton = findViewById(R.id.LoginButton);
loginEdit = findViewById(R.id.loginEdit);
passwordEdit = findViewById(R.id.PasswordEdit);
serverEdit = findViewById(R.id.ServerEdit);
portEdit = findViewById(R.id.PortEdit);


    }

    public void onLoginHandler(View view) {
new Thread(new Runnable() {
    @Override
    public void run() {

    }
}).start();
    }
}
