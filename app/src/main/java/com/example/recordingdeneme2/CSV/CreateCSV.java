package com.example.recordingdeneme2.CSV;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.CSVWriterBuilder;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;



public class CreateCSV extends Service {

    String filename = "";
    String filepath = "";

    String fileContent = "";

    int bufferSize = Integer.MAX_VALUE;

    public static ArrayList<float[]> arraysToWrite;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        threadWork();
                    } }).run();



        return START_NOT_STICKY;
    }

    private void threadWork(){
        filename = System.currentTimeMillis() + ".csv";
        filepath = "RecordedSaves";



        if(!isExternalStorageAvailableForRW()){
            Toast.makeText(this, "FileSystem is not readable/writable!", Toast.LENGTH_SHORT).show();
        }else{
            fileContent = arrayToString(arraysToWrite);
            if(!fileContent.equals("")) {
                File fileToWrite = new File(getExternalFilesDir(filepath),filename);
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream((fileToWrite));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                try {

                    fos.write(fileContent.getBytes(StandardCharsets.UTF_8));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        fos.close();
                        fileContent = "";
                        Log.e("SavedSD", "Information saved to your SD card");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        arraysToWrite.clear();
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static boolean isExternalStorageAvailableForRW() {
        String extStorageState = Environment.getExternalStorageState();
        if(extStorageState.equals(Environment.MEDIA_MOUNTED)){
            return true;
        }
        return false;
    }

    public static String arrayToString(ArrayList<float[]> arrayList){
        String stringToSend = "";
        for(float[] inside : arrayList){
            stringToSend = stringToSend + inside[0] + "," + inside[1] + "," + inside[2] + "," + inside[3] + "\n";
        }
        return stringToSend;
    }

    public static String arrayToString(ArrayList<float[]> arrayList, String name){
        String stringToSend = "";
        for(float[] inside : arrayList){
            stringToSend = stringToSend + inside[0] + "," + inside[1] + "," + inside[2] + "," + inside[3] + "," + inside[4] + "," + inside[5] + "," + name+  "\n";
        }
        return stringToSend;
    }

}
