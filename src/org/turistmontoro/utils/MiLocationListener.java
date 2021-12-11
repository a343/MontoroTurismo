package org.turistmontoro.utils;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

public class MiLocationListener implements LocationListener {
	private Marker actual;
	private GoogleMap mapa;

	public void onLocationChanged(Location location) {
		// aca vamos a realizar una accion al tomar un nuevo valor
		float lat = (float) (location.getLatitude());
		float lng = (float) (location.getLongitude());
		LatLng latLng = new LatLng(lat, lng);
//		mapa = DetailExcurisones.mapa;

		Log.d("Localizacion", "Valor: " + location.toString() + " latitud: "
				+ lat + " logitud: " + lng + "Valor al mapa" + latLng);

		CameraPosition cameraPosition = new CameraPosition.Builder()
				.target(latLng) // seteamos el centro del mapa en la posicion
								// actual
				.zoom(17) // configuramos el zoom
				.build(); // Una vez seteado los parametros, construimos el
							// objetos


	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

	public void onProviderDisabled(String provider) {
		// vamos a intentar cambiar con otros proveedores
		Log.d("Localizacion", "Esta desabilitado el proveedor");
	}

	public void onProviderEnabled(String s) {
		// vamos a intentar cambiar con otros proveedores
		Log.d("Localizacion", "Esta habilitado el proveedor");
	}

	/**
	 * esta criteria va a tener menos precision, consumir menos energia, y un
	 * menor costo
	 */
	public static Criteria crearCriteria() {

		Criteria c = new Criteria();
		c.setAccuracy(Criteria.ACCURACY_FINE);
		c.setAltitudeRequired(false);
		c.setBearingRequired(false);
		c.setSpeedRequired(false);
		c.setCostAllowed(true);
		c.setPowerRequirement(Criteria.POWER_HIGH);
		return c;
	}

	public Marker getActual() {
		return actual;
	}

	public void setActual(Marker actual) {
		this.actual = actual;
	}

	public GoogleMap getMapa() {
		return mapa;
	}

	public void setMapa(GoogleMap mapa) {
		this.mapa = mapa;
	}
}
