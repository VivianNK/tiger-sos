package com.example.tigersos;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        /*
         * Establish db connection
         */
        MongoClientURI uri = new MongoClientURI(
                "mongodb://<username>:<password>@tigersos-cluster1-shard-00-00-hhhlw.gcp.mongodb.net:27017,tigersos-cluster1-shard-00-01-hhhlw.gcp.mongodb.net:27017,tigersos-cluster1-shard-00-02-hhhlw.gcp.mongodb.net:27017/test?ssl=true&replicaSet=TigerSOS-Cluster1-shard-0&authSource=admin&retryWrites=true&w=majority");

        MongoClient mongoClient = null;

        try {
            mongoClient = new MongoClient(uri);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        assert mongoClient != null;
        DB db = mongoClient.getDB("journaldev");
        DBCollection col = db.getCollection("users");
    }


        //Ari's stuff:

        //Setting Ritchie button and making him disappear
        Button ritchie = findViewById(R.id.ritchie);


        /**
        final LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new MyLocationListener(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //If the user doesn't give GPS permissions, quit.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
        */
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        ritchie.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                //Creating dialog box for confirmation of safety
                dialog.setMessage("Are you in a safe location?");
                dialog.setTitle("");
                dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        //TODO: Take location info
                        //TODO: Text public safety (or Ari)
                    }
                });
                dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        // Do nothing presumably??
                    }
                });

                AlertDialog message = dialog.create();
                message.show();







                //TODO: make dialogue box asking whether or not the user is safe.
                //TODO  if yes, then quit the app
                //TODO  if no, text Public Safety
                //Location Stuff
                //TODO: figure out how to send location
                /*Location recentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                System.out.println("Latitude: " + recentLocation.getLatitude());
                System.out.println("Longitude: " + recentLocation.getLongitude());*/

                //Send text
                //sendSMS();
                }
        });
    }

    private void sendSMS(){
        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(String.valueOf(this)), 0);
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("5705751358", null, "Hi Ari :)", pi, null);

    }

}
