package org.turistmontoro.monumentos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

import org.turistmontoro.R;
import org.turistmontoro.adaptadores.Lista_adaptador;
import org.turistmontoro.datos.ListaEntrada;
import org.turistmontoro.genericDetails.DetailsActivity;
import org.turistmontoro.utils.AsynCall;
import org.turistmontoro.utils.Utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.gson.Gson;

public class InfoArtesaniaFragment extends SherlockFragment {

	private ListView lista;
	ProgressDialog progress;
	ArrayList<ListaEntrada> datos;
	private static final String METHOD_NAME = "getArtesania";

	public static InfoArtesaniaFragment newInstance(String content) {

		InfoArtesaniaFragment fragment = new InfoArtesaniaFragment();

		return fragment;

	}

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
		// }else{
		// a�adirImagen();
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

	private void a�adirImagen() {

		for (Iterator<ListaEntrada> it = datos.iterator(); it.hasNext();) {
			ListaEntrada auxiliar = (ListaEntrada) it.next();

			if (auxiliar.getTitulo().contains("Mazapanes")) {

				auxiliar.setIdImagen(R.drawable.logo_mazapanes);

			} else if (auxiliar.getTitulo().contains("Ap�cola")) {

				auxiliar.setIdImagen(R.drawable.miel);

			} else if (auxiliar.getTitulo().contains("Aceite")) {

				auxiliar.setIdImagen(R.drawable.aceite);

			} else if (auxiliar.getTitulo().contains("Cerrajer�a")) {

				auxiliar.setIdImagen(R.drawable.forjaluisramos);

			} else if (auxiliar.getTitulo().contains("cuero")) {

				auxiliar.setIdImagen(R.drawable.cuero_mohedo);

			} else if (auxiliar.getTitulo().contains("Esparto")) {

				auxiliar.setIdImagen(R.drawable.cestitaguita0a);
			} else if (auxiliar.getTitulo().contains("Talleres")) {

				auxiliar.setIdImagen(R.drawable.torrebelen);
			}

		}
	}

//	private void obtenerInfo() {
//
//		AsynCall task = new AsynCall(progress, METHOD_NAME);
//		// Call execute
//		try {
//			datos = task.execute().get();
//
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
				R.drawable.logo_mazapanes,
				"Mazapanes La Logro�esa",
				"El Mazap�n de Montoro lanzado a la mayor parte del territorio nacional",
				"Tradici�n, artesan�a, saber hacer y calidad son algunas de las caracter�sticas que han convertido a La Logro�esa en uno de los referentes del mercado espa�ol en la fabricaci�n del mazap�n",
				"957 47 59 36", "http://torregonza.com",
				"Calle Realejo 7, Montoro, C�rdoba"));
		datos.add(new ListaEntrada(
				R.drawable.miel,
				"Ap�cola de Montoro",
				"Amplia gama de mieles monoflorales y t�picas de la sierra del Parque Natural de Carde�a-Montoro.",
				"Empresa dedicada a la obtenci�n, envasado y fabricaci�n de miel de excepcional calidad de nuestra Sierra.Ha obtenido premios a la calidad y a la presentaci�n artesanal en la Expomiel de C�rdoba y en el 2006 le han otorgado el Premio Lince Ib�rico de la Junta de Andaluc�a.",
				"957 161 463", "http://www.comercianda.es/apicolademontoro",
				"Calle Doctor Fleming, 17, 14600, Montoro"));
		datos.add(new ListaEntrada(
				R.drawable.aceite,
				"Aceite de Oliva Virgen de Montoro",
				"Aceite con denominacion de origen Montoro-Adamuz",
				"Los Aceites de Oliva V�rgenes Extra de la DOP Montoro-Adamuz son una Fuente Natural de Salud, gracias a su alto contenido en polifenoles, antioxidantes naturales, muy beneficiosos para la salud. Nuestro aceite de oliva virgen se obtiene de las variedades de aceituna que existen en nuestras comarcas: Picual, Nevadillo Negro, Lech�n de Sevilla, Picudo y Carrasque�o de la Sierra.",
				"957 113 404", "http://montoro-adamuz.com", ""));
		datos.add(new ListaEntrada(
				R.drawable.forjaluisramos,
				"Cerrajer�a art�stica Ramos",
				"Artesan�a de la Forja",
				"Esta empresa se ha especializado en barbacoas, ofreciendo tambi�n una gran variedad de mobiliario y elementos de decoraci�n.",
				"957160263", "", "Redonda, S/N , Montoro, C�rdoba"));
		datos.add(new ListaEntrada(
				R.drawable.cuero_mohedo,
				"Artesan�a del cuero Hnos. Mohedo.",
				"Desde 1850 funciona esta peque�a industria artesanal.",
				"El material utilizado es la piel de vacuno y los objetos obtenidos a partir de ella son m�ltiples : botas, zapatos, cinturones, sillas, bolsos, zurrones, chalecos, zahones, fundas de pu�ales y de rifles, entre otros. La venta se realiza a particulares de toda Espa�a que acuden a Montoro. Esta empresa ha participado en numerosas ferias y exposiciones de toda la geograf�a espa�ola.",
				"", "http://calzadosmohedo.blogspot.com.es",
				"Calle Corredera, 14600, Montoro"));
		datos.add(new ListaEntrada(
				R.drawable.cestitaguita0a,
				"Artesan�a del Esparto Hidalgo",
				"Desde 1940 la familia Hidalgo elabora objetos de esparto artesanalmente.",
				"Artesanos con mas de 50 a�os de experiencia en la elaboraci�n de trabajos artesanales utilizando materias vegetales como es el esparto. Retomando los conocimientos de anta�o , esta familia montore�a continua con la tradici�n perpetuando este arte y embelleciendo multitud de rincones con sus obras.",
				"957 161 691", "", "Calle Corredera 73, 14600, Montoro"));
		datos.add(new ListaEntrada(
				R.drawable.torrebelen,
				"Talleres Jos� Madue�o Ruiz SL",
				"Reconstrucci�n de edificios realizados en piedra.",
				"Tiene sus or�genes como empresa en el a�o 1973 y fundada por Jos� Madue�o Ruiz. Nos dedicamos a extraer nuestras piedras de Molinaza y a la comercializaci�n de  las mismas y as� como aserramos y elaboramos todo tipo de m�rmoles y granitos en nuestras modernas instalaciones",
				"957 16 07 37", "http://areniscamolinaza.com/",
				"Calle de Cervantes, 59, Montoro,Cordoba"));
	}

}
