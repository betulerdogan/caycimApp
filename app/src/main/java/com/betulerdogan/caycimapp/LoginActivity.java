package com.betulerdogan.caycimapp;


import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.betulerdogan.caycimapp.Common.Common;
import com.betulerdogan.caycimapp.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {


    Button btnSignIn;
    AutoCompleteTextView edtPhone;
    EditText edtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnSignIn = (Button) findViewById(R.id.SignIn);
        edtPhone = (AutoCompleteTextView) findViewById(R.id.loginUser);
        edtPassword = (EditText)findViewById(R.id.loginPass);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(LoginActivity.this);
                mDialog.setMessage("Lütfen Bekleyiniz...");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(edtPhone.getText().toString()).exists()) {
                            mDialog.dismiss();
                            User user = dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(edtPassword.getText().toString())) {
                                Intent homeIntent = new Intent(LoginActivity.this,Home.class);
                                Common.currentUser = user;
                                startActivity(homeIntent);
                                finish();

                            } else {
                                Toast.makeText(LoginActivity.this, "Parolanız Yanlış.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            mDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "Kullanıcı Bulunamadı.", Toast.LENGTH_SHORT).show();
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

