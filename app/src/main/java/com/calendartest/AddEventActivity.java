package com.calendartest;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class AddEventActivity extends AppCompatActivity {
    TextView fromDate, toDate;
    EditText eventName, eventLocation, eventDescription;
    Spinner repeatPicker, privacyPicker, remindPicker;
    String repeatFrequency, privacy, remind;
    Intent getData;
    Intent toMain;

    public DBHelper dbHelper;
    public SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        dbHelper = new DBHelper(this, null, null, 1);

        eventName = (EditText) findViewById(R.id.eventName);
        eventLocation = (EditText) findViewById(R.id.eventLocation);
        repeatPicker = (Spinner) findViewById(R.id.repeatPicker);
        privacyPicker = (Spinner) findViewById(R.id.privacyPicker);
        remindPicker = (Spinner) findViewById(R.id.remindPicker);
        eventDescription = (EditText) findViewById(R.id.ecentDescription);

        getData = getIntent();

        fromDate = (TextView) findViewById(R.id.fromDate);
        toDate = (TextView) findViewById(R.id.toDate);

        fromDate.setText(getData.getStringExtra("Date"));
        toDate.setText(getData.getStringExtra("Date"));

        repeatFrequency = repeatPicker.getSelectedItem().toString();
        privacy = privacyPicker.getSelectedItem().toString();
        remind = remindPicker.getSelectedItem().toString();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_event, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addSave:
//                db = dbHelper.getWritableDatabase();
                Bundle values = new Bundle();
                values.putString("名稱", eventName.getText().toString());
                values.putString("地點", eventLocation.getText().toString());
                values.putString("開始日期", fromDate.getText().toString());
                values.putString("結束日期", toDate.getText().toString());
                values.putString("重複頻率", repeatFrequency);
                values.putString("隱私權", privacy);
                values.putString("提醒", remind);
                values.putString("說明", eventDescription.getText().toString());
                toMain = new Intent();
                toMain.putExtras(values);
                setResult(RESULT_OK, toMain);
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

}
