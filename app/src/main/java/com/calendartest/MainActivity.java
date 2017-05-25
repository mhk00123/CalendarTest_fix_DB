package com.calendartest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    MaterialCalendarView mcv;   //月曆物件
    Calendar c; //時間物件
    ListView event_list;    //活動清單物件
    ArrayList<String> event_array;  //活動內容陣列
    ArrayAdapter<String> myAd;  //清單內容排版物件，可自訂清單內容與排版

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toAdd = new Intent(MainActivity.this, AddEventActivity.class);
                toAdd.putExtra("Date", event_list.getItemAtPosition(0).toString());
//                String currentTime = String.valueOf(c.get(Calendar.HOUR_OF_DAY)) + "時" + String.valueOf(c.get(Calendar.MINUTE)) + "分";
//                toAdd.putExtra("Time", currentTime);
                startActivityForResult(toAdd, 200);
            }
        });

        setTitle("Calendar");

        mcv = (MaterialCalendarView) findViewById(R.id.calendarView); //物件實體化
        c = Calendar.getInstance(); //取得目前時間
        mcv.setSelectedDate(c); //預選目前時間

        event_list = (ListView) findViewById(R.id.eventList);   //活動清單物件實體化
        refreshList(c);

        mcv.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                event_array = new ArrayList<>();
                String event_date = date.getYear() + "年" + (date.getMonth() + 1) + "月" + date.getDay() + "日";
                event_array.add(event_date);
                myAd = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, event_array);
                event_list.setAdapter(myAd);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        switch (id) {
            case R.id.mode_month:
                mcv.state().edit().setCalendarDisplayMode(CalendarMode.MONTHS).commit();
                break;
            case R.id.mode_week:
                mcv.state().edit().setCalendarDisplayMode(CalendarMode.WEEKS).commit();
                break;
            case R.id.action_today:
                mcv.setCurrentDate(c);
                mcv.setSelectedDate(c);
                refreshList(c);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 200 && resultCode == RESULT_OK) {
            event_array.add(data.getExtras().getString("名稱"));
            event_array.add(data.getExtras().getString("地點"));
            event_array.add(data.getExtras().getString("開始日期"));
            event_array.add(data.getExtras().getString("結束日期"));
            event_array.add(data.getExtras().getString("重複頻率"));
            event_array.add(data.getExtras().getString("隱私權"));
            event_array.add(data.getExtras().getString("提醒"));
            event_array.add(data.getExtras().getString("說明"));
            myAd = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, event_array);
            event_list.setAdapter(myAd);
        }
    }

    void refreshList(Calendar cal) {
        String date = cal.get(Calendar.YEAR) + "年" + (cal.get(Calendar.MONTH) + 1) + "月" + cal.get(Calendar.DAY_OF_MONTH) + "日";
        event_array = new ArrayList<>();
        event_array.add(date);
        myAd = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, event_array);
        event_list.setAdapter(myAd);
    }


}
