package com.sds.testlab.robotlectricsample;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listview ;
        InfoListViewAdapter adapter = new InfoListViewAdapter() ;

        listview = (ListView) findViewById(R.id.list);
        listview.setAdapter(adapter);

        // 첫 번째 아이템 추가.
        adapter.addItem("ListBox 공지합니다.","foo@example.com") ;
        // 두 번째 아이템 추가.
        adapter.addItem("CheckBox 공지합니다.","bar@example.com") ;


    }
}
