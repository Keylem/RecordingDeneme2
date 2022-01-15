package com.example.recordingdeneme2.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.recordingdeneme2.R;
import com.example.recordingdeneme2.Utils.ValueRecorder;

public class RecordActivity extends AppCompatActivity {

    TextView textViewX;
    TextView textViewY;
    TextView textViewZ;
    TextView textViewTime;

    Button startStopButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_activity);

        textViewX = (TextView) findViewById(R.id.textViewX);
        textViewY = (TextView) findViewById(R.id.textViewY);
        textViewZ = (TextView) findViewById(R.id.textViewZ);
        textViewTime = (TextView) findViewById(R.id.textViewTime);

        startStopButton = (Button) findViewById(R.id.recordButton);

    }

    public void startStopRecording(View view){
        if(ValueRecorder.recordSwitch == false){
            startStopButton.setText("Recording...");
            ValueRecorder.recordSwitch = true;
            Intent valueRecorderIntent = new Intent(getApplication(), ValueRecorder.class);
            startService(valueRecorderIntent);

        }else{
            startStopButton.setText("Record!");
            dataPreview();
            ValueRecorder.recordSwitch = false;

        }

    }

    public void dataPreview(){
        if (!ValueRecorder.valueArrays.isEmpty()){
            float[] getBackData =  ValueRecorder.valueArrays.get(ValueRecorder.valueArrays.size() -1 );
            textViewX.setText("X: " + getBackData[1]);
            textViewY.setText("Y: " + getBackData[2]);
            textViewZ.setText("Z: " + getBackData[3]);

            textViewTime.setText("Time Elapsed: " + getBackData[0]);

        }
    }
}
