package com.mirea.artemov.notesappv2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mirea.artemov.notesappv2.Models.User;

public class LoginActivity extends AppCompatActivity {

    EditText editText_username, editText_password;
    TextView textView_login;
    Button button_login;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editText_username=findViewById(R.id.editText_username);
        editText_password=findViewById(R.id.editText_password);
        textView_login=findViewById(R.id.textView_login);
        button_login=findViewById(R.id.button_login);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=editText_username.getText().toString();
                String password=editText_password.getText().toString();

                if(username.isEmpty() || password.isEmpty()){
                    Toast.makeText(LoginActivity.this,"Username and Password can't be null!", Toast.LENGTH_SHORT).show();
                    return;
                }

                user.setUsername(username);
                user.setPassword(password);

                Intent intent = new Intent();
                intent.putExtra("user", user); // передается только сериализуемый объект
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}