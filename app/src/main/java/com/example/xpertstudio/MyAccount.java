package com.example.xpertstudio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.xpertstudio.Login.newID;

public class MyAccount extends AppCompatActivity {
TextView txt;
RecyclerView recyclerView;
OrderGetAdapter Adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        txt=findViewById(R.id.title);
        recyclerView=findViewById(R.id.rv);

        txt.setText(newID);

        List<ModelForOrders> data=new ArrayList<>();




        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference usertable=database.getReference("Orders").child(newID);



        usertable.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               data.add(new ModelForOrders(snapshot.child("firstreq").getValue().toString(),snapshot.child("secondreq").getValue().toString(),snapshot.child("thirdreq").getValue().toString(),snapshot.child("price").getValue().toString()));
                Toast.makeText(MyAccount.this, ""+snapshot.child("firstreq"), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Adapter=new OrderGetAdapter(getApplicationContext(), (ArrayList<ModelForOrders>) data);
        recyclerView.setAdapter(Adapter);


    }
}