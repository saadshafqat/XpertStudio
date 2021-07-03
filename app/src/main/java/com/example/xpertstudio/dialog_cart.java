package com.example.xpertstudio;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.xpertstudio.Booking.option1;
import static com.example.xpertstudio.Booking.option2;
import static com.example.xpertstudio.Booking.option3;
import static com.example.xpertstudio.Booking.option4;
import static com.example.xpertstudio.Booking.option5;
import static com.example.xpertstudio.Booking.option6;
import static com.example.xpertstudio.Booking.option7;
import static com.example.xpertstudio.Booking.phonenum;

public class dialog_cart extends DialogFragment {
    private TextView carttxt1,carttxt4,carttxt2,carttxt3;
    Boolean check1=false,check2=false,check3=false;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity(),android.R.style.Theme_Material_Dialog);

        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.dialog_cart,null);

        builder.setView(view)
                .setTitle("Cart")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Order Cancelled", Toast.LENGTH_SHORT).show();

                    }
                })
                .setPositiveButton("Place Order", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase database=FirebaseDatabase.getInstance();
                        DatabaseReference ordertable=database.getReference("Orders");
                        if(check1==true){
                            Cart cart=new Cart(carttxt1.getText().toString().trim(),carttxt4.getText().toString().trim(),"null","null","null");
                            ordertable.child(phonenum).setValue(cart);
                            Toast.makeText(getActivity(), "Order Succesfully Placed!!", Toast.LENGTH_SHORT).show();
                        }
                        if (check2==true){
                            Cart cart=new Cart(carttxt1.getText().toString().trim(),carttxt4.getText().toString().trim(),carttxt2.getText().toString().trim(),"null","null");
                            ordertable.child(phonenum).setValue(cart);
                            Toast.makeText(getActivity(), "Order Succesfully Placed!!", Toast.LENGTH_SHORT).show();
                        }
                        if(check3==true){
                            Cart cart=new Cart(carttxt1.getText().toString().trim(),carttxt4.getText().toString().trim(),carttxt2.getText().toString().trim(),carttxt3.getText().toString().trim(),"null");
                            ordertable.child(phonenum).setValue(cart);
                            Toast.makeText(getActivity(), "Order Succesfully Placed!!", Toast.LENGTH_SHORT).show();
                        }





                    }
                });

        carttxt1=(TextView) view.findViewById(R.id.carttxt1);
        carttxt2=(TextView) view.findViewById(R.id.carttxt2);
        carttxt3=(TextView) view.findViewById(R.id.carttxt3);
        carttxt4=(TextView) view.findViewById(R.id.carttxt4);
        if(option4==true||option1==true||option2==true){
            check1=true;
            check2=false;
            check3=false;
            String finalpaydesc=this.getArguments().getString("finalpaydesc");
            int finalpay=this.getArguments().getInt("finalpay");
            String finalp= String.valueOf(finalpay);
            carttxt1.setText("1x "+finalpaydesc);
            carttxt4.setText("Total: "+finalp);
        }
        if(option6==true||option3==true||option5==true){
            check1=false;
            check2=true;
            check3=false;
            String singlepaydesc=this.getArguments().getString("singlepaydesc");
            String doublepaydesc=this.getArguments().getString("doublepaydesc");
            int finalpay=this.getArguments().getInt("finalpay");
            String finalp= String.valueOf(finalpay);
            carttxt1.setText("1x "+singlepaydesc);
            carttxt2.setText("1x "+doublepaydesc);
            carttxt4.setText("Total: "+finalp);
        }
        if(option7==true){
            check1=false;
            check2=false;
            check3=true;
            String singlepaydesc=this.getArguments().getString("singlepaydesc");
            String doublepaydesc=this.getArguments().getString("doublepaydesc");
            String triplepaydesc=this.getArguments().getString("triplepaydesc");
            int finalpay=this.getArguments().getInt("finalpay");
            String finalp= String.valueOf(finalpay);
            carttxt1.setText("1x "+singlepaydesc);
            carttxt2.setText("1x "+doublepaydesc);
            carttxt3.setText("1x "+triplepaydesc);
            carttxt4.setText("Total: "+finalp);
        }




        return builder.create();
    }
}
