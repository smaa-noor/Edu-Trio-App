package com.example.edutrio;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edutrio.Models.paperModel;
import com.example.edutrio.adapter.InstNameAdapter;
import com.example.edutrio.adapter.papersAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore db;
    CollectionReference papersColl;
    RecyclerView rvInstName;
    RecyclerView.LayoutManager rvLayoutManager;
    InstNameAdapter instNameAdapter;
    List<paperModel> papersList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = FirebaseFirestore.getInstance();
        papersColl = db.collection("QuestionPapers");

        Spinner spClass = findViewById(R.id.spClass);
        Spinner spSubject = findViewById(R.id.spSubject);
        Spinner spYears = findViewById(R.id.spYears);
        Button btnSubmit = findViewById(R.id.button);
        RecyclerView rvPapers = findViewById(R.id.rv_papers);
        rvInstName = findViewById(R.id.mainRecInstName);
        rvLayoutManager = new LinearLayoutManager(getApplicationContext());

        rvInstName.setLayoutManager(rvLayoutManager);
        papersColl.get().addOnSuccessListener(this, new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                queryDocumentSnapshots.getDocuments().forEach(documentSnapshot -> {
                    paperModel paperModel = documentSnapshot.toObject(com.example.edutrio.Models.paperModel.class);
                    assert paperModel != null;
                    papersList.add(paperModel);

                });
                // calling constructor of adapter
                // with source list as a parameter
                instNameAdapter = new InstNameAdapter();

                // Set Horizontal Layout Manager
                // for Recycler view
                LinearLayoutManager HorizontalLayout
                        = new LinearLayoutManager(
                        MainActivity.this,
                        LinearLayoutManager.HORIZONTAL,
                        false);
                rvInstName.setLayoutManager(HorizontalLayout);

                // Set adapter on recycler view
                rvInstName.setAdapter(instNameAdapter);
                createAdapter("Class", spClass);
                createAdapter("Subject", spSubject);
                createAdapter("Year", spYears);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

        papersAdapter papersAdapter = new papersAdapter(papersList, getApplicationContext());

        rvPapers.setAdapter(papersAdapter);
        rvPapers.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        btnSubmit.setOnClickListener(view -> {
            String clss = spClass.toString();
            String subject = spSubject.toString();
            String years = spYears.toString();
            Button btnSubmit1;
        });
    }

    public void createAdapter(String type, Spinner spinner){
        ArrayList<String> itemList = new ArrayList<>();
        switch (type){
            case "Class":
                papersList.forEach(paper -> {
                    if (!itemList.contains(paper.getDiscipline())){
                        itemList.add(paper.getDiscipline());
                    }
                });
                break;
            case "Subject":
                papersList.forEach(paper -> {
                    if (!itemList.contains(paper.getSubject())){
                        itemList.add(paper.getSubject());
                    }
                });
                break;
            case "Year":
                papersList.forEach(paper -> {
                    if (!itemList.contains(String.valueOf(paper.getYear()))){
                        itemList.add(String.valueOf(paper.getYear()));
                    }
                });
                break;
            default:
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,itemList);
        spinner.setAdapter(adapter);
    }
}