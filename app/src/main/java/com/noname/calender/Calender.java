package com.noname.calender;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Calender extends AppCompatActivity {

    String[] Months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    int SelectedMonthID = 1;
    int Year = 2025;

    public void switchMonth(boolean isLeft, TextView monthDisplay)
    {
        if (isLeft)
        {
            SelectedMonthID--;
            if (SelectedMonthID < 1)
            {
                SelectedMonthID = 11;
                Year--;
            }
        }
        else
        {
            SelectedMonthID++;
            if (SelectedMonthID > 11)
            {
                SelectedMonthID = 0;
                Year++;
            }
        }
        monthDisplay.setText(Months[SelectedMonthID] + " " + Year);
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
    }
}