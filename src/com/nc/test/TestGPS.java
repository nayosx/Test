package com.nc.test;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class TestGPS extends MapActivity {
	
	MapView mapa;
	MapController control;
	LocationManager manager;
	LocationListener listener;
	TextView t2, t4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_gps);
        t2 = (TextView) findViewById(R.id.TextView02);
        t4 = (TextView) findViewById(R.id.TextView04);
        mapa = (MapView) findViewById(R.id.map);
        mapa.setBuiltInZoomControls(true);
        
        control = mapa.getController();
        manager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        listener = new GPSEstado();
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);
    }
    
    private class GPSEstado implements LocationListener
    {

		public void onLocationChanged(Location location) {
			
			control.animateTo(new GeoPoint((int)(location.getLatitude() * 1E6), (int) (location.getLongitude() * 1E6)));
			control.setZoom(16);
			
			t2.setText(""+location.getLatitude() * 1E6);
			t4.setText(""+location.getLongitude() * 1E6);
			// TODO Auto-generated method stub
			
		}

		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}
    	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_test_gps, menu);
        return true;
    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		Intent i = new Intent("android.location.GPS_ENABLED_CHANGE");
		i.putExtra("enable", false);
		sendBroadcast(i);
	}
	
	
	
}
