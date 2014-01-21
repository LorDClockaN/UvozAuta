package com.lordclockan.uvozauta;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinnerBenzin, spinnerStarost;
    EditText priceBenzinTxt;
    TextView txtRezultat;
    public int spnBenzinValue = 0, spnDizelValue = 0, co2BenzinSwitch = 0;
    public double sum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

        spinnerBenzin = (Spinner) findViewById(R.id.spnBenzin);
        spinnerStarost = (Spinner) findViewById(R.id.spnStarost);
        priceBenzinTxt = (EditText) findViewById(R.id.txtPriceBenzin);
        txtRezultat = (TextView) findViewById(R.id.txtRezultat);

        ArrayAdapter adapterBenzin = ArrayAdapter.createFromResource(this, R.array.co2_benzin, android.R.layout.simple_spinner_dropdown_item);
        spinnerBenzin.setAdapter(adapterBenzin);
        spinnerBenzin.setOnItemSelectedListener(this);

        final ArrayAdapter adapterStarost = ArrayAdapter.createFromResource(this, R.array.starostVozila, android.R.layout.simple_spinner_dropdown_item);
        spinnerStarost.setAdapter(adapterStarost);
        spinnerStarost.setOnItemSelectedListener(this);

        final Button addBtnBenzin = (Button) findViewById(R.id.btnBenzin);

       addBtnBenzin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               int in = Integer.valueOf(priceBenzinTxt.getText().toString());

               sum = ((in / 100) * spnBenzinValue) + in;
               String krajnjiRezultat = String.valueOf(sum);
               txtRezultat.setText(krajnjiRezultat + " €");
               Toast.makeText(getApplicationContext(), "Izračunaj!!!" + sum, Toast.LENGTH_SHORT).show();
           }
       });

        priceBenzinTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                addBtnBenzin.setEnabled(!priceBenzinTxt.getText().toString().trim().isEmpty());
            }

            @Override
            public void afterTextChanged(Editable editable) {



            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        TextView myText = (TextView) view;

        spnBenzinValue = spinnerBenzin.getSelectedItemPosition();

        switch (spnBenzinValue) {
            case 0:
                spnBenzinValue = 0;
                break;
            case 1:
                spnBenzinValue = 1;
                break;
            case 2:
                spnBenzinValue = 2;
                break;
            case 3:
                spnBenzinValue = 3;
                break;
            case 4:
                spnBenzinValue = 6;
                break;
            case 5:
                spnBenzinValue = 10;
                break;
            case 6:
                spnBenzinValue = 14;
                break;
            case 7:
                spnBenzinValue = 16;
                break;
            case 8:
                spnBenzinValue = 18;
                break;
            case 9:
                spnBenzinValue = 21;
                break;
            case 10:
                spnBenzinValue = 23;
                break;
            case 11:
                spnBenzinValue = 27;
                break;
            case 12:
                spnBenzinValue = 29;
                break;
            default:
                spnBenzinValue = 0;
        }

        Toast.makeText(this, "Izabrali ste " + myText.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}
