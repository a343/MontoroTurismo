package org.turistmontoro.monumentos;

import java.util.ArrayList;
import java.util.TimerTask;

import org.turistmontoro.R;
import org.turistmontoro.alojamiento.InfoAlojamientoActivity;
import org.turistmontoro.datos.ListaEntrada;
import org.turistmontoro.excursiones.Excursiones;
import org.turistmontoro.genericDetails.DetailsActivity;
import org.turistmontoro.ocio.OcioActivity;
import org.turistmontoro.ocio.InfoPubsActivity;
import org.turistmontoro.sqlLite.SQLite;
import org.turistmontoro.utils.AsynCall;
import org.turistmontoro.utils.Utils;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.google.android.gms.ads.*;
import com.google.gson.Gson;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends SherlockActivity {

	private static final String METHOD_NAME = "getTodos";
	private static final String MY_AD_UNIT_ID = "ca-app-pub-4583169902024944/3859746913";
	private ProgressDialog progress;
	private ArrayList<ListaEntrada> datos;
	public static SQLite bd;
	// private static String PREFERENCIAS = "firsTime";
	// private ScheduledExecutorService scheduleTaskExecutor;
	Context context;
	private AdView adView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// boolean firstime = true;
		// Date d = Calendar.getInstance().getTime();
		// d.setMinutes(d.getMinutes() + 2);
		// context = getBaseContext();
		//

		setContentView(R.layout.activity_main);
		// Ponemos titulo al action bar y mostramos el bton home
		ActionBar ab = getSupportActionBar();
		ab.setIcon(R.drawable.ic_escudo_montoro);
		// SharedPreferences preferencias = this.getSharedPreferences(
		// PREFERENCIAS, Context.MODE_PRIVATE);

		// preferencias.getBoolean("firstime", firstime);
		// bd = new SQLite(this);
		//
		// // Actualizar SQLite
		// MyTimerTask myTask = new MyTimerTask();
		// scheduleTaskExecutor = Executors.newScheduledThreadPool(1);
		//
		// // Timer myTimer = new Timer();
		//
		// // Es la primera vez que se entra a la app
		// if (firstime == true) {
		//
		// Log.i("", "primera vez");
		// preferencias.edit().putBoolean("firstime", false);
		//
		// // myTimer.scheduleAtFixedRate(myTask, 2000, 30*24*60*60*1000);
		// // myTimer.scheduleAtFixedRate(myTask, 2 * 1000, 1 * 60 * 1000);
		//
		// scheduleTaskExecutor.scheduleAtFixedRate(myTask, 1, 5,
		// TimeUnit.MINUTES);
		//
		// }

		// Admob
		// Create the adView.
		adView = new AdView(this);
		adView.setAdUnitId(MY_AD_UNIT_ID);
		adView.setAdSize(AdSize.BANNER);

		// Lookup your LinearLayout assuming it's been given
		// the attribute android:id="@+id/mainLayout".
		LinearLayout layout = (LinearLayout) findViewById(R.id.admobBanner);

		// Add the adView to it.
		layout.addView(adView);

		// Initiate a generic request.
		AdRequest adRequest = new AdRequest.Builder().build();

		// Load the adView with the ad request.
		adView.loadAd(adRequest);

	}

	@Override
	public void onDestroy() {
		adView.destroy();
		super.onDestroy();
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

		Gson gson = new Gson();
		Intent i = new Intent(this, DetailsActivity.class);
		i.putExtra("detalle", gson.toJson(Utils.obtenerInfoOficinaTurismo()));
		startActivity(i);
		return true;

	}

//	private void obtenerInfo() throws Exception {
//
//		AsynCall task = new AsynCall(progress, METHOD_NAME);
//		// Call execute
//		try {
//			datos = task.execute().get();
//
//			if (datos == null) {
//				throw new Exception();
//			}
//		} catch (Exception e) {
//			throw new Exception();
//
//		}
//
//	}

	// Museos, Monumentos, Artesania
	public void GoToInfoTurist(View view) {

		Intent i = new Intent(this, InfoTuristActivity.class);
		i.putExtra("titulo", "¿Qué visitar?");
		startActivity(i);

	}

	// Bares, Restaurantes
	public void GoToInfoBares(View view) {

		Intent i = new Intent(this, OcioActivity.class);
		i.putExtra("titulo", "Comer/Tapear");
		startActivity(i);
		Log.i("MainActivity", "pulsado iniciar nueva activity");

	}

	// Pubs, disco
	public void GoToInfoPubs(View view) {

		Intent i = new Intent(this, InfoPubsActivity.class);
		i.putExtra("titulo", "Tomar una copa");
		startActivity(i);

	}

	// Alojamiento
	public void GoToInfoAlojamiento(View view) {

		Intent i = new Intent(this, InfoAlojamientoActivity.class);
		i.putExtra("titulo", "Alojamiento");
		startActivity(i);

	}

	// Rutas
	public void GoToExcursions(View view) {

		Intent i = new Intent(this, Excursiones.class);
		i.putExtra("titulo", "Excursiones");
		startActivity(i);

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

//	class MyTimerTask extends TimerTask {
//
//		@Override
//		public void run() {
//
//			// TODO Auto-generated method stub
//			try {
//				obtenerInfo();
//				for (ListaEntrada d : datos) {
//					bd.insertarBD(d);
//				}
//
//			} catch (Exception e) {
//				// AlertDialog.Builder dialogo1 = new AlertDialog.Builder(
//				// MainActivity.this.getApplicationContext());
//				// dialogo1.setTitle("Importante");
//				// dialogo1.setMessage("Losiento, Algo ha salido mal...");
//				// dialogo1.show();
//
//				Toast.makeText(context, "Losiento, Algo ha salido mal...",
//						Toast.LENGTH_LONG).show();
//
//			}
//
//		}

//	}

}
