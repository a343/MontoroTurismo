package org.turistmontoro.ocio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

import org.turistmontoro.R;
import org.turistmontoro.adaptadores.Lista_adaptador;
import org.turistmontoro.datos.ListaEntrada;
import org.turistmontoro.genericDetails.DetailsActivity;
import org.turistmontoro.sqlLite.SQLite;
import org.turistmontoro.utils.AsynCall;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.gson.Gson;

public class InfoRestaurantFragment extends SherlockFragment {

	private ListView lista;
	ProgressDialog progress;
	ArrayList<ListaEntrada> datos;
	private static final String METHOD_NAME = "getRestaurantes";
	public static SQLite baseDatos;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		datos = new ArrayList<ListaEntrada>();

		rellenarInfo(datos);
		// if (Utils.hasInternet(getActivity())) {
		// progress = ProgressDialog.show(this.getActivity(),
		// "Cargando Informacion", "Cargando...", true);
		// obtenerInfo();
		//
		// } else {
		//
		// datos.addAll(MainActivity.bd.getDatos("artesania"));
		// }
		// if (datos.isEmpty()) {
		// Toast.makeText(getSherlockActivity(),
		// "Ha fallado la carga de datos", 20).show();
		// } else {
		// añadirImagen();
		// }
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,

	Bundle savedInstanceState) {

		View myView = inflater.inflate(R.layout.fragment_list, null);
		// Colocamos imagen de lista
		ImageView bg = (ImageView) myView.findViewById(R.id.backgroud);
		Drawable d = getResources().getDrawable(R.drawable.logo_bares);
		bg.setImageDrawable(d);

		// Admob
		AdView adView = (AdView) myView.findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);

		lista = (ListView) myView.findViewById(R.id.ListView_listado_detail);

		lista.setAdapter(new Lista_adaptador(this.getActivity(),
				R.layout.list_item, datos) {
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
				Intent i = new Intent(getActivity(), DetailsActivity.class);
				i.putExtra("detalle", gson.toJson(elegido));
//				i.putExtra("control", "1");
				startActivity(i);
			}
		});

		return myView;

	}

	private void añadirImagen() {

		for (Iterator<ListaEntrada> it = datos.iterator(); it.hasNext();) {
			ListaEntrada auxiliar = (ListaEntrada) it.next();

			if (auxiliar.getTitulo().contains("La Molina Plaza")) {

				auxiliar.setIdImagen(R.drawable.molinaplaza);

			} else if (auxiliar.getTitulo().contains("Jardinito")) {

				auxiliar.setIdImagen(R.drawable.jardinito2);

			} else if (auxiliar.getTitulo().contains("La Entrada")) {

				auxiliar.setIdImagen(R.drawable.laentrada);

			} else if (auxiliar.getTitulo().contains("Los Monteros")) {

				auxiliar.setIdImagen(R.drawable.losmonteros);

			} else if (auxiliar.getTitulo().contains("La Primera")) {

				auxiliar.setIdImagen(R.drawable.laprimera);

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
				R.drawable.molinaplaza,
				"Restaurante La Molina Plaza",
				"Precio medio: 20.00€. Menú del día: 9,50€. Tenedores: 1.",
				"Este restaurante se encuentra a unos 10 kms de Montoro dirección Cardeña, en las puertas del Parque Natural de Cardeña-Montoro."
						+ "Amplia variedad de platos procedentes de la montería. Gran carta de ibéricos. Especialidades: Carne de monte y comida casera. El Hotel Restaurante La Molina Plaza es un antiguo molino de aceite del 1855, restaurado en el 2000, ubicado en los alrededores del parque natural de sierra de Cardeña-Montoro.",
				"957 336 090", "",
				"Crta. N-420, salida km 58, Montoro, Córdoba"));
		datos.add(new ListaEntrada(
				R.drawable.jardinito2,
				"Restaurante Jardinito II",
				"Precio medio: 18.00 €. Menú del día: 8,50€. Tenedores: 1.",
				"Especialidades: Carnes a la brasa, gambas al ajillo, flamenquín casero, salmorejo, comida casera."
						+ "Este restaurante se encuentra junto a la Autovía de Andalucía, cuenta con amplios aparcamientos y buena calidad-precio.",
				"957 160 048", "http://www.restaurantejardinito2.com",
				"Calle el Carpio 19, Montoro, Córdoba"));
		datos.add(new ListaEntrada(
				R.drawable.laentrada,
				"Restaurante La Entrada",
				"Precio medio: 7.00 €. Tenedores: 1, Especialidades: Comida Casera.",
				"Ofrece una amplia carta de platos típicos. Este establecimiento cuenta con grandes salones para celebraciones. Cuenta con zona de esparcimiento.",
				"957 160 335", "",
				"Plaza Indrustrial Camino de Morente 3, Montoro, Córdoba"));
		datos.add(new ListaEntrada(
				R.drawable.losmonteros,
				"Restaurante Los Monteros",
				"Tenedores: 1, Especialidades: Productos ibéricos, carnes a la brasa, carne de monte, chuletones.",
				"Ofrece servicio de alojamiento. Se encuentra a unos 5 kms del Pantano de Martín Gonzalo y  muy cerca del Parque Natural de Cardeña y Montoro.",
				"957 162 256", "", "Crta. N-420, Km. 8, Montoro, Córdoba"));
		datos.add(new ListaEntrada(
				R.drawable.laprimera,
				"Restaurante La Primera",
				"Precio medio: 12.00 €. Menú del día: 7,00. Tenedores: 1. "
						+ "Especialidades: flamenquines caseros, rabo de toro, salmorejo.",
				"Este establecimiento ofrece una amplia variedad de comida típica montoreña y salones donde poder disfrutar de celebraciones de todo tipo, asi como un pub ubicado junto al restaurante. ",
				"957 16 02 23", "",
				"Avda. Doctor Fleming, s/n, Montoro, Córdoba"));

	}

}
