package com.vito.tugas_4_prak;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.Toast;

import com.vito.tugas_4_prak.modul.User;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfile extends AppCompatActivity {

    private ImageButton btnback ;
    private CircleImageView profilePict;
    private Button editImage;

    private Button btnsubmit;

    private static  final String TAG = EditProfile.class.getCanonicalName();
    private static  final int GALLERY_REQUEST_CODE = 1;

    public static final String profile_key = "profile";
    private EditText et_name, et_email, et_phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        btnback = findViewById(R.id.btn_backToProfile);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent back = new Intent(EditProfile.this, ShowProfile.class);
                startActivity(back);
            }
        });
        et_name = findViewById(R.id.et_name);
        et_email = findViewById(R.id.et_email);
        et_phoneNumber = findViewById(R.id.et_phoneNumber);
        btnsubmit = findViewById(R.id.btn_submitProfile);
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et_name.getText().toString();
                String email = et_email.getText().toString();
                String phone = et_phoneNumber.getText().toString();

                User profile = new User(name, email, phone);
                Intent submit = new Intent(EditProfile.this, ShowProfile.class);
                submit.putExtra(profile_key, profile);
                startActivity(submit);
            }
        });
        profilePict = findViewById(R.id.image_view_profile);
        editImage = findViewById(R.id.btn_editImage);
        editImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), GALLERY_REQUEST_CODE);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED){
            Toast.makeText(this, "Cancel Input Image", Toast.LENGTH_SHORT).show();
            return;
        }else if (requestCode == GALLERY_REQUEST_CODE){
            if (data != null){
                try {
                    Uri imageuri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageuri);
                    profilePict.setImageBitmap(bitmap);
                }catch (IOException e){
                    Toast.makeText(this, "Can't Load Image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }
}