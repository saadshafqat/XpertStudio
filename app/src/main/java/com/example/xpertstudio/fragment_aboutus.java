package com.example.xpertstudio;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class fragment_aboutus extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_aboutus, container, false);

        Button whatsappBtn=(Button) v.findViewById(R.id.whatsappbtn);
        Button facebookBtn=(Button) v.findViewById(R.id.facebookbtn);

        ImageView aboutusimg =(ImageView) v.findViewById(R.id.aboutusimg);
        TextView aboutustxt1 =(TextView) v.findViewById(R.id.aboutustxt);
        TextView phonenumber1 =(TextView) v.findViewById(R.id.phonenumber1);
        TextView phonenumber2 =(TextView) v.findViewById(R.id.phonenumber2);
        TextView email =(TextView) v.findViewById(R.id.email);
        TextView callicon1 =(TextView) v.findViewById(R.id.callicon1);
        TextView callicon2 =(TextView) v.findViewById(R.id.callicon2);
        TextView emailicon =(TextView) v.findViewById(R.id.emailicon);

        Animation moveup=AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.moveup);
        Animation fadein=AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.fadein);

        aboutusimg.setAnimation(moveup);

      new Handler().postDelayed(new Runnable() {
          @Override
          public void run() {
              aboutustxt1.setText("ایکسپرٹ سٹوڈیو\n مرالہ پلازہ ڈنگہ روڈ کھاریاں\n ");
              phonenumber1.setText("03324951740");
              phonenumber2.setText("03361560822");
              email.setText("xpertstudio786@gmail.com");

              callicon1.setVisibility(View.VISIBLE);
              callicon2.setVisibility(View.VISIBLE);
              emailicon.setVisibility(View.VISIBLE);
                facebookBtn.setVisibility(View.VISIBLE);
                whatsappBtn.setVisibility(View.VISIBLE);

              aboutustxt1.setAnimation(fadein);

          }
      },1000);
        facebookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String facebookId = "fb://page/1385904898394349";
                String urlPage = "https://www.facebook.com/Xpert-Studio-1385904898394349";

                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(facebookId)));
                }catch (Exception e){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlPage)));
                }
            }
        });
        whatsappBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contact = "+92 3324951740"; // use country code with your phone number
                String url = "https://api.whatsapp.com/send?phone=" + contact;
                try {
                    PackageManager pm = getActivity().getApplicationContext().getPackageManager();
                    pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                } catch (PackageManager.NameNotFoundException e) {
                    Toast.makeText(getActivity(), "WhatsApp not found on Phone!!", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });




        return v;


    }





}

