package com.example.unit;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Spinner conversionSpinner;
    private EditText inputEditText;
    private TextView resultTextView;
    private Button convertButton;

    private static final String METER_TO_CENTIMETER = "Meter to Centimeter";
    private static final String CENTIMETER_TO_METER = "Centimeter to Meter";
    private static final String GRAM_TO_KILOGRAM = "gram to kilogram";
    private static final String KILOGRAM_TO_GRAM = "kilogram to gram";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conversionSpinner = findViewById(R.id.conversionSpinner);
        inputEditText = findViewById(R.id.inputEditText);
        resultTextView = findViewById(R.id.resultTextView);
        convertButton = findViewById(R.id.convertButton);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.conversion_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        conversionSpinner.setAdapter(adapter);

        conversionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Clear the result text when the conversion type is changed
                resultTextView.setText("");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convert();
            }
        });
    }

    private void convert() {
        String conversionOption = conversionSpinner.getSelectedItem().toString();
        String inputValue = inputEditText.getText().toString().trim();

        if (!inputValue.isEmpty()) {
            double value = Double.parseDouble(inputValue);
            double result;

            if (conversionOption.equals(METER_TO_CENTIMETER)) {
                result = value * 100;
                resultTextView.setText(value + " meters is equal to " + result + " centimeters.");
            }
            else if (conversionOption.equals(CENTIMETER_TO_METER)) {
                result = value / 100;
                resultTextView.setText(value + " centimeters is equal to " + result + " meters.");
            }
            else if (conversionOption.equals(GRAM_TO_KILOGRAM)) {
                result = value / 100;
                resultTextView.setText(value + " grams is equal to  " + result + " kilograms.");
            }
                 else if (conversionOption.equals(KILOGRAM_TO_GRAM)) {
                    result = value * 100;
                    resultTextView.setText(value + " kilograms is equal to " + result + " grams.");
        }
                 else {
            resultTextView.setText("Please enter a value to convert.");
        }
    }
}}
