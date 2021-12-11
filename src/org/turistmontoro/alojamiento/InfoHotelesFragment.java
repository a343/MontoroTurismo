package org.turistmontoro.alojamiento;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

import org.turistmontoro.R;
import org.turistmontoro.adaptadores.Lista_adaptador;
import org.turistmontoro.datos.ListaEntrada;
import org.turistmontoro.genericDetails.DetailsActivity;
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

public class InfoHotelesFragment extends SherlockFragment {

	private static final String METHOD_NAME = "getHoteles";
	private ListView lista;
	ArrayList<ListaEntrada> datos;
	private ProgressDialog progress;

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
		//
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

		// Admob
		AdView adView = (AdView) myView.findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);

		// Colocamos imagen de lista
		ImageView bg = (ImageView) myView.findViewById(R.id.backgroud);
		Drawable d = getResources().getDrawable(R.drawable.logo_alojamiento);
		bg.setImageDrawable(d);

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
				startActivity(i);
			}
		});

		return myView;

	}

	private void rellenarInfo(ArrayList<ListaEntrada> datos) {

		datos.add(new ListaEntrada(
				R.drawable.hotelmirador,
				"Hotel Mirador",
				"Hotel 3 estrellas. Precio medio: 30 - 39 € por persona/noche",
				"Este hotel cuenta con cafetería, Restaurante, Aire Climatizado, Piscina con amplios jardines y Aparcamiento. El hotel obsequia a sus huespedes con una impresionante imagen panoramica de la monumental e historica ciudad de Montoro y del rio Guadalquir ",
				"957 162 803", "http://www.hotelmiradordemontoro.es",
				"Cerro La Muela, Montoro, Córdoba"));
		datos.add(new ListaEntrada(
				R.drawable.molinolanava,
				"Molino La Nava",
				"Precio: 80-90€ (Desayuno incluido). Antiguo molino aceitero rehabilitado como hotel rural con encanto.",
				"En la primera planta hallarás ocho habitaciones, cada una diferente y decorada individualmente en un estilo clásico y elegante.Todas las habitaciones cuentan con calefacción, aire acondicionado, baño completo con ducha, televisión, teléfono, WIFI, caja fuerte, albornoz y amenities. Disponemos de una habitación habilitada para personas con movilidad reducida. Contamos con servicio de lavandería, cuna gratuita disponible y documentación sobre la zona.",
				"957 33 64 22", "http://www.molinonava.com",
				"Camino la Nava 6, Montoro, Córdoba"));

		datos.add(new ListaEntrada(
				R.drawable.hostal,
				"Hostal Montoro",
				"Hostal 2 Estrellas. 35 habitaciones climatizadas.",
				"Cafetería, Restaurante con capacidad para 300 comensales, Aparcamiento, Garaje, Calefacción Central y Aire Acondicionado",
				"957 160 792", "http://hostalmontoro.com",
				"Ctra. Madrid-Cádiz, Salida Km. 359, Montoro, Córdoba"));

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

	private void añadirImagen() {

		for (Iterator<ListaEntrada> it = datos.iterator(); it.hasNext();) {
			ListaEntrada auxiliar = (ListaEntrada) it.next();

			if (auxiliar.getTitulo().contains("Mirador")) {

				auxiliar.setIdImagen(R.drawable.hotelmirador);

			} else if (auxiliar.getTitulo().contains("Molino La Nava")) {
				auxiliar.setIdImagen(R.drawable.molinolanava);

			} else if (auxiliar.getTitulo().contains("Hostal Montoro")) {
				auxiliar.setIdImagen(R.drawable.hostal);

			}
		}
	}

}
