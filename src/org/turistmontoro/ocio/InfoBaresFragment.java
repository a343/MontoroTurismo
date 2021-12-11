package org.turistmontoro.ocio;

import java.util.ArrayList;

import org.turistmontoro.R;
import org.turistmontoro.adaptadores.Lista_adaptador;
import org.turistmontoro.datos.ListaEntrada;
import org.turistmontoro.genericDetails.DetailsActivity;
import org.turistmontoro.utils.ManejadorBares;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
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

public class InfoBaresFragment extends SherlockFragment {

	private ListView lista;
	ProgressDialog progress;
	ArrayList<ListaEntrada> datos;
	private static final String METHOD_NAME = "getBares";

	@Override
	public void onCreate(Bundle savedInstanceState) {

		datos = new ArrayList<ListaEntrada>();

		rellenarInfo(datos);
		// Obtenemos la informaicon a través del WS

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
				Log.i("Bares", "seteando informacions en la lista....");
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
				Intent i = new Intent(getActivity(), ManejadorBares.class);
				i.putExtra("detalle", gson.toJson(elegido));
				startActivity(i);
			}
		});

		return myView;

	}

	// private void añadirImagen() {
	//
	// for (Iterator<ListaEntrada> it = datos.iterator(); it.hasNext();) {
	// ListaEntrada auxiliar = (ListaEntrada) it.next();
	// if (auxiliar.getTitulo().contains("Gordo")) {
	//
	// auxiliar.setIdImagen(R.drawable.elgordo);
	//
	// } else if (auxiliar.getTitulo().contains("John´s Corner")) {
	//
	// auxiliar.setIdImagen(R.drawable.jhon_corners);
	//
	// } else if (auxiliar.getTitulo().contains("Municipal")) {
	//
	// auxiliar.setIdImagen(R.drawable.piscina);
	//
	// } else if (auxiliar.getTitulo().contains("El Coto")) {
	//
	// auxiliar.setIdImagen(R.drawable.coto);
	//
	// } else if (auxiliar.getTitulo().contains("Cafeteria Vértice")) {
	//
	// auxiliar.setIdImagen(R.drawable.vertice);
	//
	// } else if (auxiliar.getTitulo().contains("Bar Yepez")) {
	//
	// auxiliar.setIdImagen(R.drawable.yepez);
	// }
	//
	// }
	// }
	//
	// private void obtenerInfo() {
	//
	// AsynCall task = new AsynCall(progress, METHOD_NAME);
	// // Call execute
	// try {
	// datos = task.execute().get();
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (ExecutionException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	private void rellenarInfo(ArrayList<ListaEntrada> datos) {

		datos.add(new ListaEntrada(
				R.drawable.jhon_corners,
				"Cerveceria John´s Corner",
				"Tapas con la bebida. \n" + "Buena relacion calidad/precio",
				"Especialidad en pizzas, bocatas, tablas y comida mejicana. Comida a domicilio. Carta con más de 250 tapas diferentes",
				"957 162 220", "", "Calle Virgen de Gracia 1, Montoro, Córdoba"));
		datos.add(new ListaEntrada(
				R.drawable.elgordo,
				"Bar El Gordo",
				"Tapas con la bebida \n" + "Buena relacion calidad/precio",
				"Servicio de cafetería, bar y restauración. Cuenta con una zona exterior donde instala veladores al aire libre.",
				"957 161 264", "", "Blas Infante 1, Montoro, Córdoba"));

		datos.add(new ListaEntrada(
				R.drawable.piscina,
				"Bar Piscina Municipal",
				"Comida para llevar, pollos al horno, paellas por encargo y bocadillos variados. Amplia variedad de tapas y raciones",
				"Establecimiento habilitado para todo tipo de celebraciones, con veladores cubiertos y salón climatizado.",
				"957 162 222", "", "Virgen de Gracia, Montoro, Córdoba"));

		datos.add(new ListaEntrada(
				R.drawable.coto,
				"Bar El Coto",
				"Servicio de cafetería y bar durante todo el año.",
				"En primavera y verano, ofrece servicio de comida en los veladores instalados en el exterior del establecimiento.",
				"957 160 951", "", "Plaza de España 6, Montoro, Córdoba"));
		datos.add(new ListaEntrada(
				R.drawable.vertice,
				"Bar Cafeteria Vértice",
				"Servicio de cafetería, bar y restauración.",
				"Ofrece a su clientela una amplia variedad de platos y diferentes menus. Tiene salon comedor. Precio medio: 18.00 €. Menú del día: 7,50 €",
				"957 160 676", "", "Calle Cervantes, 15, Montoro, Córdoba"));
		datos.add(new ListaEntrada(
				R.drawable.yepez,
				"Bar Yepez",
				"Su especialidad en tapas, raciones y comida casera.",
				"Es uno de los puntos de encuentro más concurridos del centro de la ciudad.",
				"957 160 123", "", "Plaza del Charco, 4, Montoro, Córdoba"));

		datos.add(new ListaEntrada(
				R.drawable.tabernacasajose_opt,
				"Casa José",
				"Servicio de cafetería y bar. Tapas con tu consumicion.",
				"Una taberna restaurante en el centro de Montoro, con una terraza en la plaza donde tomar unas tapas en compañía y con el encanto propio del pueblo y un balconcito donde tapear, cenar o tomar una copa con vistas al meandro del río Guadalquivir y donde descubrirás el embrujo y la belleza de Montoro.",
				"", "", "Plaza España, Montoro, Córdoba"));

		datos.add(new ListaEntrada(
				R.drawable.hanoy_opt,
				"Bar Hanoy",
				"Servicio de cafetería y bar con una gran terraza.",
				"Este establecimiento ofrece servicio de cafetería, bar y  restaurante. Cuenta con una terraza exterior donde se instalan veladores.",
				"957 160 272", "http://goo.gl/g7s6wN",
				"Plaza España 24, Montoro, Córdoba"));

		datos.add(new ListaEntrada(
				R.drawable.imperio_opt,
				"Bar Nuevo Imperio",
				"Servicio de cafetería y bar. Tapas con tu consumicion",
				"Este establecimiento ofrece servicio de cafetería, bar y  restaurante.",
				"", "", "Calle Corredera 33, Montoro, Córdoba"));

		datos.add(new ListaEntrada(
				R.drawable.chispazo,
				"El chispazo",
				"Bar de tapas, restaurante, cervecería, cocktelería.",
				"Horarios: De Lunes a Domingo de 08:00 a 00:00 horas. \n El Restaurante El Chispazo, ubicado en Montoro, ofrece una cocina de tradición con pinceladas innovadoras donde el rabo de toro y los productos frescos son los protagonistas. Precio menú diario: Menos de 15 €. Precio medio carta: 15-25 €. Precio menú degustación: 26-35 €. Especialidad/Recomendación: Rabo de toro tradicional.",
				"957 96 31 43", "http://goo.gl/tPzoEF",
				"Plaza del charco 20, Montoro, Córdoba"));

		datos.add(new ListaEntrada(
				R.drawable.donde_siempre_opt,
				"Bar Donde Siempre",
				"Servicio de cafetería, bar y restaurante.",
				"Este establecimiento ofrece servicio de cafetería, bar y  restaurante. ",
				"957 16 25 74", "http://goo.gl/vEs1lv",
				"Calle Cervantes, Montoro, Córdoba"));

	}
}
