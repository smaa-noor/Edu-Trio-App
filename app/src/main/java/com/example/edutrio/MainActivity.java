package com.example.edutrio;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edutrio.Models.Helper;
import com.example.edutrio.Models.paperModel;
import com.example.edutrio.adapter.InstNameAdapter;
import com.example.edutrio.adapter.papersAdapter;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore db;
    CollectionReference papersColl;
    RecyclerView rvInstName;
    RecyclerView.LayoutManager rvLayoutManager;
    InstNameAdapter instNameAdapter;
    List<paperModel> papersList = new ArrayList<>();
    Helper helper;

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
        helper = new Helper(this);

        Spinner spClass = findViewById(R.id.spClass);
        Spinner spSubject = findViewById(R.id.spSubject);
        Spinner spYears = findViewById(R.id.spYears);
        Button btnSubmit = findViewById(R.id.button);
        RecyclerView rvPapers = findViewById(R.id.rv_papers);
        rvInstName = findViewById(R.id.mainRecInstName);
        rvLayoutManager = new LinearLayoutManager(getApplicationContext());

        rvInstName.setLayoutManager(rvLayoutManager);
        papersColl.get().addOnSuccessListener(this, queryDocumentSnapshots -> {
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
            helper.createAdapter(papersList,"Class", spClass);
            helper.createAdapter(papersList,"Subject", spSubject);
            helper.createAdapter(papersList,"Year", spYears);
        }).addOnFailureListener(e -> {

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
}