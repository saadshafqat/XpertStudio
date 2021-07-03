package com.example.xpertstudio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.xpertstudio.MainActivity.cvAccount;
import static com.example.xpertstudio.MainActivity.cvLogin;
import static com.example.xpertstudio.MainActivity.cvLogout;
import static com.example.xpertstudio.MainActivity.cvSignUp;
import static com.example.xpertstudio.MainActivity.regID;

public class Login extends AppCompatActivity {
static EditText userID;
EditText        userPass;
Button logbtn;
static Boolean LoginSeasion=false;
    static String  newID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        userID=findViewById(R.id.userID);
        userPass=findViewById(R.id.userPass);
        logbtn=findViewById(R.id.LoginButton);


        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference tableusers=database.getReference("Users");

        logbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               newID = userID.getText().toString().trim();
                String newPass = userPass.getText().toString().trim();
                Boolean check = true;

                if (TextUtils.isEmpty(newID)) {
                    userID.setError("User Phone Number Required!!!");
                    check = false;
                }
                if (TextUtils.isEmpty(newPass)) {
                    userPass.setError("User Password Required!!!");
                    check = false;
                }
                if (newID.length() < 11 || newID.length() > 11) {
                    userID.setError("Must Be Length 10");
                    check = false;
                }

                if (check == true) {
                    tableusers.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.child(newID).exists()) {
                                Person person = snapshot.child(newID).getValue(Person.class);
                                if (person.getPassword().equals(newPass)) {
                                    Toast.makeText(Login.this, "Logged in!!", Toast.LENGTH_SHORT).show();
                                    LoginSeasion=true;
                                    if(LoginSeasion==true){
                                        cvLogin.setVisibility(View.GONE);
                                        cvSignUp.setVisibility(View.GONE);
                                        regID.setText("You are Logged in as: "+ newID);
                                        cvAccount.setVisibility(View.VISIBLE);
                                        cvLogout.setVisibility(View.VISIBLE);
                                    }
                                    finish();
                                } else
                                    Toast.makeText(Login.this, "Failed!!", Toast.LENGTH_SHORT).show();
                            } else
                                Toast.makeText(Login.this, "User Not Found!! Register Yourself", Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }


            }
        });


    }
}