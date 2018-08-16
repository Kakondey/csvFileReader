package com.kakondey701.kd.csvreader;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class CsvFileOpener extends AppCompatActivity {

    int position;
    Uri uri;
    private List<Details> detailsList = new ArrayList<>();
    ListView studentListDetailsListView;
    StudentsDetailsListVIewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_details_listview);
        studentListDetailsListView = (ListView)findViewById(R.id.list);


        /*received the csv student_details_listview in budle*/
        Bundle bundle = getIntent().getExtras();

        /*inserted the whole student_details_listview of csv files into csvFiles arraylist*/
        ArrayList<File> csvFiles = (ArrayList) bundle.getParcelableArrayList("csvList");

        /*got the position of selected csv file*/
        position = bundle.getInt("position", 0);

        /*taking the whole student_details_listview of csv files and attached the selected position to it and fed into universal resouce indicator and converted to string.*/
        uri = Uri.parse(csvFiles.get(position).toString());
        Toast.makeText(this, uri.toString(), Toast.LENGTH_SHORT).show();

        readDetails();

        /*adapter = new csvAdapter(this,android.R.layout.simple_list_item_1);*/
        Log.d("TAG", "onCreate:000 ");

    }







    private void readDetails() {
        File file = new File(uri.toString());
        /*try {
            FileInputStream fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/


        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(file), Charset.forName("UTF-8")));

            String line = "";
            try {
                while ((line = reader.readLine()) != null) {
                    //split by ','
                    String[] tokens = line.split(",");

                    //read the data.
                    Details details = new Details();

                    details.setRoll_number(tokens[0]);
                    details.setName(tokens[1]);
                    detailsList.add(details);

                    adapter = new StudentsDetailsListVIewAdapter(this,details.getName(),details.getRoll_number());
                    //final CheckBox checkBox = (CheckBox)findViewById(R.id.check_box);
                    //ArrayAdapter<Details> studentAdapter = new ArrayAdapter<Details>(getApplicationContext(), R.layout.student_details_listview,R.id.studentName,detailsList);
                    studentListDetailsListView.setAdapter(adapter);

                   // Log.d("CsvFileOpener", "Just created "+ details);


                }
            } catch (IOException e) {
                Log.wtf("CsvFileOpener", "Error reading data file" + line, e);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}