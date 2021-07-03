package com.example.xpertstudio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static com.example.xpertstudio.Login.newID;

public class Booking extends AppCompatActivity {
int singlepay=0,doublepay=0,triplepay=0,finalpay=0,ans=0;
static Boolean option1=false,option2=false,option3=false,option4=false,option5=false,option6=false,option7=false;
static String phonenum;
String singlepaydesc,doublepaydesc,triplepaydesc,finalpaydesc;
TextView enteredNumber;
 TextView txtview,txtview1,txtview2,txtview3;
 RadioButton rdbtn1,rdbtn2;
 Button bookbtn;
 String[] customlist={"","Photography Only","Videography Only","Photo&Video","Drone Only","Photo&Drone","Video&Drone","All Three"};
 String[] newlist={" ","(Full HD) 3 Days Complete @65,000PKR","Standard Package 3 Days Complete @95,000PKR","Premium Package 3 Days Complete @135,000PKR","Xpert Premium Package 3 Days Complete @175,000PKR"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);


        RadioGroup grp=(RadioGroup) findViewById(R.id.rdgroup);
        enteredNumber=(TextView) findViewById(R.id.regID);
        Spinner spinpkg=(Spinner) findViewById(R.id.spinnerpkg);
        Spinner spincustom=(Spinner) findViewById(R.id.spinner1);
        Spinner spincustom2=(Spinner) findViewById(R.id.spinner2);
        Spinner spincustom3=(Spinner) findViewById(R.id.spinner3);
        Spinner spincustom4=(Spinner) findViewById(R.id.spinner4);
        rdbtn1=(RadioButton) findViewById(R.id.rdpackages);
        rdbtn2=(RadioButton) findViewById(R.id.rdcustom);
        txtview1=(TextView) findViewById(R.id.txtview1);
        txtview2=(TextView) findViewById(R.id.txtview2);
        txtview3=(TextView) findViewById(R.id.txtview3);
        txtview=(TextView) findViewById(R.id.txtview);

        bookbtn =(Button) findViewById(R.id.bookbtn);
     enteredNumber.setText("You Are Logged in as: "+newID);

        grp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rdpackages:{
                        singlepay=doublepay=triplepay=0;
                        spincustom4.setVisibility(View.GONE);
                        spincustom3.setVisibility(View.GONE);
                        txtview2.setVisibility(View.GONE);
                        txtview3.setVisibility(View.GONE);
                        spincustom.setVisibility(View.GONE);
                        spincustom2.setVisibility(View.GONE);
                        txtview1.setVisibility(View.GONE);
                        Toast.makeText(Booking.this, "Before Order!! Check description of Packages in Packages Page", Toast.LENGTH_LONG).show();
                        ArrayAdapter<String> adapterpkg= new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,newlist);
                        spinpkg.setAdapter(adapterpkg);
                        spinpkg.setVisibility(View.VISIBLE);
                        txtview.setText("Select Package");
                        txtview.setVisibility(View.VISIBLE);
                        spinpkg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                switch(spinpkg.getSelectedItemPosition()){
                                    case 0:{
                                        break;
                                    }
                                    case 1:{
                                        option7=false;
                                        option6=false;
                                        option5=false;
                                        option4=false;
                                        option3=false;
                                        option2=false;
                                        option1=true;
                                        finalpay=65000;
                                        finalpaydesc=getString(R.string.package_1);
                                        break;
                                    }
                                    case 2:{
                                        option7=false;
                                        option6=false;
                                        option5=false;
                                        option4=false;
                                        option3=false;
                                        option2=false;
                                        option1=true;
                                        finalpay=95000;
                                        finalpaydesc=getString(R.string.package_2);
                                        break;
                                    }
                                    case 3:{
                                        option7=false;
                                        option6=false;
                                        option5=false;
                                        option4=false;
                                        option3=false;
                                        option2=false;
                                        option1=true;
                                        finalpay=135000;
                                        finalpaydesc=getString(R.string.package_3);
                                        break;
                                    }
                                    case 4:{
                                        option7=false;
                                        option6=false;
                                        option5=false;
                                        option4=false;
                                        option3=false;
                                        option2=false;
                                        option1=true;
                                        finalpay=175000;
                                        finalpaydesc=getString(R.string.package_4);
                                        break;
                                    }
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                        break;
                    }

                }
                if(rdbtn2.isChecked()){
                    finalpay=singlepay=doublepay=triplepay=0;
                    spincustom.setVisibility(View.GONE);
                    txtview.setVisibility(View.GONE);
                    spinpkg.setVisibility(View.GONE);
                    ArrayAdapter<String> adaptercustom = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, customlist);
                    spincustom.setAdapter(adaptercustom);
                    spincustom.setVisibility(View.VISIBLE);
                    txtview.setText("Select Equipment");
                    txtview.setVisibility(View.VISIBLE);
                    spincustom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            switch (spincustom.getSelectedItemPosition()){
                                case 0:{
                                    break;
                                }
                                case 1:{
                                    option7=false;
                                    option6=false;
                                    option5=false;
                                    option4=false;
                                    option3=false;
                                    option2=false;
                                    option1=true;
                                    singlepay=doublepay=triplepay=0;
                                    spincustom4.setVisibility(View.GONE);
                                    txtview3.setVisibility(View.GONE);
                                    txtview2.setVisibility(View.GONE);
                                    spincustom3.setVisibility(View.GONE);
                                    txtview1.setText("Select # of Photographers");
                                    txtview1.setVisibility(View.VISIBLE);
                                    String[] photographerList = {" ","1 @ 7,000", "2 @ 14,000", "3 @ 21,000"};
                                    ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, photographerList);
                                    spincustom2.setAdapter(adapter1);
                                    spincustom2.setVisibility(View.VISIBLE);
                                    spincustom2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                            switch(spincustom2.getSelectedItemPosition()){
                                                case 0:{
                                                    break;
                                                }
                                                case 1:{
                                                    finalpay=7000;
                                                    finalpaydesc="1 Photographer @ 7,000";
                                                    break;
                                                }
                                                case 2:{
                                                    finalpay=14000;
                                                    finalpaydesc="2 Photographers @ 14,000";
                                                    break;
                                                }
                                                case 3:{
                                                    finalpay=21000;
                                                    finalpaydesc="3 Photographers @ 21,000";
                                                    break;
                                                }
                                            }
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {

                                        }
                                    });
                                    break;
                                }
                                case 2:{
                                    option7=false;
                                    option6=false;
                                    option5=false;
                                    option4=false;
                                    option3=false;
                                    option2=true;
                                    option1=false;
                                    singlepay=doublepay=triplepay=0;
                                    spincustom4.setVisibility(View.GONE);
                                    txtview3.setVisibility(View.GONE);
                                    txtview2.setVisibility(View.GONE);
                                    spincustom3.setVisibility(View.GONE);
                                    txtview1.setText("Select Videography Equipment");
                                    txtview1.setVisibility(View.VISIBLE);
                                    String[] videographyList = {" ","DSLR @ 15,000", "FULL HD @ 10,000", "NORMAL HD @ 6,000"};
                                    ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, videographyList);
                                    spincustom2.setAdapter(adapter1);
                                    spincustom2.setVisibility(View.VISIBLE);
                                    spincustom2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                            switch(spincustom2.getSelectedItemPosition()){
                                                case 0:{
                                                    break;
                                                }
                                                case 1:{
                                                    finalpay=15000;
                                                    finalpaydesc="DSLR Videographer @ 15,000";
                                                    break;
                                                }
                                                case 2:{
                                                    finalpay=10000;
                                                    finalpaydesc="FULL HD @ 10,000";
                                                    break;
                                                }
                                                case 3:{
                                                    finalpay=6000;
                                                    finalpaydesc="Normal HD @ 6,000";
                                                    break;
                                                }
                                            }
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {

                                        }
                                    });

                                    break;
                                }
                                case 3:{
                                    option7=false;
                                    option6=false;
                                    option5=false;
                                    option4=false;
                                    option3=true;
                                    option2=false;
                                    option1=false;
                                    spincustom4.setVisibility(View.GONE);
                                    txtview3.setVisibility(View.GONE);
                                    txtview2.setText("Select Videography Equipment");
                                    txtview2.setVisibility(View.VISIBLE);
                                    String[] videographyList = {" ","DSLR @ 15,000", "FULL HD @ 10,000", "NORMAL HD @ 6,000"};
                                    ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, videographyList);
                                    spincustom3.setAdapter(adapter1);
                                    spincustom3.setVisibility(View.VISIBLE);
                                    txtview1.setText("Select # of Photographers");
                                    txtview1.setVisibility(View.VISIBLE);
                                    String[] photographerList = {" ","1 @ 7,000", "2 @ 14,000", "3 @ 21,000"};
                                    ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, photographerList);
                                    spincustom2.setAdapter(adapter2);
                                    triplepay=finalpay=0;
                                    spincustom2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                            switch(spincustom2.getSelectedItemPosition()){
                                                case 0:{
                                                    break;
                                                }
                                                case 1:{
                                                    singlepay=7000;
                                                    singlepaydesc="1 Photographer @ 7,000";
                                                    break;
                                                }
                                                case 2:{
                                                    singlepay=14000;
                                                    singlepaydesc="2 Photographers @ 14,000";
                                                    break;
                                                }
                                                case 3:{
                                                    singlepay=21000;
                                                    singlepaydesc="3 Photographers @ 21,000";
                                                    break;
                                                }
                                            }
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {

                                        }
                                    });
                                    spincustom3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                            switch(spincustom3.getSelectedItemPosition()){
                                                case 0:{
                                                    break;
                                                }
                                                case 1:{
                                                    doublepay=15000;
                                                    doublepaydesc="DSLR Videographer @ 15,000";
                                                    break;
                                                }
                                                case 2:{
                                                    doublepay=10000;
                                                    doublepaydesc="FULL HD @ 10,000";
                                                    break;
                                                }
                                                case 3:{
                                                    doublepay=6000;
                                                    doublepaydesc="Normal HD @ 6,000";
                                                    break;
                                                }
                                            }
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {

                                        }
                                    });
                                    spincustom2.setVisibility(View.VISIBLE);

                                    break;
                                }
                                case 4:{
                                    option7=false;
                                    option6=false;
                                    option5=false;
                                    option4=true;
                                    option3=false;
                                    option2=false;
                                    option1=false;
                                    singlepay=0;doublepay=0;triplepay=0;
                                    spincustom4.setVisibility(View.GONE);
                                    txtview3.setVisibility(View.GONE);
                                    txtview2.setVisibility(View.GONE);
                                    spincustom3.setVisibility(View.GONE);
                                    txtview1.setText("Select Drone Days");
                                    txtview1.setVisibility(View.VISIBLE);
                                    String[] droneList = {" ","1 Day @ 8000", "2 Day @ 16,000", "3 Day @ 24,000"};
                                    ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, droneList);
                                    spincustom2.setAdapter(adapter1);
                                    spincustom2.setVisibility(View.VISIBLE);
                                    spincustom2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                            switch(spincustom2.getSelectedItemPosition()){
                                                case 0:{
                                                    break;
                                                }
                                                case 1:{
                                                    finalpay=8000;
                                                    finalpaydesc="1 Day Drone @ 8000";
                                                    break;
                                                }
                                                case 2:{
                                                    finalpay=16000;
                                                    finalpaydesc="2 Day Drone @ 16,000";
                                                    break;
                                                }
                                                case 3:{
                                                    finalpay=24000;
                                                    finalpaydesc="3 Day @ 24,000";
                                                    break;
                                                }
                                            }
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {

                                        }
                                    });
                                    break;
                                }
                                case 5:{
                                    option7=false;
                                    option6=false;
                                    option5=true;
                                    option4=false;
                                    option3=false;
                                    option2=false;
                                    option1=false;
                                    triplepay=0;
                                    spincustom4.setVisibility(View.GONE);
                                    txtview3.setVisibility(View.GONE);
                                    txtview1.setText("Select # of Photographers");
                                    txtview1.setVisibility(View.VISIBLE);
                                    String[] photographerList = {" ","1 @ 7,000", "2 @ 14,000", "3 @ 21,000"};
                                    ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, photographerList);
                                    spincustom2.setAdapter(adapter1);
                                    spincustom2.setVisibility(View.VISIBLE);
                                    txtview2.setText("Select Drone Days");
                                    txtview2.setVisibility(View.VISIBLE);
                                    String[] droneList = {" ","1 Day @ 8000", "2 Day @ 16,000", "3 Day @ 24,000"};
                                    ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, droneList);
                                    spincustom3.setAdapter(adapter2);
                                    spincustom3.setVisibility(View.VISIBLE);
                                    spincustom3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                            switch(spincustom3.getSelectedItemPosition()){
                                                case 0:{
                                                    break;
                                                }
                                                case 1:{
                                                    doublepay=8000;
                                                    doublepaydesc="1 Day Drone @ 8000";
                                                    break;
                                                }
                                                case 2:{
                                                    doublepay=16000;
                                                    doublepaydesc="2 Day Drone @ 16,000";
                                                    break;
                                                }
                                                case 3:{
                                                    doublepay=24000;
                                                    doublepaydesc="3 Day @ 24,000";
                                                    break;
                                                }
                                            }
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {

                                        }
                                    });
                                    spincustom2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                            switch(spincustom2.getSelectedItemPosition()){
                                                case 0:{
                                                    break;
                                                }
                                                case 1:{
                                                    singlepay=7000;
                                                    singlepaydesc="1 Photographer @ 7,000";
                                                    break;
                                                }
                                                case 2:{
                                                    singlepay=14000;
                                                    singlepaydesc="2 Photographers @ 14,000";
                                                    break;
                                                }
                                                case 3:{
                                                    singlepay=21000;
                                                    singlepaydesc="3 Photographers @ 21,000";
                                                    break;
                                                }
                                            }
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {

                                        }
                                    });

                                    break;
                                }
                                case 6:{
                                    option7=false;
                                    option6=true;
                                    option5=false;
                                    option4=false;
                                    option3=false;
                                    option2=false;
                                    option1=false;
                                    spincustom4.setVisibility(View.GONE);
                                    txtview3.setVisibility(View.GONE);
                                    txtview1.setText("Select Videography Equipment");
                                    txtview1.setVisibility(View.VISIBLE);
                                    String[] videographyList = {" ","DSLR @ 15,000", "FULL HD @ 10,000", "NORMAL HD @ 6,000"};
                                    ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, videographyList);
                                    spincustom2.setAdapter(adapter1);
                                    spincustom2.setVisibility(View.VISIBLE);
                                    txtview2.setText("Select Drone Days");
                                    txtview2.setVisibility(View.VISIBLE);
                                    String[] droneList = {" ","1 Day @ 8000", "2 Day @ 16,000", "3 Day @ 24,000"};
                                    ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, droneList);
                                    spincustom3.setAdapter(adapter2);
                                    spincustom3.setVisibility(View.VISIBLE);
                                    spincustom2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                            switch(spincustom2.getSelectedItemPosition()){
                                                case 0:{
                                                    break;
                                                }
                                                case 1:{
                                                    doublepay=15000;
                                                    doublepaydesc="DSLR Videographer @ 15,000";
                                                    break;
                                                }
                                                case 2:{
                                                    doublepay=10000;
                                                    doublepaydesc="FULL HD @ 10,000";
                                                    break;
                                                }
                                                case 3:{
                                                    doublepay=6000;
                                                    doublepaydesc="Normal HD @ 6,000";
                                                    break;
                                                }
                                            }
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {

                                        }
                                    });
                                    spincustom3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                            switch(spincustom3.getSelectedItemPosition()){
                                                case 0:{
                                                    break;
                                                }
                                                case 1:{
                                                    singlepay=8000;
                                                    singlepaydesc="1 Day Drone @ 8000";
                                                    break;
                                                }
                                                case 2:{
                                                    singlepay=16000;
                                                    singlepaydesc="2 Day Drone @ 16,000";
                                                    break;
                                                }
                                                case 3:{
                                                    singlepay=24000;
                                                    singlepaydesc="3 Day @ 24,000";
                                                    break;
                                                }
                                            }
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {

                                        }
                                    });

                                    break;
                                }
                                case 7:{
                                    option7=true;
                                    option6=false;
                                    option5=false;
                                    option4=false;
                                    option3=false;
                                    option2=false;
                                    option1=false;
                                    txtview1.setText("Select # of Photographers");
                                    txtview1.setVisibility(View.VISIBLE);
                                    String[] photographerList = {" ","1 @ 7,000", "2 @ 14,000", "3 @ 21,000"};
                                    ArrayAdapter<String> adapter0 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, photographerList);
                                    spincustom2.setAdapter(adapter0);
                                    spincustom2.setVisibility(View.VISIBLE);
                                    txtview2.setText("Select Videography Equipment");
                                    txtview2.setVisibility(View.VISIBLE);
                                    String[] videographyList = {" ","DSLR @ 15,000", "FULL HD @ 10,000", "NORMAL HD @ 6,000"};
                                    ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, videographyList);
                                    spincustom3.setAdapter(adapter1);
                                    spincustom3.setVisibility(View.VISIBLE);
                                    txtview3.setText("Select Drone Days");
                                    txtview3.setVisibility(View.VISIBLE);
                                    String[] droneList = {" ","1 Day @ 8000", "2 Day @ 16,000", "3 Day @ 24,000"};
                                    ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, droneList);
                                    spincustom4.setAdapter(adapter2);
                                    spincustom4.setVisibility(View.VISIBLE);
                                    spincustom2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                            switch(spincustom2.getSelectedItemPosition()){
                                                case 0:{
                                                    break;
                                                }
                                                case 1:{
                                                    singlepay=7000;

                                                    singlepaydesc="1 Photographer @ 7,000";
                                                    break;
                                                }
                                                case 2:{
                                                    singlepay=14000;

                                                    singlepaydesc="2 Photographers @ 14,000";
                                                    break;
                                                }
                                                case 3:{
                                                    singlepay=21000;

                                                    singlepaydesc="3 Photographers @ 21,000";
                                                    break;
                                                }
                                            }
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {

                                        }
                                    });
                                    spincustom3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                            switch(spincustom3.getSelectedItemPosition()){
                                                case 0:{
                                                    break;
                                                }
                                                case 1:{
                                                    doublepay=15000;
                                                    doublepaydesc="DSLR Videographer @ 15,000";
                                                    break;
                                                }
                                                case 2:{
                                                    doublepay=10000;
                                                    doublepaydesc="FULL HD @ 10,000";

                                                    break;
                                                }
                                                case 3:{
                                                    doublepay=6000;
                                                    doublepaydesc="Normal HD @ 6,000";

                                                    break;
                                                }
                                            }
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {

                                        }
                                    });
                                    spincustom4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                            switch(spincustom4.getSelectedItemPosition()){
                                                case 0:{
                                                    break;
                                                }
                                                case 1:{
                                                    triplepay=8000;
                                                    triplepaydesc="1 Day Drone @ 8000";

                                                    break;
                                                }
                                                case 2:{
                                                    triplepay=16000;
                                                    triplepaydesc="2 Day Drone @ 16,000";

                                                    break;
                                                }
                                                case 3:{
                                                    triplepay=24000;
                                                    triplepaydesc="3 Day Drone @ 24,000";

                                                    break;
                                                }
                                            }
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {

                                        }
                                    });

                                    break;
                                }
                                default:{
                                    Toast.makeText(Booking.this, "Invalid Selection", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });


                }
            }
        });
        bookbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phonenum=newID;


                if(option7==true){
                     finalpay=sumupprice(singlepay,doublepay,triplepay);
                }
                if(option6==true||option3==true||option5==true){
                    finalpay=sumuppricevd(singlepay,doublepay);
                }
                if(option4==true||option1==true||option2==true){
                    finalpay=sumupprice(finalpay);
                }
                    FirebaseDatabase database=FirebaseDatabase.getInstance();
                    DatabaseReference tableusers=database.getReference("Users");
                    tableusers.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.child(phonenum).exists()) {
                                dialog_cart dialog_cart=new dialog_cart();
                                if(option4==true||option1==true||option2==true){
                                Bundle bundle=new Bundle();
                                bundle.putString("finalpaydesc",finalpaydesc);
                                bundle.putInt("finalpay",finalpay);
                                dialog_cart.setArguments(bundle);
                                }
                                if(option6==true||option3==true||option5==true){
                                    Bundle bundle=new Bundle();
                                    bundle.putString("singlepaydesc",singlepaydesc);
                                    bundle.putString("doublepaydesc",doublepaydesc);
                                    bundle.putInt("finalpay",finalpay);
                                    dialog_cart.setArguments(bundle);
                                }
                                if (option7==true){
                                    Bundle bundle=new Bundle();
                                    bundle.putString("singlepaydesc",singlepaydesc);
                                    bundle.putString("doublepaydesc",doublepaydesc);
                                    bundle.putString("triplepaydesc",triplepaydesc);
                                    bundle.putInt("finalpay",finalpay);
                                    dialog_cart.setArguments(bundle);
                                }
                                dialog_cart.show(getSupportFragmentManager(),"dialogCart");


                            }
                            else
                                Toast.makeText(Booking.this, "User is not Registered!! Register Yourself", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

            }
        });
                }



    private int sumupprice(int var1,int var2,int var3){
        int finalval=0;
        finalval=var1+var2+var3;
        return finalval;
    }
    private int sumuppricevd(int var1,int var2){
        int finalval=0;
        finalval=var1+var2;
        return finalval;
    }
    private int sumupprice(int var1){
        int finalval=0;
        finalval=var1;
        return finalval;
    }
            }


