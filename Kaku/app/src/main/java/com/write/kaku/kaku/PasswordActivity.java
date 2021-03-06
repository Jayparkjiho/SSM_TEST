package com.write.kaku.kaku;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.write.kaku.kaku.databinding.PasswordViewBinding;

public class PasswordActivity extends AppCompatActivity {

    PasswordViewBinding binding;
    FirebaseAuth mAuth;
    String email;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.password_view);
        mAuth = FirebaseAuth.getInstance();
        binding.setActivity(this);

    }

    public void passwordConfirmButtonClick(View view) {
        email = getIntent().getExtras().getString("email");
        password = binding.inputPassword.getText().toString();
        logInWithEmailAndPassword(email, password);
    }

    public void logInWithEmailAndPassword(String email, String password){
        Log.i("email",email);
        Log.i("pwd",password);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(PasswordActivity.this, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent intent = new Intent(PasswordActivity.this, MainViewActivity.class );
                    startActivity(intent);
                }else{
                    Toast.makeText(PasswordActivity.this, "login fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
