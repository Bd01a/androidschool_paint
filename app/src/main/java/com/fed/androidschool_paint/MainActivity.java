package com.fed.androidschool_paint;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private PaintView paintView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RadioButton pointRadButton = findViewById(R.id.radbutton_point);
        final RadioButton lineRadButton = findViewById(R.id.radbutton_line);
        final RadioButton rectRadButton = findViewById(R.id.radbutton_rect);
        final RadioButton polygonRadButton = findViewById(R.id.radbutton_polygon);

        paintView = findViewById(R.id.paint_view);

        final Button clearButton = findViewById(R.id.button_clear);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paintView.clear();
            }
        });

        final Spinner spinner = findViewById(R.id.spinner_color);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1, paintView.getNamesColorPaints());
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                paintView.setPaint(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        pointRadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paintView.setDrawType(PaintView.DRAW_POINT);
                lineRadButton.setChecked(false);
                rectRadButton.setChecked(false);
                polygonRadButton.setChecked(false);
            }
        });

        lineRadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paintView.setDrawType(PaintView.DRAW_LINE);
                pointRadButton.setChecked(false);
                rectRadButton.setChecked(false);
                polygonRadButton.setChecked(false);
            }
        });

        rectRadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paintView.setDrawType(PaintView.DRAW_RECT);
                lineRadButton.setChecked(false);
                pointRadButton.setChecked(false);
                polygonRadButton.setChecked(false);
            }
        });

        polygonRadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paintView.setDrawType(PaintView.DRAW_POLYGON);
                lineRadButton.setChecked(false);
                pointRadButton.setChecked(false);
                rectRadButton.setChecked(false);
            }
        });

    }

}
