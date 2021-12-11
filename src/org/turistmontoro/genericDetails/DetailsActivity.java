package org.turistmontoro.genericDetails;

import java.util.List;

import org.turistmontoro.R;
import org.turistmontoro.datos.ListaEntrada;
import org.turistmontoro.monumentos.MainActivity;
import org.turistmontoro.utils.Utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.gson.Gson;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class DetailsActivity extends SherlockActivity {

	private ListaEntrada detalle;
	Context context;
	protected Criteria criteria;
	protected LocationManager locationManager;
	TextView descripcion;
	TextView telefono;
	TextView direccion;
	TextView web;
	TextView webText;
	TextView telHint;
	ImageView foto;
	ImageView icon_web;
	TextView carta;
	ImageView imgCarta;
	String control;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_activity);
		context = getApplicationContext();

		descripcion = (TextView) findViewById(R.id.descripcion);
		telefono = (TextView) findViewById(R.id.telefono);
		direccion = (TextView) findViewById(R.id.direccion);
		web = (TextView) findViewById(R.id.web);
		webText = (TextView) findViewById(R.id.hint4);
		foto = (ImageView) findViewById(R.id.foto);
		icon_web = (ImageView) findViewById(R.id.img_web);

		// Obtenemos la info del activity anterior y la mostramos
		Gson gson = new Gson();
		Bundle bundle = getIntent().getExtras();
		String auxDetalle = bundle.getString("detalle");
		detalle = gson.fromJson(auxDetalle, ListaEntrada.class);
//		try {
//			control = bundle.getString("control");
//		} catch (Exception e) {
//
//		}

		// //Ponemos titulo al action bar y mostramos el bton home
		ActionBar ab = getSupportActionBar();
		ab.setTitle(detalle.getTitulo());
		ab.setDisplayHomeAsUpEnabled(true);
		ab.setIcon(R.drawable.ic_escudo_montoro);

		// Rellenamos la informacion
		foto.setImageResource(detalle.getIdImagen());
		descripcion.setText(detalle.getDescripcionLarga());
		descripcion.setMovementMethod(new ScrollingMovementMethod());
		telefono.setText(detalle.getTelefono());
		direccion.setText(detalle.getDireccion());
		web.setText(detalle.getWeb());

		// Admob
		AdView adView = (AdView) this.findViewById(R.id.adViewDetail);
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);

		if (detalle.getWeb() != "") {

			webText.setText("Web");
			icon_web.setImageResource(R.drawable.icon_web);

		}
		
		telefono.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DoCall();
			}
		});

		direccion.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GoAddres();
			}
		});

		web.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GoWeb();
			}
		});

	}

	public void DoCall() {

		String number = detalle.getTelefono();
		if (number != "" && number != null) {
			Intent intent = new Intent(Intent.ACTION_CALL);
			intent.setData(Uri.parse("tel:" + number));
			startActivity(intent);
		}
	}

	public void GoAddres() {

		String direccion = detalle.getDireccion();
		if (direccion != "" && direccion != null) {
			location(direccion);
		}
	}

	public void GoWeb() {

		String web = detalle.getWeb();
		if (web != "" && web != null) {
			Intent i = new Intent(Intent.ACTION_VIEW);
			i.setData(Uri.parse(web));
			startActivity(i);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		super.onOptionsItemSelected(item);

		switch (item.getItemId()) {

		case R.id.infoTurist:

			Gson gson = new Gson();
			Intent i = new Intent(this, DetailsActivity.class);
			i.putExtra("detalle",
					gson.toJson(Utils.obtenerInfoOficinaTurismo()));
			startActivity(i);
			break;

		case android.R.id.home:
			Intent intent = new Intent(this, MainActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			break;
		}
		return true;

	}

	private void location(String direccion) {
		// initialize
		context = getApplicationContext();
		locationManager = (LocationManager) getApplicationContext()
				.getSystemService(Context.LOCATION_SERVICE);
		// Coarse accuracy is specified here to get the fastest possible result.
		// The calling Activity will likely (or have already) request ongoing
		// updates using the Fine location provider.
		criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_COARSE);

		if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			createGpsDisabledAlert();
			Log.d("", "GPS desactivado");
		} else {

			locationManager = (LocationManager) context
					.getSystemService(Context.LOCATION_SERVICE);
			List<String> matchingProviders = locationManager.getAllProviders();

			for (String provider : matchingProviders) {
				locationManager.removeUpdates(singeUpdateListener);
				Location location = locationManager
						.getLastKnownLocation(provider);

				showWay(location, direccion);

				// Se le pide a cada proveedor que intente obtener la posicion
				// actual
				locationManager.requestLocationUpdates(provider, 0, 0,
						singeUpdateListener, context.getMainLooper());
			}
		}

	}

	// Alerta que avisa si el gps esta desactivado
	private void createGpsDisabledAlert() {

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Su GPS está desactivado, ¿desea activarlo?")
				.setCancelable(false)
				.setPositiveButton("Activar GPS",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								showGpsOptions();
							}
						});
		builder.setNegativeButton("Cancelar",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});

		AlertDialog alert = builder.create();
		alert.show();

	}

	// Mostrar la opciones para activar GPS
	private void showGpsOptions() {
		Intent gpsOptionsIntent = new Intent(
				android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		startActivity(gpsOptionsIntent);
	}

	protected LocationListener singeUpdateListener = new LocationListener() {
		public void onLocationChanged(Location location) {
			locationManager.removeUpdates(singeUpdateListener);
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
		}

		public void onProviderEnabled(String provider) {
		}

		public void onProviderDisabled(String provider) {
		}
	};

	// Muestra el camino de la posicion actual hasta la direccion dada.
	public void showWay(Location location, String direccion) {
		if (location != null) {

			direccion = direccion.replace(' ', '+');
			Uri uri = Uri.parse("http://maps.google.com/maps?f=d&saddr="
					+ location.getLatitude() + "," + location.getLongitude()
					+ "&daddr=" + "?q=" + direccion + "&hl=en");
			Intent it = new Intent(android.content.Intent.ACTION_VIEW, uri);
			startActivity(it);
		}

	}

}
