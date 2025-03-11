package com.noname.calender;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.Month;
import java.util.*;

public class Calender extends AppCompatActivity {
    Calendar CalendarClass = Calendar.getInstance();

    String[] Months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    int SelectedMonthID = CalendarClass.get(Calendar.MONTH);
    int Year = CalendarClass.get(Calendar.YEAR);

    public void switchMonth(boolean isLeft, TextView monthDisplay)
    {
        if (isLeft)
        {
            CalendarClass.add(Calendar.MONTH, -1);
        }
        else
        {
            CalendarClass.add(Calendar.MONTH, 1);
        }
        monthDisplay.setText(Months[CalendarClass.get(Calendar.MONTH)] + " " + CalendarClass.get(Calendar.YEAR));
        updateDays();
    }

    public void updateDays()
    {
        Calendar TempCalendarClass = Calendar.getInstance();
        TempCalendarClass.set(Calendar.MONTH, CalendarClass.get(Calendar.MONTH));
        TempCalendarClass.set(Calendar.DATE, 1);
        int Offset = -(TempCalendarClass.get(Calendar.DAY_OF_WEEK))+1;
        for (int w = 1; w <= 42; w++)
        {
            int resID = getResources().getIdentifier("day" + w, "id", getPackageName());
            TextView DayButton = findViewById(resID);
            int Value = w+Offset;
            if (Value <= 0)
            {
                TempCalendarClass.add(Calendar.MONTH, -1);
                Value = TempCalendarClass.getActualMaximum(Calendar.DATE) + Value;
                TempCalendarClass.add(Calendar.MONTH, 1);
            }
            else if (Value > CalendarClass.getActualMaximum(Calendar.DATE))
            {
                Value = Value - CalendarClass.getActualMaximum(Calendar.DATE);
            }
            DayButton.setText(Integer.toString(Value));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.calender);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView MonthDisplay = findViewById(R.id.MonthDisplay);
        ImageButton leftMonth = findViewById(R.id.switchMonth1);
        ImageButton rightMonth = findViewById(R.id.switchMonth2);

        MonthDisplay.setText(Months[CalendarClass.get(Calendar.MONTH)] + " " + CalendarClass.get(Calendar.YEAR));

        leftMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchMonth(true, MonthDisplay);
            }
        });

        rightMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchMonth(false, MonthDisplay);
            }
        });

        updateDays();


    }
}