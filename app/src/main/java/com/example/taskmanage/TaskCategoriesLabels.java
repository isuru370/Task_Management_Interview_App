package com.example.taskmanage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.taskmanage.modelcalss.category;
import com.example.taskmanage.modelcalss.recycleAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class TaskCategoriesLabels extends AppCompatActivity {

    EditText categoryID,categoryName;
    private FirebaseFirestore db;


    private ArrayList<category> categories;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_categories_labels);

        categoryID = findViewById(R.id.categoryId);
        categoryName = findViewById(R.id.categoryName);
        db = FirebaseFirestore.getInstance();

        recyclerView= findViewById(R.id.recycleView);
        categories = new ArrayList<>();

        setUserInfo();
        recycleAdapter adapter = new recycleAdapter(categories);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        findViewById(R.id.catSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!categoryID.getText().toString().isEmpty() != categoryName.getText().toString().isEmpty()){
                    category cat = new category();
                    cat.setCatId(categoryID.getText().toString());
                    cat.setCatName(categoryName.getText().toString());
                    db.collection("Category").add(cat).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            Toast.makeText(TaskCategoriesLabels.this, "Category Save Success", Toast.LENGTH_SHORT).show();
                            setUserInfo();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(TaskCategoriesLabels.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{

                }
            }
        });
    }

    private void setUserInfo() {
        db.collection("Category").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(!queryDocumentSnapshots.isEmpty()){
                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot d : list) {
                        category cat = d.toObject(category.class);

                            categories.add(cat);
                        }
                    }
                }
        });
//        categories.add(new category("1234","work"));
//        categories.add(new category("12345","personal"));
//        categories.add(new category("12234","shopping"));
    }
}