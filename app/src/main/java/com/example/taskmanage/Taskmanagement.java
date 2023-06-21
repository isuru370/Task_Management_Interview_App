package com.example.taskmanage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.taskmanage.modelcalss.category;
import com.example.taskmanage.modelcalss.task;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Taskmanagement extends AppCompatActivity {

     EditText dueDate,uTitle,uDescription,uPropty;
     Spinner uSppiner;
     ImageView uDelete,uEdit;
     private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskmanagement);

        dueDate = findViewById(R.id.datePickerText);
        uTitle = findViewById(R.id.userTitle);
        uDescription = findViewById(R.id.edtInput);
        uPropty = findViewById(R.id.priorityLevel);
        uSppiner = findViewById(R.id.categoryId);
        uDelete = findViewById(R.id.delete);
        uEdit = findViewById(R.id.edit);
        db = FirebaseFirestore.getInstance();
        findViewById(R.id.datePicker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               final Calendar c = Calendar.getInstance();

               int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);


                dueDate.setText(year+" "+month+" "+day);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Taskmanagement.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                dueDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        },
                        year, month, day);
                datePickerDialog.show();
            }

        });
        findViewById(R.id.notify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(Taskmanagement.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.bottom_sheet_layout);

                dialog.show();
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                dialog.getWindow().setGravity(Gravity.BOTTOM);
            }
        });
        db.collection("Category").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(queryDocumentSnapshots.isEmpty()){
                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot d : list) {
                        category cat = d.toObject(category.class);
                        List<String> list2 = new ArrayList<String>();
                        list2.add(cat.getCatName());
                        ArrayAdapter arrayAdapter = new ArrayAdapter(
                                getApplicationContext(),
                                android.R.layout.simple_spinner_item,list2
                        );
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        uSppiner.setAdapter(arrayAdapter);

                    }
                }
            }
        });

        findViewById(R.id.nextKey).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Taskmanagement.this,TaskCategoriesLabels.class));
            }
        });
        findViewById(R.id.nextKey2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Taskmanagement.this,TaskTrackingProgress.class));
            }
        });
        findViewById(R.id.taskAddBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!dueDate.getText().toString().isEmpty() != uSppiner.getSelectedItem().toString().isEmpty()){
                    task uTasks = new task();
                    uTasks.setTitle(uTitle.getText().toString());
                    uTasks.setDescrption(uDescription.getText().toString());
                    uTasks.setDueDate(dueDate.getText().toString());
                    uTasks.setProptyLavel(uPropty.getText().toString());
                    uTasks.setCategory(uSppiner.getSelectedItem().toString());
                    db.collection("UserTask").add(uTasks).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            Toast.makeText(Taskmanagement.this, "Date Save Success", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {

                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Taskmanagement.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    Toast.makeText(Taskmanagement.this, "getMessage()", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}