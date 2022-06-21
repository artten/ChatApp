package com.example.chatapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.chatapp.entities.Post;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    private AppDB db;
    private PostDao postDao;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "PostsDB")
                .allowMainThreadQueries().build();
        postDao = db.postDao();

        Button register = findViewById(R.id.reg);
        EditText userName = findViewById(R.id.regId);
        EditText nickName = findViewById(R.id.regNickname);
        EditText password = findViewById(R.id.regPassword);
        EditText retPass = findViewById(R.id.regPasswordRe);
        Button reg = findViewById(R.id.reg); // this is register button in register page.
        context = this;
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterAPI registerAPI = new RegisterAPI(context);
                int ret2 = registerAPI.postRegister(userName.getText().toString(),
                        password.getText().toString(), nickName.getText().toString());

                Log.d("lols", "ret = " + ret2);
                //Intent intent = new Intent(getApplicationContext(), ChatContacts.class);
                //startActivity(intent);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkRegistration(userName, nickName, password, retPass)) {
                    Post post = new Post(1, userName.getText().toString(), nickName.getText()
                            .toString(), password.getText().toString(), " ");
                    postDao.insert(post);
                    Intent intent = new Intent(getApplicationContext(), ChatContacts.class);
                    startActivity(intent);
                }
            }
        });
    }

    protected boolean checkRegistration(EditText userName, EditText nickName, EditText password, EditText retPass) {

        if (userName.getText().toString().length() > 2) {
            if (nickName.getText().toString().length() > 2) {
                if (password.getText().toString().equals(retPass.getText().toString())) {
                    String passValidator = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{9,}";
                    Pattern pattern = Pattern.compile(passValidator);
                    Matcher matcher = pattern.matcher(password.getText().toString());
                    if (matcher.matches()) {
                        return true;
                    }
                    else {
                            alertCreate("Password illegal",
                                    "password should be at least 9 characters and include: \n" +
                                            "at least one lower letter \n" +
                                            "at least one upper letter \n" +
                                            "at least one number");
                    }
                }
                else {
                    alertCreate(" ", "Passwords are not the same");
                }
            }
            else {
                alertCreate("Nickname too short", "Nickname should be at least 3 letters");
            }
        }
        else {
            alertCreate("Username too short", "Username should be at least 3 letters");
        }
        return false;
    }

    protected void alertCreate(String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(Register.this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertDialog.show();
    }
}
