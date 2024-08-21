package com.example.edutrio;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edutrio.Models.paperModel;
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

        Spinner spInst = findViewById(R.id.spinst);
        Spinner spClass = findViewById(R.id.spclass);
        Spinner spSubject = findViewById(R.id.spsubject);
        Spinner spYears = findViewById(R.id.spyears);
        Spinner spBoard = findViewById(R.id.spboard);
        Button btnSubmit = findViewById(R.id.button);
        RecyclerView rvPapers = findViewById(R.id.rv_papers);

        papersColl.get().addOnSuccessListener(this, new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                queryDocumentSnapshots.getDocuments().forEach(documentSnapshot -> {
                    new AlertDialog.Builder(getApplicationContext()).setTitle(documentSnapshot.toString());
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
        List<paperModel> papersList = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            paperModel paperModel = new paperModel("Subject "+i, 2010+i, "Discipline " + i, "Group " + i, "Board " + i);
            papersList.add(paperModel);
        }

        papersAdapter papersAdapter = new papersAdapter(papersList, getApplicationContext());

        rvPapers.setAdapter(papersAdapter);
        rvPapers.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        btnSubmit.setOnClickListener(view -> {
            String institution = spInst.toString();
            String clss = spClass.toString();
            String subject = spSubject.toString();
            String years = spYears.toString();
            String board = spBoard.toString();
            Button btnSubmit1;
        });
        String[] institution = {"select institutions", "school", "college", "university"};
        String[] itemsClass = {" class", "9th", "10th"};
        String[] itemsSubject = {" subject", "english", "math"};
        String[] itemsYear = {"select year", "2020", "2021", "2022","2023"};
        String[] itemsBoard = {"select board", "punjab board", "gujranwala board"};
        createAdapter(institution, spInst);
        createAdapter(itemsClass, spClass);
        createAdapter(itemsSubject, spSubject);
        createAdapter(itemsYear, spYears);
        createAdapter(itemsBoard, spBoard);
    }

    public void createAdapter(String[] itemList, Spinner spinner){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,itemList);
        spinner.setAdapter(adapter);
    }

}