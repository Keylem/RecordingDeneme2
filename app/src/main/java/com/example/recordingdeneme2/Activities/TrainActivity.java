package com.example.recordingdeneme2.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.recordingdeneme2.Utils.MyAdapter;
import com.example.recordingdeneme2.R;

import java.io.File;

public class TrainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        TextView noFilesTextView = findViewById(R.id.noFilesTextView);

        String path = getIntent().getStringExtra("path");

        File root = new File(path);
        File[] filesAndFolders = root.listFiles();

        if(filesAndFolders == null || filesAndFolders.length == 0){
            noFilesTextView.setVisibility(View.VISIBLE);
            return;
        }

        noFilesTextView.setVisibility((View.INVISIBLE));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter( new MyAdapter(getApplicationContext(), filesAndFolders));

    }
}