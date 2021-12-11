package org.turistmontoro.excursiones;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

import org.turistmontoro.R;
import org.turistmontoro.adaptadores.Lista_adaptador;
import org.turistmontoro.datos.ListaEntrada;
import org.turistmontoro.genericDetails.DetailsActivity;
import org.turistmontoro.monumentos.MainActivity;
import org.turistmontoro.utils.AsynCall;
import org.turistmontoro.utils.Utils;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.gson.Gson;

public class Excursiones extends SherlockActivity {

	private ListView lista;
	ProgressDialog progress;
	ArrayList<ListaEntrada> datos;
	private static final String METHOD_NAME = "getExcursiones";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_list);

		// Colocamos imagen de lista
		ImageView bg = (ImageView) findViewById(R.id.backgroud);
		Drawable d = getResources().getDrawable(R.drawable.logo_rutas);
		bg.setImageDrawable(d);

		// Comprobamos si esta instalado google play services
		checkGooglePlayServicesAvailability();

		Bundle bundle = getIntent().getExtras();
		String titulo = bundle.getString("titulo");
		// Ponemos titulo al action bar
		ActionBar ab = getSupportActionBar();
		ab.setTitle(titulo);
		ab.setIcon(R.drawable.ic_escudo_montoro);
		datos = new ArrayList<ListaEntrada>();

		rellenarInfo(datos);
//		if (Utils.hasInternet(this)) {
//			progress = ProgressDialog.show(this, "Cargando Informacion",
//					"Cargando...", true);
//			obtenerInfo();
//
//		} else {
//
//			datos.addAll(MainActivity.bd.getDatos("artesania"));
//		}
//		añadirImagen();
		lista = (ListView) findViewById(R.id.ListView_listado_detail);

		lista.setAdapter(new Lista_adaptador(this, R.layout.list_item, datos) {
			@Override
			public void onEntrada(Object entrada, View view) {
				if (entrada != null) {
					TextView texto_superior_entrada = (TextView) view
							.findViewById(R.id.textView_superior);
					if (texto_superior_entrada != null)
						texto_superior_entrada.setText(((ListaEntrada) entrada)
								.getTitulo());

					TextView texto_inferior_entrada = (TextView) view
							.findViewById(R.id.textView_inferior);
					texto_inferior_entrada.setGravity(Gravity.CENTER);
					if (texto_inferior_entrada != null)
						texto_inferior_entrada.setText(((ListaEntrada) entrada)
								.getDescripcion());

					ImageView imagen_entrada = (ImageView) view
							.findViewById(R.id.imageView_imagen);
					if (imagen_entrada != null)
						imagen_entrada
								.setImageResource(((ListaEntrada) entrada)
										.getIdImagen());
				}
			}

		});

		lista.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> pariente, View view,
					int posicion, long id) {
				ListaEntrada elegido = (ListaEntrada) pariente
						.getItemAtPosition(posicion);

				Gson gson = new Gson();
				Intent i = new Intent(Excursiones.this,
						DetailExcursionesActivity.class);
				i.putExtra("DetalleRutas", gson.toJson(elegido));
				startActivity(i);
			}
		});
	}

	private void añadirImagen() {

		for (Iterator<ListaEntrada> it = datos.iterator(); it.hasNext();) {
			ListaEntrada auxiliar = (ListaEntrada) it.next();

			if (auxiliar.getTitulo().contains("RUTA 1")) {

				auxiliar.setIdImagen(R.drawable.ruta1);

			} else if (auxiliar.getTitulo().contains("RUTA 2")) {

				auxiliar.setIdImagen(R.drawable.ruta2);

			} else if (auxiliar.getTitulo().contains("RUTA 3")) {

				auxiliar.setIdImagen(R.drawable.ruta3);

			} else if (auxiliar.getTitulo().contains("RUTA 4")) {

				auxiliar.setIdImagen(R.drawable.ruta4);

			} else if (auxiliar.getTitulo().contains("RUTA 5")) {

				auxiliar.setIdImagen(R.drawable.ruta5);
			}

		}
	}

//	private void obtenerInfo() {
//
//		AsynCall task = new AsynCall(progress, METHOD_NAME);
//		// Call execute
//		try {
//			datos = task.execute().get();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	private void rellenarInfo(ArrayList<ListaEntrada> datos) {
		datos.add(new ListaEntrada(
				1,
				R.drawable.ruta1,
				"RUTA 1: MONTORO EN ÉPOCA ROMANA",
				"Longitud de la ruta: 3,5 kms. \n" + "Duracion aprox: 1h 15min",
				"", "", "http://goo.gl/z4vyY", "", ""));
		datos.add(new ListaEntrada(2, R.drawable.ruta2,
				"RUTA 2.- MONTORO EN LA EDAD MEDIA",
				"Longitud de la Ruta: 2,77 kms. \n" + "Duracion aprox: 1h", "",
				"", "http://goo.gl/zGR5U", "", ""));
		datos.add(new ListaEntrada(3, R.drawable.ruta3,
				"RUTA 3.- MONTORO EN LOS SIGLOS XVI-XVIII",
				"Longitud de la Ruta: 2,57 km. \n" + "Duracion aprox: 1h", "",
				"", "http://goo.gl/4xO61", "", ""));
		datos.add(new ListaEntrada(4, R.drawable.ruta4,
				"RUTA 4.- HERÁLDICA DE MONTORO",
				"Longitud de la Ruta: 3,22 km. \n"
						+ "Duracion aprox: 1h 30 min", "", "",
				"http://goo.gl/zGR5U", "", ""));
		datos.add(new ListaEntrada(5, R.drawable.ruta5,
				"RUTA 5.- MONTORO EN LOS SIGLOS XIX Y XX",
				"Longitud de la Ruta: 1,86 kms. \n" + "Duracion aprox: 45min",
				"", "", "http://goo.gl/wZ1Mi", "", ""));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.main, menu);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		Gson gson = new Gson();
		Intent i = new Intent(this, DetailsActivity.class);
		i.putExtra("detalle", gson.toJson(Utils.obtenerInfoOficinaTurismo()));
		startActivity(i);

		return true;
	}

	public void checkGooglePlayServicesAvailability() {
		int statusCode = GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(this);
		if (statusCode != ConnectionResult.SUCCESS) {
			GooglePlayServicesUtil.getErrorDialog(statusCode, this, 0).show();
		}
	}
}
