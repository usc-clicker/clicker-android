package edu.usc.clicker.util;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class LocationHelper implements LocationListener {
    public final LocationManager manager;
    private final int FIVE_SECONDS = 5000;
    private Location bestLocation;
    private boolean hasLocation = false;
    private boolean trackLocation = false;
    private LocationHelperListener listener;

    @Override
    public void onLocationChanged(Location location) {
        hasLocation = true;
        if (isBetterLocation(location, bestLocation)) {
            bestLocation = location;
        }

        if (listener != null) {
            listener.locationStatusChanged(true);
        }
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

    public Location getBestLocation() {
        return bestLocation;
    }

    public boolean hasLocation() {
        return hasLocation;
    }

    public void setLocationHelperListener(LocationHelperListener listener) {
        this.listener = listener;
        if (this.listener != null) {
            listener.locationStatusChanged(hasLocation);
        }
    }

    public boolean getTrackLocation() {
        return trackLocation;
    }

    public void setTrackLocation(boolean trackLocation) {
        if (trackLocation == this.trackLocation) { //if trackLocation doesn't change, don't do anything
            return;
        }

        try {
            if (trackLocation) {
                this.trackLocation = true;
                manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 1, this);
            } else {
                this.trackLocation = false;
                manager.removeUpdates(this);
            }
        } catch (SecurityException se) {
            se.printStackTrace();
        }
    }

    public LocationHelper(LocationManager manager) {
        this.manager = manager;
    }

    public interface LocationHelperListener {
        void locationStatusChanged(boolean hasLocation);
    }

    /** Determines whether one Location reading is better than the current Location fix
     * @param location  The new Location that you want to evaluate
     * @param currentBestLocation  The current Location fix, to which you want to compare the new one
     */
    protected boolean isBetterLocation(Location location, Location currentBestLocation) {
        if (currentBestLocation == null) {
            // A new location is always better than no location
            return true;
        }

        // Check whether the new location fix is newer or older
        long timeDelta = location.getTime() - currentBestLocation.getTime();
        boolean isSignificantlyNewer = timeDelta > FIVE_SECONDS;
        boolean isSignificantlyOlder = timeDelta < -FIVE_SECONDS;
        boolean isNewer = timeDelta > 0;

        // If it's been more than two minutes since the current location, use the new location
        // because the user has likely moved
        if (isSignificantlyNewer) {
            return true;
            // If the new location is more than two minutes older, it must be worse
        } else if (isSignificantlyOlder) {
            return false;
        }

        // Check whether the new location fix is more or less accurate
        int accuracyDelta = (int) (location.getAccuracy() - currentBestLocation.getAccuracy());
        boolean isLessAccurate = accuracyDelta > 0;
        boolean isMoreAccurate = accuracyDelta < 0;
        boolean isSignificantlyLessAccurate = accuracyDelta > 200;

        // Check if the old and new location are from the same provider
        boolean isFromSameProvider = isSameProvider(location.getProvider(),
                currentBestLocation.getProvider());

        // Determine location quality using a combination of timeliness and accuracy
        if (isMoreAccurate) {
            return true;
        } else if (isNewer && !isLessAccurate) {
            return true;
        } else if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider) {
            return true;
        }
        return false;
    }

    /** Checks whether two providers are the same */
    private boolean isSameProvider(String provider1, String provider2) {
        if (provider1 == null) {
            return provider2 == null;
        }
        return provider1.equals(provider2);
    }
}
