package edu.usc.clicker.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import edu.usc.clicker.R;
import edu.usc.clicker.util.ClickerLog;

public class ResponseActivity extends AppCompatActivity implements LocationListener {
    protected LocationManager locationManager;

    protected Location lastLocation;

    public boolean hasLastLocation() {
        return lastLocation != null;
    }

    public Location getLastLocation() {
        return lastLocation;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        registerLocationListener();
    }

    private void registerLocationListener() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                final AlertDialog permissionsAlert = new AlertDialog.Builder(this).setTitle(R.string.permissions_request_title).setMessage(R.string.permissions_request_message).setPositiveButton(R.string.permissions_request_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                    }
                }).create();

                permissionsAlert.show();
            }
        } else {
            onRequestPermissionsResult(0, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, new int[]{PackageManager.PERMISSION_GRANTED, PackageManager.PERMISSION_GRANTED});
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, ResponseActivity.this);
            lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        } catch (SecurityException se) {
            registerLocationListener();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        lastLocation = location;
        ClickerLog.d("ResponseActivity", "current location: " + location.toString());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
