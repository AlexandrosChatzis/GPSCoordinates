package com.example.alex.gpscoordinates;

import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,LocationListener {

    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    public static final String TAG = MapsActivity.class.getSimpleName();
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        /*LatLng Thessaloniki = new LatLng(40.6436100, 22.9308600);
        mMap.addMarker(new MarkerOptions().position(Thessaloniki).title("Marker in Thessaloniki"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Thessaloniki, 5));

        LatLng Serres = new LatLng(41.0849900, 23.5475700);
        mMap.addMarker(new MarkerOptions().position(Serres).title("Marker in Serres"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Serres, 5));
*/

        //MAGAZIA


        LatLng Mammas_pizza = new LatLng(40.268508, 22.501781);
        mMap.addMarker(new MarkerOptions().position(Mammas_pizza).title("Mammas pizza"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Mammas_pizza, 15));

        LatLng Family = new LatLng(37.963928, 23.648268);
        mMap.addMarker(new MarkerOptions().position(Family).title("Family"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Family, 15));

        LatLng Coffee_island = new LatLng(41.089990, 23.548625);
        mMap.addMarker(new MarkerOptions().position(Coffee_island).title("Coffee_island"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Coffee_island, 15));

        LatLng Kaliteros = new LatLng(41.088124, 23.548523);
        mMap.addMarker(new MarkerOptions().position(Kaliteros).title("Kaliteros"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Kaliteros, 15));

        LatLng Astoria = new LatLng(40.691612, 21.681245);
        mMap.addMarker(new MarkerOptions().position(Astoria).title("Astoria"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Astoria, 15));

        LatLng Erateino = new LatLng(41.089392, 23.545757);
        mMap.addMarker(new MarkerOptions().position(Erateino).title("Erateino"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Erateino, 15));

        LatLng Dimosthenis = new LatLng(41.090035, 23.548703);
        mMap.addMarker(new MarkerOptions().position(Dimosthenis).title("Dimosthenis"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Dimosthenis, 15));


        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
    }


    @Override
    protected void onResume() {
        super.onResume();

        mGoogleApiClient.connect();
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.i(TAG, "Location services connected.");

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (location == null) {
            // Blank for a moment...
        }
        else {
            handleNewLocation(location);
        };

    }

    private void handleNewLocation(Location location) {
        Log.d(TAG, location.toString());
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Location services suspended. Please reconnect.");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        }
        else
        {
            Log.i(TAG, "Location services connection failed with code " + connectionResult.getErrorCode());
        }

    }

    @Override
    public void onLocationChanged(Location location) {

    }
}
