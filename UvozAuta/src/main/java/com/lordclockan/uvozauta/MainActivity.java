package com.lordclockan.uvozauta;

import android.content.Intent;
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

    Spinner spinnerBenzin, spinnerDizel;
    EditText txtPriceBenzin, txtPriceDizel;
    TextView txtRezultatBenzin, txtTrosarinaBenzin, txtRezultatDizel, txtTrosarinaDizel, lblAuthor;
    public int spnBenzinValue = 0, spnDizelValue = 0, trosarinaPostotakBenzin = 0,
            trosarinaPostotakDizel = 0;
    public double sum_co2Benzin = 0, benzinValueReturn = 0, sum_co2Dizel = 0, dizelValueReturn = 0;
    public float mTrosarinaRezultatBenzin = 0, mKrajnjiRezultatBenzin = 0,
            mTrosarinaRezultatDizel = 0, mKrajnjiRezultatDizel = 0;

    // Calculator part
    EditText mnumber1text, mnumber2text;
    TextView mtotal;
    Button mclear, msummarize, mminus, mdivide, mmultiply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

        addListenerOnAuthor();

        spinnerBenzin = (Spinner) findViewById(R.id.spnBenzin);
        spinnerDizel = (Spinner) findViewById(R.id.spnDizel);
       // spinnerStarost = (Spinner) findViewById(R.id.spnStarost);

        txtPriceBenzin = (EditText) findViewById(R.id.txtPriceBenzin);
        txtRezultatBenzin = (TextView) findViewById(R.id.txtRezultatBenzin);
        txtTrosarinaBenzin = (TextView) findViewById(R.id.txtTrosarinaBenzin);

        txtPriceDizel = (EditText) findViewById(R.id.txtPriceDizel);
        txtRezultatDizel = (TextView) findViewById(R.id.txtRezultatDizel);
        txtTrosarinaDizel = (TextView) findViewById(R.id.txtTrosarinaDizel);

        // Calculator
        mnumber1text=(EditText)findViewById(R.id.num1text);
        mnumber2text=(EditText)findViewById(R.id.num2text);
        mtotal = (TextView)findViewById(R.id.total);
        mclear = (Button)findViewById(R.id.clear);



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

        tabSpec = tabHost.newTabSpec("calculator");
        tabSpec.setContent(R.id.tabCalculator);
        tabSpec.setIndicator("Calculator");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("about");
        tabSpec.setContent(R.id.tabAbut);
        tabSpec.setIndicator("Info");
        tabHost.addTab(tabSpec);



        ArrayAdapter adapterBenzin = ArrayAdapter.createFromResource(this, R.array.co2_benzin, android.R.layout.simple_spinner_dropdown_item);
        spinnerBenzin.setAdapter(adapterBenzin);
        spinnerBenzin.setOnItemSelectedListener(this);

        ArrayAdapter adapterDizel = ArrayAdapter.createFromResource(this, R.array. co2_dizel, android.R.layout.simple_spinner_dropdown_item);
        spinnerDizel.setAdapter(adapterDizel);
        spinnerDizel.setOnItemSelectedListener(this);

        /* ArrayAdapter adapterStarost = ArrayAdapter.createFromResource(this, R.array.starostVozila, android.R.layout.simple_spinner_dropdown_item);
        spinnerStarost.setAdapter(adapterStarost);
        spinnerStarost.setOnItemSelectedListener(this); */

        final Button addBtnBenzin = (Button) findViewById(R.id.btnBenzin);
        final Button addBtnDizel = (Button) findViewById(R.id.btnDizel);

        addBtnBenzin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               int inPriceBenzin = Integer.valueOf(txtPriceBenzin.getText().toString());

               // poreznaOsnovica();

               sum_co2Benzin = (float) inPriceBenzin * benzinValueReturn;
               trosarinaPostotakBenzin = (int) (benzinValueReturn * 100);
               mTrosarinaRezultatBenzin = (float) sum_co2Benzin;
               mKrajnjiRezultatBenzin = inPriceBenzin + mTrosarinaRezultatBenzin;

               String krajnjiRezultatBenzin = String.valueOf(mKrajnjiRezultatBenzin);
               String trosarinaRezultatBenzin = String.valueOf(mTrosarinaRezultatBenzin);
               txtTrosarinaBenzin.setText(trosarinaPostotakBenzin + " % - " + trosarinaRezultatBenzin + " kn");
               txtRezultatBenzin.setText(krajnjiRezultatBenzin + " kn");

               // Toast.makeText(getApplicationContext(), "Izračunaj!!!" + mKrajnjiRezultat, Toast.LENGTH_SHORT).show();
           }
        });

        addBtnDizel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int inPriceDizel = Integer.valueOf(txtPriceDizel.getText().toString());

                sum_co2Dizel = (float) inPriceDizel * dizelValueReturn;
                trosarinaPostotakDizel = (int) (dizelValueReturn * 100);
                mTrosarinaRezultatDizel = (float) sum_co2Dizel;
                mKrajnjiRezultatDizel = inPriceDizel + mTrosarinaRezultatDizel;

                String krajnjiRezultatDizel = String.valueOf(mKrajnjiRezultatDizel);
                String trosarinaRezultatDizel = String.valueOf(mTrosarinaRezultatDizel);
                txtTrosarinaDizel.setText(trosarinaPostotakDizel + " % - " + trosarinaRezultatDizel + " kn");
                txtRezultatDizel.setText(krajnjiRezultatDizel + " kn");
            }
        });

        txtPriceBenzin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (txtPriceBenzin != null) {
                    addBtnBenzin.setEnabled(!txtPriceBenzin.getText().toString().trim().isEmpty());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

        txtPriceDizel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (txtPriceDizel != null) {
                    addBtnDizel.setEnabled(!txtPriceDizel.getText().toString().trim().isEmpty());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

     /*   lblAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendEmail = new Intent(Intent.ACTION_SEND, Uri.parse("davor@losinj.com"));
                sendEmail.putExtra(Intent.EXTRA_SUBJECT, "Unos Auta INFO");
                startActivity(sendEmail);
            }
        });*/

    }

    // Calculator
    public void summarize(View v) {
        if (mnumber1text.getText().toString().isEmpty() ||
                mnumber2text.getText().toString().isEmpty()){
            Toast msg = Toast.makeText(getBaseContext(), "Morate upisati brojeve!",
                    Toast.LENGTH_LONG);
            msg.show();
        } else {
            Double num1 = Double.parseDouble(mnumber1text.getText().toString());
            Double num2 = Double.parseDouble(mnumber2text.getText().toString());
            Double tot=num1+num2;

            mtotal.setText("Total: " + tot.toString());
        }
    }

    public void minus(View v) {
        if (mnumber1text.getText().toString().isEmpty() ||
                mnumber2text.getText().toString().isEmpty()){
            Toast msg = Toast.makeText(getBaseContext(), "Morate upisati brojeve!",
                    Toast.LENGTH_LONG);
            msg.show();
        } else {
            Double num1 = Double.parseDouble(mnumber1text.getText().toString());
            Double num2 = Double.parseDouble(mnumber2text.getText().toString());
            Double tot=num1-num2;

            mtotal.setText("Total: " + tot.toString());
        }
    }

    public void multiply(View v) {
        if (mnumber1text.getText().toString().isEmpty() ||
                mnumber2text.getText().toString().isEmpty()){
            Toast msg = Toast.makeText(getBaseContext(), "Morate upisati brojeve!",
                    Toast.LENGTH_LONG);
            msg.show();
        } else {
            Double num1 = Double.parseDouble(mnumber1text.getText().toString());
            Double num2 = Double.parseDouble(mnumber2text.getText().toString());
            Double tot=num1*num2;

            mtotal.setText("Total: " + tot.toString());
        }
    }

    public void divide(View v) {
        if (mnumber1text.getText().toString().isEmpty() ||
                mnumber2text.getText().toString().isEmpty()){
            Toast msg1 = Toast.makeText(getBaseContext(), "Morate upisati brojeve!",
                    Toast.LENGTH_LONG);
            msg1.show();
        } else if (mnumber2text.getText().toString().contentEquals("0") ||
                mnumber2text.getText().toString().contentEquals("0.0")) {
            Toast msg2 = Toast.makeText(getBaseContext(), "Ne možete dijeliti sa NULOM!!",
                    Toast.LENGTH_LONG);
            msg2.show();
        } else {
            Double num1 = Double.parseDouble(mnumber1text.getText().toString());
            Double num2 = Double.parseDouble(mnumber2text.getText().toString());
            Double tot=num1 / num2;

            mtotal.setText("Total: " + tot.toString());
        }
    }

    public void addListenerOnAuthor() {

         lblAuthor = (TextView) findViewById(R.id.lblInfo_Author);

         lblAuthor.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent emailIntent =
                        new Intent(Intent.ACTION_SEND);
                String[] recipients = new String[]{"davor@losinj.com", "",};
                emailIntent.putExtra(Intent.EXTRA_EMAIL, recipients);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Unos Auta app info");
                emailIntent.setType("text/plain");
                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                finish();

            }

        });

    }

 /*   public void poreznaOsnovica() {
        int in = Integer.valueOf(priceBenzinTxt.getText().toString());

        if ( in >= 0 ) {
            if (in > 0 && in <= 100000) {
                mPoreznaOsnovica = 0.01;
            } else if (in > 100000 && in <= 150000) {
                mPoreznaOsnovica = 0.02;
            } else if (in > 150000 && in <= 200000) {
                mPoreznaOsnovica = 0.04;
            } else if (in > 200000 && in <= 250000) {
                mPoreznaOsnovica = 0.06;
            } else if (in > 250000 && in <= 300000) {
                mPoreznaOsnovica = 0.07;
            } else if (in > 300000 && in <= 350000) {
                mPoreznaOsnovica = 0.08;
            } else if (in > 350000 && in <= 400000) {
                mPoreznaOsnovica = 0.09;
            } else if (in > 400000 && in <= 450000) {
                mPoreznaOsnovica = 0.11;
            } else if (in > 450000 && in <= 500000) {
                mPoreznaOsnovica = 0.12;
            } else if (in > 500000) {
                mPoreznaOsnovica = 0.14;
            } else {
                mPoreznaOsnovica = 0;
            }
        }
    } */


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
        spnDizelValue = spinnerDizel.getSelectedItemPosition();
        // spnStarostValue = spinnerStarost.getSelectedItemPosition();

        switch (spnBenzinValue) {
            case 0:
                benzinValueReturn = 0.0;
                break;
            case 1:
                benzinValueReturn = 0.015;
                break;
            case 2:
                benzinValueReturn = 0.025;
                break;
            case 3:
                benzinValueReturn = 0.035;
                break;
            case 4:
                benzinValueReturn = 0.07;
                break;
            case 5:
                benzinValueReturn = 0.115;
                break;
            case 6:
                benzinValueReturn = 0.16;
                break;
            case 7:
                benzinValueReturn = 0.18;
                break;
            case 8:
                benzinValueReturn = 0.20;
                break;
            case 9:
                benzinValueReturn = 0.23;
                break;
            case 10:
                benzinValueReturn = 0.27;
                break;
            case 11:
                benzinValueReturn = 0.29;
                break;
            case 12:
                benzinValueReturn = 0.31;
                break;
            default:
                benzinValueReturn = 0;
        }

        switch (spnDizelValue) {
            case 0:
                dizelValueReturn = 0.0;
                break;
            case 1:
                dizelValueReturn = 0.01;
                break;
            case 2:
                dizelValueReturn = 0.02;
                break;
            case 3:
                dizelValueReturn = 0.03;
                break;
            case 4:
                dizelValueReturn = 0.06;
                break;
            case 5:
                dizelValueReturn = 0.10;
                break;
            case 6:
                dizelValueReturn = 0.14;
                break;
            case 7:
                dizelValueReturn = 0.16;
                break;
            case 8:
                dizelValueReturn = 0.18;
                break;
            case 9:
                dizelValueReturn = 0.21;
                break;
            case 10:
                dizelValueReturn = 0.23;
                break;
            case 11:
                dizelValueReturn = 0.27;
                break;
            case 12:
                dizelValueReturn = 0.29;
                break;
            default:
                dizelValueReturn = 0;
        }

        /* switch (spnStarostValue) {
            case 0:
                starostReturn = 0;
                break;
            case 1:
                starostReturn = 0.03;
                break;
            case 2:
                starostReturn = 0.06;
                break;
            case 3:
                starostReturn = 0.09;
                break;
            case 4:
                starostReturn = 0.11;
                break;
            case 5:
                starostReturn = 0.13;
                break;
            case 6:
                starostReturn = 0.15;
                break;
            case 7:
                starostReturn = 0.16;
                break;
            case 8:
                starostReturn = 0.17;
                break;
            case 9:
                starostReturn = 0.18;
                break;
            case 10:
                starostReturn = 0.19;
                break;
            case 11:
                starostReturn = 0.20;
                break;
            case 12:
                starostReturn = 0.23;
                break;
            case 13:
                starostReturn = 0.26;
                break;
            case 14:
                starostReturn = 0.29;
                break;
            case 15:
                starostReturn = 0.31;
                break;
            case 16:
                starostReturn = 0.355;
                break;
            case 17:
                starostReturn = 0.40;
                break;
            case 18:
                starostReturn = 0.44;
                break;
            case 19:
                starostReturn = 0.48;
                break;
            case 20:
                starostReturn = 0.55;
                break;
            case 21:
                starostReturn = 0.61;
                break;
            case 22:
                starostReturn = 0.66;
                break;
            case 23:
                starostReturn = 0.70;
                break;
            case 24:
                starostReturn = 0.73;
                break;
            case 25:
                starostReturn = 0.74;
                break;
            default:
                starostReturn = 0;
        } */

        // Toast.makeText(this, "Izabrali ste " + myText.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        mclear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mnumber1text.setText("");
                mnumber2text.setText("");
                mtotal.setText("Total: ");
            }
        });
    }
}
