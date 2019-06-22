package com.betulerdogan.caycimapp;


import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.betulerdogan.caycimapp.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignActivity extends AppCompatActivity {


    Button btnSignIn;
    AutoCompleteTextView edtPhone, edtEmail;
    EditText edtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        edtEmail = (AutoCompleteTextView) findViewById(R.id.email);
        edtPassword = (EditText)findViewById(R.id.password);
        edtPhone = (AutoCompleteTextView) findViewById(R.id.phone);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(SignActivity.this);
                mDialog.setMessage("Lütfen Bekleyiniz...");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(edtPhone.getText().toString().exists())) {
                            mDialog.dismiss();
                            User user = dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(edtPassword.getText().toString())) {
                                Toast.makeText(SignActivity.this, "Giriş Başarılı", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(SignActivity.this, "Giriş Yapılamadı", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(SignActivity.this, "Kullanıcı Bulunamadı.", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}

