package com.vito.tugas_4_prak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vito.tugas_4_prak.modul.User;

public class ShowProfile extends AppCompatActivity {

    private TextView tv_name, tv_email, tv_phoneNumber;

    private TextView toEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_profile);
        toEdit = findViewById(R.id.tv_editProfile);
        toEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toEdit = new Intent(ShowProfile.this, EditProfile.class);
                startActivity(toEdit);
            }
        });
        tv_email = findViewById(R.id.tv_showemail);
        tv_name = findViewById(R.id.tv_showname);
        tv_phoneNumber = findViewById(R.id.tv_showphoneNumber);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            User profile = extras.getParcelable("profile");
            tv_name.setText(profile.getNama());
            tv_email.setText(profile.getEmail());
            tv_phoneNumber.setText(profile.getNumber());
        }

    }

}