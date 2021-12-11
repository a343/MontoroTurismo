package org.turistmontoro.ocio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

import org.turistmontoro.R;
import org.turistmontoro.adaptadores.Lista_adaptador;
import org.turistmontoro.datos.ListaEntrada;
import org.turistmontoro.genericDetails.DetailsActivity;
import org.turistmontoro.utils.AsynCall;
import org.turistmontoro.utils.Utils;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.gson.Gson;

public class InfoPubsActivity extends SherlockFragmentActivity {

	private ListView lista;
	ProgressDialog progress;
	ArrayList<ListaEntrada> datos;
	private static final String METHOD_NAME = "getPubs";

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.fragment_list);

		// Admob
		AdView adView = (AdView) this.findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);

		Bundle bundle = getIntent().getExtras();
		String titulo = bundle.getString("titulo");
		// Ponemos titulo al action bar
		ActionBar ab = getSupportActionBar();
		ab.setTitle(titulo);
		// Icono del action bar
		ab.setIcon(R.drawable.ic_escudo_montoro);

		// Colocamos imagen de lista
		ImageView bg = (ImageView) findViewById(R.id.backgroud);
		Drawable d = getResources().getDrawable(R.drawable.logo_ocio);
		bg.setImageDrawable(d);

		datos = new ArrayList<ListaEntrada>();

		rellenarInfo(datos);
		// if (Utils.hasInternet(this)) {
		// progress = ProgressDialog.show(this, "Cargando Informacion",
		// "Cargando...", true);
		// obtenerInfo();
		//
		// } else {
		//
		// datos.addAll(MainActivity.bd.getDatos("artesania"));
		// }
		// añadirImagen();

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
				Intent i = new Intent(InfoPubsActivity.this,
						DetailsActivity.class);
				i.putExtra("detalle", gson.toJson(elegido));
				startActivity(i);
			}
		});

	}

	private void añadirImagen() {

		for (Iterator<ListaEntrada> it = datos.iterator(); it.hasNext();) {
			ListaEntrada auxiliar = (ListaEntrada) it.next();

			if (auxiliar.getTitulo().contains("Atalaya")) {

				auxiliar.setIdImagen(R.drawable.atalaya);

			} else if (auxiliar.getTitulo().contains("Moroko")) {

				auxiliar.setIdImagen(R.drawable.copas);

			} else if (auxiliar.getTitulo().contains("Bayyana")) {

				auxiliar.setIdImagen(R.drawable.copas2);

			} else if (auxiliar.getTitulo().contains("Sarao")) {

				auxiliar.setIdImagen(R.drawable.copas);

			} else if (auxiliar.getTitulo().contains("Triangulo")) {

				auxiliar.setIdImagen(R.drawable.disco);

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
				R.drawable.atalaya,
				"Cafe-Pub Atalaya",
				"Horario: 16:00 a 04:00. \n"
						+ "Situado frente a plaza de toros",
				"Todos los Sábados a partir de las 11 de la noche, Fran_Trax Dj. Sesión en directo. Desde los sonidos mas actuales al house mas selecto.",
				"", "http://goo.gl/79L9D", "Calle Cervantes 86, 14600 Montoro"));

		datos.add(new ListaEntrada(
				R.drawable.copas,
				"Pub Morocco",
				"Horario: 15:00 a 4:00.\n" + "Situado en la plaza del Charco",
				"Lugar muy concurrido, perfecto para tomar una copa,  juegos y buena música.",
				"957 16 00 24", "www.Barmoroko.com",
				"Calle Molino 7, Montoro, Córdoba"));

		datos.add(new ListaEntrada(R.drawable.copas2, "Cafe-Pub Bayyana",
				"Horario: 13:00 a 4:00.\n",
				"Local con buena musica, futbolin y dardos", "", "",
				"Avenida Andalucia 19, Montoro, Córdoba"));

		datos.add(new ListaEntrada(
				R.drawable.copas,
				"Pub Sarao",
				"Horario 21:00 a 5:00.\n",
				"Local con buena musica, futbolin, dardos y bingo para conseguir copas y botellas gratis.",
				"", "", "Calle Antonio Enrique Gómez, Montoro, Córdoba"));

		datos.add(new ListaEntrada(
				R.drawable.copas2,
				"Yepez Copas",
				"Horario 20:00 a 5:00.\n",
				"Lugar muy concurrido, perfecto para tomar una copa,  juegos y buena música.",
				"678 72 82 67", "", "Calle Molino 4, Montoro, Córdoba"));

		datos.add(new ListaEntrada(
				R.drawable.copas,
				"Cafe-Pub Joven",
				"Situado cerca del plano de la feria",
				"Lugar muy concurrido, perfecto para tomar una copa con buena música.",
				"", "", "Cervantes 19, Montoro, Córdoba"));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getSupportMenuInflater().inflate(R.menu.main, menu);
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
}
