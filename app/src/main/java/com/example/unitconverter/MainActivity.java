package com.example.unitconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.*;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final String[] physicalQuantities =
            {
                    "Length",

            };

    private final String[] lengthUnits = new String[Length.values().length];


    public void setup() {
        for (int i = 0; i < lengthUnits.length; i++) {
            lengthUnits[i] = Length.values()[i].name();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
        final Spinner quantitySelector = (Spinner) findViewById(R.id.quantitySelector);
        final Spinner fromBox = (Spinner) findViewById(R.id.fromBox);
        final Spinner toBox = (Spinner) findViewById(R.id.toBox);
        final EditText fromNumber = (EditText) findViewById(R.id.fromNumber);
        final TextView toNumber = (TextView) findViewById(R.id.toNumber);
        final Button convert = (Button) findViewById(R.id.convert);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                physicalQuantities);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final ArrayAdapter<String> lengthAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                lengthUnits);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhysicalQuantity from = null, to = null;
                String item = (String) quantitySelector.getSelectedItem();
                double input = Double.parseDouble(fromNumber.getText().toString());
                switch(item) {
                    case "Length":
                        from = Length.valueOf((String) fromBox.getSelectedItem());
                        to = Length.valueOf((String) toBox.getSelectedItem());
                        break;

                }
                double finalData = Converter.convert(input, from, to);
                String displayVal = String.format("%4.4f", finalData);
                toNumber.setText(displayVal);
            }
        });

        quantitySelector.setAdapter(adapter);

        quantitySelector.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                String item = (String) quantitySelector.getSelectedItem();

                switch(item) {
                    case "Length":
                        fromBox.setAdapter(lengthAdapter);
                        toBox.setAdapter(lengthAdapter);
                        break;
                }
                fromNumber.setText(0 + "");
                toNumber.setText(null);
            }
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}
