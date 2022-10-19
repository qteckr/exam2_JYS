package com.study.qtecexam2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    CustomAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<item> arrayList;
    TextView tv_label;
    Button button[] = new Button[5];
    Integer[] btn = {R.id.btn_menu0, R.id.btn_menu1, R.id.btn_menu2, R.id.btn_menu3, R.id.btn_menu4};
    long backKeyPressedTime = 0;

    //재료명
    String s_beans = "원두";
    String s_water = "물";
    String s_milk = "우유";
    //초기 재료 수량
    int i_beans = 10000;
    int i_water = 10000;
    int i_milk = 5000;
    //음료 가격
    int i_espresso_cost = 4000;
    int i_latte_cost = 5000;
    int i_americano_cost = 4500;
    //음료 주문 수
    int i_espresso_cnt = 0;
    int i_latte_cnt = 0;
    int i_americano_cnt = 0;

    String s_a = "";
    String s_b= "";
    item cafe = new item(s_a, s_b);

    String s_label_text = "";
    int i_today_cost_sum = 0;

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
        DecimalFormat myFormatter = new DecimalFormat("###,###");

        for (int i = 0; i < button.length; i++) {
            final int INDEX;
            INDEX = i;

            button[INDEX].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    arrayList.clear();

                    if (INDEX == 0) {
                        s_label_text = "재료보고";
                    } else if (INDEX == 1) {
                        cafe = new item("총 수익금 : ", String.valueOf(myFormatter.format(i_today_cost_sum))+" 원");
                        arrayList.add(cafe);
                        cafe = new item("에스프레소(총 " + i_espresso_cnt + "잔) : " , String.valueOf(myFormatter.format(i_espresso_cnt * i_espresso_cost))+" 원");
                        arrayList.add(cafe);
                        cafe = new item("라떼(총 " + i_latte_cnt + "잔) : ", String.valueOf(myFormatter.format(i_latte_cnt * i_latte_cost))+" 원");
                        arrayList.add(cafe);
                        cafe = new item("아메리카노(총 " + i_americano_cnt + "잔) : ", String.valueOf(myFormatter.format(i_americano_cnt * i_americano_cost))+" 원");
                        arrayList.add(cafe);
                        s_label_text = "정산보고";
                    } else if (INDEX == 2) {
                        if(i_beans >= 100 && i_water >= 30){
                            i_beans -= 100;
                            i_water -= 30;
                            i_today_cost_sum += i_espresso_cost;
                            i_espresso_cnt += 1;
                            s_label_text = "에스프레소 주문 완료";
                        }else{
                            onDialog(s_beans);
                        }
                    } else if (INDEX == 3) {
                        if(i_beans >= 100 && i_water >= 70 && i_milk >= 30){
                            i_beans -= 100;
                            i_water -= 70;
                            i_milk -= 30;
                            i_today_cost_sum += i_latte_cost;
                            i_latte_cnt += 1;
                            s_label_text = "라떼 주문 완료";
                        }else{
                            onDialog(s_beans);
                        }
                    } else if (INDEX == 4) {
                        if(i_beans >= 100 && i_water >= 100){
                            i_beans -= 100;
                            i_water -= 100;
                            i_today_cost_sum += i_americano_cost;
                            i_americano_cnt += 1;
                            s_label_text = "아메리카노 주문 완료";
                        }else{
                            onDialog(s_beans);
                        }
                    }
                    if(INDEX == 0 || INDEX == 2 || INDEX == 3 || INDEX == 4){
                        cafe = new item("잔여 원두 : " , String.valueOf(myFormatter.format(i_beans))+" g");
                        arrayList.add(cafe);
                        cafe = new item("잔여 물 : ", String.valueOf(myFormatter.format(i_water))+" ml");
                        arrayList.add(cafe);
                        cafe = new item("잔여 우유 : ", String.valueOf(myFormatter.format(i_milk))+" ml");
                        arrayList.add(cafe);
                    }
                    tv_label.setText(s_label_text);
                    adapter.notifyDataSetChanged();
                    //recyclerView.scrollToPosition(adapter.getItemCount()-1);
                }
            });
        }
    }

    public void onDialog(String material){
        s_label_text = "재고없음";

        AlertDialog.Builder msgBuilder = new AlertDialog.Builder(MainActivity.this)
                .setTitle("재료 없음")
                .setMessage(material + "가 부족합니다. 재고를 확인해주세요.")
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        AlertDialog msgDlg = msgBuilder.create();
        msgDlg.show();
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            Toast.makeText(this, "\'뒤로\' 버튼을 한번 더 누르시면\n앱이 종료됩니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            //액티비티 종료 후
            ActivityCompat.finishAffinity(this);
            //프로세스 종료
            finish();
        }
    }
}