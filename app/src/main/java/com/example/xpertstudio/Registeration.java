package com.example.xpertstudio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Registeration extends AppCompatActivity {
    EditText regID,regName,regPass;
    Button regbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        regID=findViewById(R.id.regID);
        regName=findViewById(R.id.regName);
        regPass=findViewById(R.id.regPass);
        regbtn=findViewById(R.id.RegistrationButton);

        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference usertable=database.getReference("Users");

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newID = regID.getText().toString().trim();
                String newName = regName.getText().toString().trim();
                String newPass = regPass.getText().toString().trim();
                Boolean check = true;

                if (TextUtils.isEmpty(newID)) {
                    regID.setError("User Phone Number Required!!!");
                    check = false;
                }
                if (TextUtils.isEmpty(newPass)) {
                    regPass.setError("User Password Required!!!");
                    check = false;
                }
                if (newID.length() < 11 || newID.length() > 11) {
                    regID.setError("Must Be Length 10");
                    check = false;
                }
                if (TextUtils.isEmpty(newName)) {
                    regPass.setError("Name Required!!!");
                    check = false;
                }


                if (check == true) {
                    ProgressDialog newDialog=new ProgressDialog(Registeration.this);
                    newDialog.setMessage("Please Wait...");
                    newDialog.show();
                    usertable.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.child(newID).exists()){
                                Toast.makeText(Registeration.this, "User Already Exists!!", Toast.LENGTH_SHORT).show();
                                newDialog.dismiss();
                            }
                            else{
                                Person person=new Person(newName,newPass);
                                usertable.child(newID).setValue(person);
                                Toast.makeText(Registeration.this, "User Registered!!", Toast.LENGTH_SHORT).show();
                                finish();
                                newDialog.dismiss();
                            }

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
