
package com.css490.homework1;

import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * The main activity, contains temperature converter
 * 
 * @author Tim Mikeladze
 * 
 * 
 */
public class MainActivity extends Activity implements OnClickListener {
    
    private EditText fahrenheitEditText;
    private EditText celsiusEditText;
    private Button convertFToCButton;
    private Button convertCToFButton;
    private DecimalFormat decimalFormat;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        fahrenheitEditText = (EditText) findViewById(R.id.edittext_fahrenheit);
        celsiusEditText = (EditText) findViewById(R.id.edittext_celsius);
        
        convertFToCButton = (Button) findViewById(R.id.button_convert_f_to_c);
        convertFToCButton.setOnClickListener(this);
        convertCToFButton = (Button) findViewById(R.id.button_convert_c_to_f);
        convertCToFButton.setOnClickListener(this);
        
        decimalFormat = new DecimalFormat("0.00#");
    }
    
    @Override
    public void onClick(View v) {
        if (v.getId() == convertFToCButton.getId()) {
            convertFToC();
        }
        else if (v.getId() == convertCToFButton.getId()) {
            convertCToF();
        }
    }
    
    private void convertFToC() {
        celsiusEditText.setError(null);
        if (validateTempertureField(fahrenheitEditText)) {
            setTempertureField(convertFToC(Double.parseDouble(fahrenheitEditText.getText()
                                                                                .toString())), celsiusEditText);
        }
    }
    
    private void convertCToF() {
        fahrenheitEditText.setError(null);
        if (validateTempertureField(celsiusEditText)) {
            setTempertureField(convertCToF(Double.parseDouble(celsiusEditText.getText()
                                                                             .toString())), fahrenheitEditText);
        }
    }
    
    private boolean validateTempertureField(EditText editText) {
        boolean valid = true;
        String value = editText.getText()
                               .toString();
        String error = "";
        String toast = "";
        
        if (value.isEmpty()) {
            valid = false;
            error = "Missing value";
            toast = "Please enter a value";
        }
        else if (value.contentEquals(".")) {
            valid = false;
            error = "Incorrect value";
            toast = "Please enter a correct";
        }
        
        if (!valid) {
            editText.setError(error);
            editText.requestFocus();
            Toast.makeText(this, toast, Toast.LENGTH_SHORT)
                 .show();
        }
        
        return valid;
    }
    
    private void setTempertureField(Double temp, EditText editText) {
        editText.setText(decimalFormat.format(temp));
    }
    
    private double convertFToC(double f) {
        return (f + 40) / 1.8 - 40;
        
    }
    
    private double convertCToF(double c) {
        return (c + 40) * 1.8 - 40;
    }
    
}
