package com.example.xpertstudio;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import static com.example.xpertstudio.Login.newID;

public class SendYourPhoto extends AppCompatActivity {
private static final int PICK_IMAGE_REQUEST=1;
 private Uri mImageUri;
 private StorageReference mStorageRef;
static ProgressBar mprogressBar;
    EditText editdesc;
 private StorageTask muploadTask;
   static Button sendingimg;
TextView regID;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_your_photo);
        img=(ImageView) findViewById(R.id.img);
       editdesc=(EditText) findViewById(R.id.editdsec);
        Button selectimg=(Button) findViewById(R.id.selectimg);
        Button uploadimg=(Button) findViewById(R.id.upload);
        sendingimg=(Button) findViewById(R.id.sendingimg);
       mprogressBar=(ProgressBar) findViewById(R.id.progbar);
       regID=findViewById(R.id.regID);
       regID.setText("You are Logged in as: "+ newID);
        mStorageRef=FirebaseStorage.getInstance().getReference("RawPhotos");
        selectimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  openfilechooser();
            }
        });
        uploadimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mImageUri!=null && muploadTask!=null && muploadTask.isInProgress()){
                    Toast.makeText(SendYourPhoto.this, "Uploading....", Toast.LENGTH_SHORT).show();
                }
                else{
                    EasyPaisaGateway easyPaisaGateway=new EasyPaisaGateway();
                    easyPaisaGateway.show(getSupportFragmentManager(),"easypaisafragment");
                    if(EasyPaisaGateway.check==true){
                        uploadFile();
                        EasyPaisaGateway.check=false;
                    }

                }
                if(mImageUri==null){
                    selectimg.setError("First Select Image!!!");
                }

            }
        });
        sendingimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
    private String getFileExtension(Uri uri){
        ContentResolver contentResolver=getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(contentResolver.getType(uri));

    }

  private void uploadFile() {
       if(mImageUri!=null){

           StorageReference fileRef=mStorageRef.child(newID+"-"+editdesc.getText().toString().trim()+  "." +getFileExtension(mImageUri));
           muploadTask=fileRef.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
               @Override
               public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                          mprogressBar.setProgress(0);
                          mprogressBar.setVisibility(View.GONE);
                          sendingimg.setVisibility(View.VISIBLE);



                   Toast.makeText(SendYourPhoto.this, "Upload Successfully!!", Toast.LENGTH_SHORT).show();




               }
           })
                   .addOnFailureListener(new OnFailureListener() {
                       @Override
                       public void onFailure(@NonNull Exception e) {
                           Toast.makeText(SendYourPhoto.this, "Error! "+e.getMessage(), Toast.LENGTH_SHORT).show();

                       }
                   })
                   .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                       @Override
                       public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        double progess=(100*snapshot.getBytesTransferred()/snapshot.getTotalByteCount());

                        mprogressBar.setProgress((int) progess);

                        
                       }
                   });
       }
       else{
           Toast.makeText(this, "Image not Selected!!", Toast.LENGTH_SHORT).show();
       }


    }

    private void openfilechooser() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }
    public Bitmap loadFromUri(Uri photoUri) {
        Bitmap image = null;
        try {
            // check version of Android on device
            if(Build.VERSION.SDK_INT > 27){
                // on newer versions of Android, use the new decodeBitmap method
                ImageDecoder.Source source = ImageDecoder.createSource(this.getContentResolver(), photoUri);
                image = ImageDecoder.decodeBitmap(source);

            } else {
                // support older versions of Android by using getBitmap
                image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoUri);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==PICK_IMAGE_REQUEST&&resultCode==RESULT_OK&&data!=null&&data.getData()!=null){
            mImageUri =data.getData();
            Bitmap selectedImage = loadFromUri(mImageUri);
            img.setImageBitmap(selectedImage);


        }
    }
}