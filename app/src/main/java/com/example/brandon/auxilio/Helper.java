package com.example.brandon.auxilio;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class Helper extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener{

    private ToggleButton helperToggle;
    private Button editHelper;
    public User me;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        me = intent.getExtras().getBundle("meParc").getParcelable("meParc");

        setContentView(R.layout.activity_helper);

        editHelper = (Button) findViewById(R.id.edit_help);

        helperToggle = (ToggleButton) findViewById(R.id.toggle_button);
        helperToggle.setBackgroundColor(Color.RED);

        editHelper.setOnClickListener(this);
        //helperToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener();

        helperToggle.setOnCheckedChangeListener(this);




    }

    private void Notify(String notificationTitle, String notificationMessage) {
        /*NotificationManager mNotificationManager   = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder  = new NotificationCompat.Builder(this);
        //mBuilder.setSmallIcon(R.drawable.notification_icon);
        mBuilder.setContentTitle(notificationTitle);
        mBuilder.setContentText(notificationMessage);
        // notificationID allows you to update the notification later on.
        mNotificationManager.notify(1, mBuilder.build());*/

        Notification n  = new Notification.Builder(this)
                .setContentTitle("New mail from " + "test@gmail.com")
                .setContentText("Subject")
                .setAutoCancel(true).build();


        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        notificationManager.notify(0, n);

        System.out.println("Notification");
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            // The toggle is enabled
            helperToggle.setBackgroundColor(Color.parseColor("#13CF2F"));
            helperToggle.setText("You Are Available To Help");
            Notify("Test", "Test");



        } else {
            // The toggle is disabled
            helperToggle.setBackgroundColor(Color.RED);
            helperToggle.setText("You Cannot Help Right Now");
        }
    }





    @Override
    public void onClick(View v) {
        Bundle args = new Bundle();
        args.putParcelable("meParc", me);
        Intent myIntent = new Intent(Helper.this, HelperSignUp.class);
        myIntent.putExtra("meParc", args);
        Helper.this.startActivity(myIntent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_helper, menu);
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
