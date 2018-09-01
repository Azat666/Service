package com.example.student.myservice;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

public class MyIntentService extends Service {
    private LocationManager locationManager;
    private String provider;
    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            Toast.makeText(getApplicationContext(), "Changed.....", Toast.LENGTH_SHORT).show();
            Log.d("HEYYY", "changed");
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
            Log.d("HEYYY", "onStatusChanged");
        }

        @Override
        public void onProviderEnabled(String s) {
            Log.d("HEYYY", "onProviderEnabled");
        }

        @Override
        public void onProviderDisabled(String s) {
            Log.d("HEYYY", "onProviderDisabled");
        }
    };

    public MyIntentService() {

    }

    @SuppressLint("WrongConstant")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return 0;
        }
        locationManager.requestLocationUpdates(provider, 0, 0, locationListener);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);

        provider = locationManager.getBestProvider(criteria, true);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
