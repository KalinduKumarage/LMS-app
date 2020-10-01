package com.example.lmsapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class StudentHomePage extends AppCompatActivity {

    // declaring variables
    TextView FullName;
    Button myCourses, schedule, viewProfile, ExamReport, DiscussionPage;

    FirebaseFirestore firebaseFirestore;
    FirebaseAuth fbAuth;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home_page);

        //mapping variables to the items
        FullName = (TextView)findViewById(R.id.textIDFullName);
        myCourses = (Button)findViewById(R.id.btnMyCourses);
        schedule = (Button)findViewById(R.id.btnStudentSchedule);
        viewProfile = (Button)findViewById(R.id.btnViewStudentProfile);
        ExamReport = (Button)findViewById(R.id.btnExamReports);
        DiscussionPage = (Button)findViewById(R.id.btnDiscussionPage);

        //Instantiating
        fbAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        //obtaining the current userID
        userID = fbAuth.getCurrentUser().getUid();

        DocumentReference documentReference = firebaseFirestore.collection("Users").document(userID);

        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                FullName.setText(value.getString("Name"));
            }
        });
    }
}