package com.example.kushagra.first;

import android.*;
import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

/**
 * Created by SAUROV SARKAR on 22-03-2018.
 */

public class gpslocation  implements LocationListener  {

    Context c;
    public gpslocation (Context context) {
        c = context;
    }


    public Location getlocation()
    {
        if(ContextCompat.checkSelfPermission(c, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
        {
            Toast.makeText(c,"Permission not granted !",Toast.LENGTH_SHORT).show();
            return null;
        }


        LocationManager lm= (LocationManager)c.getSystemService(Context.LOCATION_SERVICE);
        boolean isEnabled=lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if(isEnabled)
        {

            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,this);

            Location l=lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            /*Intent intent = new Intent("com.example.kushagra.first.complaints");
            PendingIntent pendingIntent= PendingIntent.getBroadcast(c,-1,intent,0);
            lm.addProximityAlert(12.90125,77.50278,3000,5000,pendingIntent);
             s=py.present();*/



            return l;

        }
        else
        {
            Toast.makeText(c,"PLEASE ENABLE GPS SERVICE!",Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    /*public boolean getstate()
    {
        return (s);
    }*/






    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {



    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}