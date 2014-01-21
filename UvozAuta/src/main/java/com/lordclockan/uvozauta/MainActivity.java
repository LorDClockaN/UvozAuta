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
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinnerBenzin, spinnerStarost;
    EditText priceBenzinTxt;
    TextView txtRezultat;
    public int spnBenzinValue = 0, spnDizelValue = 0, spnStarostValue = 0, mPoreznaOsnovica = 0;
    public double sum = 0, starostReturn = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

        spinnerBenzin = (Spinner) findViewById(R.id.spnBenzin);
        spinnerStarost = (Spinner) findViewById(R.id.spnStarost);
        priceBenzinTxt = (EditText) findViewById(R.id.txtPriceBenzin);
        txtRezultat = (TextView) findViewById(R.id.txtRezultat);
        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);

        tabHost.setup();
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("benzin");
        tabSpec.setContent(R.id.tabBenzin);
        tabSpec.setIndicator("Benzin");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("benzin");
        tabSpec.setContent(R.id.tabDizel);
        tabSpec.setIndicator("Dizel");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("about");
        tabSpec.setContent(R.id.tabAbut);
        tabSpec.setIndicator("Info");
        tabHost.addTab(tabSpec);



        ArrayAdapter adapterBenzin = ArrayAdapter.createFromResource(this, R.array.co2_benzin, android.R.layout.simple_spinner_dropdown_item);
        spinnerBenzin.setAdapter(adapterBenzin);
        spinnerBenzin.setOnItemSelectedListener(this);

        ArrayAdapter adapterStarost = ArrayAdapter.createFromResource(this, R.array.starostVozila, android.R.layout.simple_spinner_dropdown_item);
        spinnerStarost.setAdapter(adapterStarost);
        spinnerStarost.setOnItemSelectedListener(this);

        final Button addBtnBenzin = (Button) findViewById(R.id.btnBenzin);

        addBtnBenzin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               int inPriceBenzin = Integer.valueOf(priceBenzinTxt.getText().toString());

               poreznaOsnovica();
               sum = ((float) inPriceBenzin / 100) * starostReturn;
               double temp = inPriceBenzin - sum;
               temp += (temp / 100) * mPoreznaOsnovica;
               double mKrajnjiRezultat = temp;
               mKrajnjiRezultat += ((temp / 100)) * spnBenzinValue;

               String krajnjiRezultat = String.valueOf(mKrajnjiRezultat);
               txtRezultat.setText(krajnjiRezultat + " kn");
               Toast.makeText(getApplicationContext(), "Izračunaj!!!" + mKrajnjiRezultat, Toast.LENGTH_SHORT).show();
           }
        });

        priceBenzinTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (priceBenzinTxt != null) {
                    addBtnBenzin.setEnabled(!priceBenzinTxt.getText().toString().trim().isEmpty());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {



            }
        });
    }

    public void poreznaOsnovica() {
        int in = Integer.valueOf(priceBenzinTxt.getText().toString());

        if ( in >= 0 ) {
            if (in > 0 && in <= 100000) {
                mPoreznaOsnovica = 1;
            } else if (in > 100000 && in <= 150000) {
                mPoreznaOsnovica = 2;
            } else if (in > 150000 && in <= 200000) {
                mPoreznaOsnovica = 4;
            } else if (in > 200000 && in <= 250000) {
                mPoreznaOsnovica = 6;
            } else if (in > 250000 && in <= 300000) {
                mPoreznaOsnovica = 7;
            } else if (in > 300000 && in <= 350000) {
                mPoreznaOsnovica = 8;
            } else if (in > 350000 && in <= 400000) {
                mPoreznaOsnovica = 9;
            } else if (in > 400000 && in <= 450000) {
                mPoreznaOsnovica = 11;
            } else if (in > 450000 && in <= 500000) {
                mPoreznaOsnovica = 12;
            } else if (in > 500000) {
                mPoreznaOsnovica = 14;
            } else {
                mPoreznaOsnovica = 0;
            }
        }
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
        spnStarostValue = spinnerStarost.getSelectedItemPosition();

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

        switch (spnStarostValue) {
            case 0:
                starostReturn = 0;
                break;
            case 1:
                starostReturn = 3;
                break;
            case 2:
                starostReturn = 6;
                break;
            case 3:
                starostReturn = 9;
                break;
            case 4:
                starostReturn = 11;
                break;
            case 5:
                starostReturn = 13;
                break;
            case 6:
                starostReturn = 15;
                break;
            case 7:
                starostReturn = 16;
                break;
            case 8:
                starostReturn = 17;
                break;
            case 9:
                starostReturn = 18;
                break;
            case 10:
                starostReturn = 19;
                break;
            case 11:
                starostReturn = 20;
                break;
            case 12:
                starostReturn = 23;
                break;
            case 13:
                starostReturn = 26;
                break;
            case 14:
                starostReturn = 29;
                break;
            case 15:
                starostReturn = 31;
                break;
            case 16:
                starostReturn = 35.5;
                break;
            case 17:
                starostReturn = 40;
                break;
            case 18:
                starostReturn = 44;
                break;
            case 19:
                starostReturn = 48;
                break;
            case 20:
                starostReturn = 55;
                break;
            case 21:
                starostReturn = 61;
                break;
            case 22:
                starostReturn = 66;
                break;
            case 23:
                starostReturn = 70;
                break;
            case 24:
                starostReturn = 73;
                break;
            case 25:
                starostReturn = 74;
                break;
            default:
                starostReturn = 0;
        }

        Toast.makeText(this, "Izabrali ste " + myText.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}
