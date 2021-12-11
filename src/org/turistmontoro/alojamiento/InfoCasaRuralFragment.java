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

public class InfoCasaRuralFragment extends SherlockFragment {

	private static final String METHOD_NAME = "getCasaRurales";
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

		// Colocamos imagen de lista
		// Colocamos imagen de lista
		ImageView bg = (ImageView) myView.findViewById(R.id.backgroud);
		Drawable d = getResources().getDrawable(R.drawable.logo_alojamiento);
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
				startActivity(i);
			}
		});

		return myView;

	}

	private void rellenarInfo(ArrayList<ListaEntrada> datos) {

		datos.add(new ListaEntrada(
				R.drawable.chiverasbajas,
				"Refugio de Chiveras Bajas",
				"Vivienda rústica, situada en la N-420 a una distancia de 8 km de Montoro.",
				"Refugio Rural de dos dormitorios, cuarto de aseo, salón con chimenea y cocina americana. Es el lugar ideal para pasar un fin de semana caminando o haciendo senderismo  por una finca privada en donde sin duda se podrán contemplar todas las especies cinegéticas de la zona ciervo, jabalí, muflón, zorro  y variedad de aves y conejos.",
				"696 495 270", "http://www.chiverasbajas.es",
				"Crta. N-420, salida km 58, Montoro, Córdoba"));
		datos.add(new ListaEntrada(
				R.drawable.cortijosanjuan,
				"Cortijo Molino San Juan",
				"Antiguo Cortijo rehabilitado del S.XIX situado en plena sierra de Montoro",
				"Se compone de 7 casas con amplio salón, cocina campera, TV, agua caliente, luz eléctrica, cocina, baño, lavadora, piscina, patios interiores, porches y terrazas, zona ajardinada. Capacidad para 6-8 personas/casa. ",
				"957 33 63 86", " http://www.cortijomolinosanjuan.com",
				"Valle del Corcomé, Montoro, Córdoba"));
		datos.add(new ListaEntrada(
				R.drawable.molinamesias,
				"Molina de Mesías",
				"Capacidad:	13 - 15 personas. Precio medio:	20 - 29 € por persona/noche ",
				"Molino: 1 Dormitorio cuadruple, 1 doble, 3 matrimonio, camas supletorias, 2 cuartos de baño, cocina campera-salón (televisión color, frigorífico, cocina vitrocerámica, arcón congelador, lavadora, etc.)."
						+ "Casas : 1 Casas con capacidad para 14 y otra casa con capacidad para  8 personas.",
				"957 160 802", "http://www.molinamesias.com",
				"Crta. Montoro-Cardeña, km. 9, Montoro, Córdoba"));

		datos.add(new ListaEntrada(
				R.drawable.casa_maika,
				"Casa Maika. Situada en pleno centro.",
				"Precio medio: 50€ ",
				"Casa Maika ofrece 8 habitaciones dobles con baño, aire acondicionado, calefacción, TV Posee además 2 apartamentos completamente equipados con habitación doble, cuarto de baño completo, salón-cocina, aire acondicionado, calefacción y tv.",
				"957 160 273", "http://www.casamaika.com",
				"Calle Salazar, 21 Montoro, Córdoba"));

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

			if (auxiliar.getTitulo().contains("Chiveras Bajas")) {

				auxiliar.setIdImagen(R.drawable.chiverasbajas);

			} else if (auxiliar.getTitulo().contains("Molino San Juan")) {

				auxiliar.setIdImagen(R.drawable.cortijosanjuan);

			} else if (auxiliar.getTitulo().contains("Molina de Mesías")) {

				auxiliar.setIdImagen(R.drawable.molinamesias);

			} else if (auxiliar.getTitulo().contains("Casa Maika")) {

				auxiliar.setIdImagen(R.drawable.casa_maika);

			}

		}
	}

}
