package com.example.samplemygame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabaseRn;
    private EditText mEtEmail, mEtPass, mEtName;
    private Button mBtnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRn = FirebaseDatabase.getInstance().getReference("Sense");

        mEtEmail = findViewById(R.id.et_email);
        mEtPass = findViewById(R.id.et_password);
        mEtName = findViewById(R.id.et_name);
        mBtnRegister = findViewById(R.id.btn_login);

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strEmail = mEtEmail.getText().toString();
                String strPwd = mEtPass.getText().toString();
                String strName = mEtName.getText().toString();



                mFirebaseAuth.createUserWithEmailAndPassword(strEmail, strPwd).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
                            UserAccount account = new UserAccount();
                            account.setIdToken(firebaseUser.getUid());
                            account.setEmailId(firebaseUser.getEmail());
                            account.setPassword(strPwd);
                            account.setName(strName);

                            mDatabaseRn.child("UserAccount").child(firebaseUser.getUid()).setValue(account);

                            Toast.makeText(Register.this, "회원가입 성공", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(Register.this, LoginActivity.class);
                            startActivity(intent);
                            finish();

                        }else {
                            Toast.makeText(Register.this, "회원가입 실패", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}