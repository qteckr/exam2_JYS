package com.study.qtecexam2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CustomAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<item> arrayList;
    TextView tv_label;
    Button button[] = new Button[5];
    Integer[] btn = {R.id.btn_menu0, R.id.btn_menu1, R.id.btn_menu2, R.id.btn_menu3, R.id.btn_menu4};
    int a;
    String b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //액션바 제거
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        onInit();
    }

    private void onInit() {
        for (int i = 0; i < button.length; i++) {
            button[i] = (Button) findViewById(btn[i]);
        }
        tv_label = (TextView) findViewById(R.id.tv_label);
        arrayList = new ArrayList<item>();
        adapter = new CustomAdapter(arrayList, this);
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        for (int i = 0; i < button.length; i++) {
            final int INDEX;
            INDEX = i;
            a = INDEX;
            b = String.valueOf(INDEX);
            button[INDEX].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(INDEX == 0){
                        a = 1; b = "1";
                    }else if(INDEX == 1){
                        a = 2; b = "2";
                    }else if(INDEX == 2){
                        a = 3; b = "3";
                    }else if(INDEX == 3){
                        a = 4; b = "4";
                    }else if(INDEX == 4){
                        a = 5; b = "5";
                    }

                    tv_label.setText("메 뉴" + String.valueOf(INDEX+1));
                    item test = new item(a,b);
                    arrayList.add(test);
                    adapter.notifyDataSetChanged();
                }
            });
        }
    }
}