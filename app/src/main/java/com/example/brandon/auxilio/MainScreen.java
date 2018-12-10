package com.example.brandon.auxilio;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainScreen extends AppCompatActivity implements View.OnClickListener {

    private Button helperButton;
    private Button helpButton;
    private SharedPreferences existentialism;

    public User me = new User("", false, 0, 0, 0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_);

        helpButton = (Button) findViewById(R.id.help_button);
        helperButton = (Button) findViewById(R.id.become_helper);

        helperButton.setOnClickListener(this);
        helpButton.setOnClickListener(this);

        existentialism = getSharedPreferences("SomePrefsIGuess", 0);


    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.become_helper:
                if(existentialism.getBoolean("hasdone", false)) {
                    Bundle args = new Bundle();
                    args.putParcelable("meParc", me);
                    Intent myIntent = new Intent(MainScreen.this, Helper.class);
                    myIntent.putExtra("meParc", args);
                    MainScreen.this.startActivity(myIntent);
                    break;

                }else {

                    Bundle args = new Bundle();
                    args.putParcelable("meParc", me);
                    Intent myIntent = new Intent(MainScreen.this, HelperSignUp.class);
                    myIntent.putExtra("meParc", args);
                    MainScreen.this.startActivity(myIntent);
                    break;
                }

            case R.id.help_button:
                Bundle args = new Bundle();
                args.putParcelable("meParc", me);
                Intent myIntent = new Intent(MainScreen.this, MapsActivity.class);
                myIntent.putExtra("meParc", args);
                MainScreen.this.startActivity(myIntent);
                break;


        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_, menu);
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
