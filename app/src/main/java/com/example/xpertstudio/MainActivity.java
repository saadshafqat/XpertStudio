package com.example.xpertstudio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.xpertstudio.Login.LoginSeasion;
import static com.example.xpertstudio.Login.newID;

public class MainActivity extends AppCompatActivity {
    static CardView cvSignUp;
   static CardView cvLogin;
   static CardView cvAccount;
   static CardView cvLogout;
   static TextView regID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



       cvSignUp=(CardView) findViewById(R.id.cvSignUp);
       cvLogin =(CardView) findViewById(R.id.cvLogin);
         cvLogout=(CardView) findViewById(R.id.cvLogout);
      cvAccount=(CardView) findViewById(R.id.cvAccount);
        CardView cvBookNow=(CardView) findViewById(R.id.cvBookNow);
        CardView cvSendYourPhoto=(CardView) findViewById(R.id.cvSendYourPhoto);
        FloatingActionButton fab=(FloatingActionButton) findViewById(R.id.floatingActionButton3);
     regID=(TextView) findViewById(R.id.regiD);



        BottomNavigationView bottNav = findViewById(R.id.bottom_navigation);
        bottNav.setOnNavigationItemSelectedListener(navListener);

        cvAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MyAccount.class);
                startActivity(intent);
            }
        });

cvSignUp.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(MainActivity.this,Registeration.class);
        startActivity(intent);
    }
});
        cvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Login.class);
                startActivity(intent);
            }
        });
        cvBookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LoginSeasion==true) {
                    Intent intent = new Intent(MainActivity.this, Booking.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(MainActivity.this, "Login Yourself first..!", Toast.LENGTH_SHORT).show();
            }
        });
        cvSendYourPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LoginSeasion==true) {
                    Intent intent = new Intent(MainActivity.this, SendYourPhoto.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(MainActivity.this, "Login Yourself first..!", Toast.LENGTH_SHORT).show();
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,GalleryActivity.class);
                startActivity(intent);
            }
        });

        cvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginSeasion=false;
                cvLogin.setVisibility(View.VISIBLE);
                cvSignUp.setVisibility(View.VISIBLE);
                regID.setVisibility(View.GONE);
                cvAccount.setVisibility(View.GONE);
                cvLogout.setVisibility(View.GONE);
            }
        });

    }
   private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
       @Override
       public boolean onNavigationItemSelected(@NonNull MenuItem item) {
           Fragment selectedfragment = null;

           switch (item.getItemId())
           {
               case R.id.booking: {
                   selectedfragment = new fragment_bookus();
                   break;
               }
               case R.id.packages: {
                   selectedfragment = new fragment_packages();
                   break;
               }
               case R.id.aboutus: {
                   selectedfragment = new fragment_aboutus();
                   break;
               }
           }
           getSupportFragmentManager().beginTransaction().replace(R.id.flayout,selectedfragment).commit();

           return true;
       }
   };

}