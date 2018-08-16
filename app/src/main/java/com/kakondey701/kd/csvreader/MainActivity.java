package com.kakondey701.kd.csvreader;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lv1;
    String[] items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv1 = (ListView)findViewById(R.id.listView);


        final ArrayList<File> csvFIles = findcsvFiles(Environment.getExternalStorageDirectory());

        items = new String[csvFIles.size()];

        for (int i=0; i<csvFIles.size(); i++)
        {/*Toast.makeText(getApplicationContext(),csvFIles.get(i).getName().toString(),Toast.LENGTH_SHORT).show();*/
            items[i] = csvFIles.get(i).getName().toString();
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.csv_layout,R.id.textView,items);
        lv1.setAdapter(arrayAdapter);


        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                startActivity(new Intent(getApplicationContext(),CsvFileOpener.class).putExtra("position",position).putExtra("csvList",csvFIles));
            }
        });


    }

    public ArrayList<File> findcsvFiles(File root)
    {
        ArrayList<File> arrayList = new ArrayList<File>();

        File[] files = root.listFiles();
        for (File singleFIle : files)
        {
            if (singleFIle.isDirectory() && !singleFIle.isHidden())
            {
                arrayList.addAll(findcsvFiles(singleFIle));
            }
            else
            {
                if (singleFIle.getName().endsWith(".csv"))
                {
                    arrayList.add(singleFIle);
                }
            }
        }
        return arrayList;
    }
}
