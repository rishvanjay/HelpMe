package com.example.brandon.auxilio;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HelperSignUp extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private Button confirmButton;
    private EditText addressEdit;
    private SharedPreferences existentialism;
    //private static final String PREFS_NAME = "MorePrefsIGuess";
    private boolean clickyclicky = false;
    public User me;



   /* public boolean getSp(){

        //boolean hasDone = existentialism.getBoolean("hasdone", false);
        if(clickyclicky) {
            return existentialism.getBoolean("hasdone", false);
        }else{
            return false;
        }
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        me = intent.getExtras().getBundle("meParc").getParcelable("meParc");

        setContentView(R.layout.activity_helper_sign_up);

        //existentialism = getSharedPreferences(PREFS_NAME,0);


        Spinner housingSpinner = (Spinner) findViewById(R.id.housing_spinner);
        Spinner foodSpinner = (Spinner) findViewById(R.id.food_spinner);
        Spinner medicalSpinner = (Spinner) findViewById(R.id.medical_spinner);


        housingSpinner.setOnItemSelectedListener(this);
        foodSpinner.setOnItemSelectedListener(this);
        medicalSpinner.setOnItemSelectedListener(this);

        List<String> housingCategories = new ArrayList<>();
        housingCategories.add("None");
        housingCategories.add("1 Bed");
        housingCategories.add("1 Bed Overnight                                ");
        housingCategories.add("2 Bed");
        housingCategories.add("2 Bed Overnight");

        List<String> foodCategories = new ArrayList<>();
        foodCategories.add("None");
        foodCategories.add("Snacks                                               ");
        foodCategories.add("Meals");

        List<String> medicalCategories = new ArrayList<>();
        medicalCategories.add("None");
        medicalCategories.add("Basic Care - Sprains and Irritation");
        medicalCategories.add("Advanced Care - Cuts and Illness");

        ArrayAdapter<String> housingDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, housingCategories);
        ArrayAdapter<String> foodDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, foodCategories);
        ArrayAdapter<String> medicalDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, medicalCategories);

        housingDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foodDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        medicalDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        housingSpinner.setAdapter(housingDataAdapter);
        foodSpinner.setAdapter(foodDataAdapter);
        medicalSpinner.setAdapter(medicalDataAdapter);

        housingSpinner.setSelection(me.getHousing());
        foodSpinner.setSelection(me.getFood());
        medicalSpinner.setSelection(me.getFood());



        addressEdit = (EditText) findViewById(R.id.address_text);
        addressEdit.setText(me.getAddress());


        confirmButton = (Button) findViewById(R.id.register_button);
        confirmButton.setOnClickListener(this);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        if (parent.getItemAtPosition(1).toString().equals("1 Bed")) {
            me.setHousing(position);
        }
        if (parent.getItemAtPosition(1).toString().equals("Snacks                                               ")) {
            me.setFood(position);
        }
        if (parent.getItemAtPosition(1).toString().equals("Basic Care - Sprains and Irritation")) {
            me.setMedical(position);
        }
        System.out.println(view);


        // Showing selected spinner item
        //   Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        addressEdit.setError(null);
        View focusView = null;
        boolean cancel = false;

        if(addressEdit.getText().toString().isEmpty()){
            addressEdit.setError(getString(R.string.error_field_required));
            focusView = addressEdit;
            cancel = true;
        }

        if(cancel){
            focusView.requestFocus();
        }else{
            SharedPreferences existentialism2 = getSharedPreferences("SomePrefsIGuess", 0);
            SharedPreferences.Editor editor = existentialism2.edit();
            editor.putBoolean("hasdone", true);
            editor.commit();

            me.setAddress(addressEdit.getText().toString());

            System.out.println(existentialism2.getBoolean("hasdone", false));
            clickyclicky = true;




            Bundle args = new Bundle();
            args.putParcelable("meParc", me);
            Intent myIntent = new Intent(HelperSignUp.this, Helper.class);
            myIntent.putExtra("meParc", args);
            HelperSignUp.this.startActivity(myIntent);
        }

    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_helper_sign_up, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}



