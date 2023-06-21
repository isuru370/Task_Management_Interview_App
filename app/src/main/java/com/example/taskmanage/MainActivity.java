package com.example.taskmanage;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText userEmail, userPassword, userConfirmPassword;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userEmail = findViewById(R.id.userEmail);
        userPassword = findViewById(R.id.userPassword);
        userConfirmPassword = findViewById(R.id.userConfirmPassword);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        findViewById(R.id.userRegisterBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!userEmail.getText().toString().isEmpty() != userPassword.getText().toString().isEmpty()) {
                    if (emailValidate(userEmail.getText().toString())) {
                        if (userPassword.getText().toString().equals(userConfirmPassword.getText().toString())) {
                            Toast.makeText(getApplicationContext(), "All is well", Toast.LENGTH_SHORT).show();
                            mAuth.createUserWithEmailAndPassword(userEmail.getText().toString(), userPassword.getText().toString())
                                    .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                // Sign in success, update UI with the signed-in user's information
                                                Log.d(TAG, "createUserWithEmail:success");
                                                FirebaseUser user = mAuth.getCurrentUser();
                                                Toast.makeText(MainActivity.this, "Account Create Success.",
                                                        Toast.LENGTH_SHORT).show();
                                                updateUser(user);
                                                startActivity(new Intent(MainActivity.this,Taskmanagement.class));
                                            } else {
                                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                                Toast.makeText(MainActivity.this, "Authentication failed.",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });


                        } else {
                            Toast.makeText(getApplicationContext(), "The password is not the same", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "The email is incorrect", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Data not entered correctly", Toast.LENGTH_SHORT).show();
                }


            }

            private boolean emailValidate(String emails) {
                String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
                Pattern pattern = Pattern.compile(regex);
                //Create instance of matcher
                Matcher matcher = pattern.matcher(emails);
                if (matcher.matches()) {
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    private void updateUser(FirebaseUser user) {
        Map<String, Object> users = new HashMap<>();
        users.put("UserEmail", userEmail.getText().toString());
        users.put("UserId", user);

        db.collection("users")
                .add(users)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            startActivity(new Intent(MainActivity.this, Taskmanagement.class));
        }
    }
}