package com.example.aunik.roadcondition2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends ActionBarActivity {

    EditText email, pass;

    Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = (EditText)findViewById(R.id.emailEditText);
        pass = (EditText)findViewById(R.id.passEditText);
        loginButton = (Button) findViewById(R.id.loginButton);

        pass.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    Toast.makeText(getApplicationContext(), pass.getText(), Toast.LENGTH_SHORT).show();
                    handleLogin();
                    return true;
                }
                return false;
            }
        });




        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                handleLogin();
            }
        });




        //handleLogin();

    }
    public void onClick(View v) {
        Intent homeIntent = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(homeIntent);
    }






    void handleLogin(){
        if(isValidUser()){
            Intent homeIntent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(homeIntent);
        }
        else{
            Toast.makeText(getApplicationContext(), "Try agian...Email password mismatched", Toast.LENGTH_SHORT).show();
        }
    }

    boolean isValidUser(){
        String str = pass.getText().toString();
        String mail = email.getText().toString();
        //String passTxt = str.substring ( 0, str.length() - 1 );
        if(mail.equals("xyz")  && str.equals("123")){
            return true;
        }
        else {
            Toast.makeText(getApplicationContext(), pass.getText().toString() + email.getText().toString(), Toast.LENGTH_SHORT).show();
        }
        return false;
    }

}
