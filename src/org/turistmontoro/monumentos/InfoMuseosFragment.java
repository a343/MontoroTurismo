package org.turistmontoro.monumentos;

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

public class InfoMuseosFragment extends SherlockFragment {

	private ListView lista;
	ProgressDialog progress;
	ArrayList<ListaEntrada> datos;
	private static final String METHOD_NAME = "getMuseos";

	public static InfoMuseosFragment newInstance(String content) {

		InfoMuseosFragment fragment = new InfoMuseosFragment();

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
		// if (datos.isEmpty()) {
		// Toast.makeText(getSherlockActivity(),
		// "Ha fallado la carga de datos", 20).show();
		// } else {
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
			if (auxiliar.getTitulo().contains("Arqueol�gico")) {

				auxiliar.setIdImagen(R.drawable.museo_arqueologico);

			} else if (auxiliar.getTitulo().contains("Antonio Rodriguez Luna")) {

				auxiliar.setIdImagen(R.drawable.museopintorrodriguezluna);

			} else if (auxiliar.getTitulo().contains("Museo del Aceite")) {

				auxiliar.setIdImagen(R.drawable.museo_aceite);

			} else if (auxiliar.getTitulo().contains("Semana Santa")) {

				auxiliar.setIdImagen(R.drawable.igleisa_santiago);

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
				R.drawable.museo_arqueologico,
				"Museo Arqueol�gico Municipal",
				"Se ubica en el interior de la Iglesia de Sta. M� del Castillo de la Mota.",
				"Horario: S�bados, domingos y festivos de 10.30 a 14 horas. Abierto a partir del dia 6 de Diciembre. Las vitrinas del Museo Municipal de Montoro contienen materiales arqueol�gicos recuperados en el T�rmino de Montoro, correspondientes a las diversas fases cronoculturales, desde materiales paleol�ticos, neol�ticos, de las Edades del Cobre y Bronce, Ib�ricos romanos, medievales y modernos. Contienen tambi�n una importante colecci�n de f�siles pertenecientes a las distintas etapas de la formaci�n y evoluci�n de la vida en la Tierra. Hay que destacar como m�s dignos de menci�n la colecci�n de minerales, la colecci�n de f�siles de plantas del Paleozoico, as� como otras muchas muestras de f�siles de otros periodos geol�gicos. Una estela de guerrero datada hace unos 3.200 a�os y encontrada en la Campi�a montore�a recientemente. Igualmente son interesantes algunas muestras de la cultura romana, entre ellas, una escultura de m�rmol blanco con coraza ( Thoracata ) del tiempo del Emperador Trajano, verdaderamente notable, y algunas muestras epigr�ficas curiosas.",
				"", "http://museodemontoro.es",
				"Plaza Sta. M� de la Mota, Montoro, C�rdoba"));
		datos.add(new ListaEntrada(
				R.drawable.museopintorrodriguezluna,
				"Museo Antonio Rodriguez Luna",
				"Se encuentra ubicado en la capilla barroca de San Jacinto",
				"Horario: S�bados, domingos y festivos de 10.30 a 14 horas. Este pintor, nombrado Hijo Predilecto de Montoro, don� a su pueblo doce cuadros, destacando el titulado 'Toro furioso'. Rodr�guez Luna es una de las figuras claves en el proceso renovador, siendo uno de los pioneros del Surrealismo en Espa�a para posteriormente sufrir una de las m�s fruct�feras evoluciones, primero hacia el realismo cr�tico cercano a su compromiso pol�tico, pues fue uno de los artistas participantes en la Exposici�n Internacional de Par�s; luego en los a�os del exilio hacia la nueva figuraci�n, evolucionando en sus �ltimos a�os hacia la abstracci�n. ",
				"", "http://museodemontoro.es",
				"Plaza Charco, Montoro, C�rdoba"));
		datos.add(new ListaEntrada(
				R.drawable.museo_aceite,
				"Museo del Aceite",
				"Aceite con denominacion de origen Montoro-Adamuz",
				"Horario: S�bados, domingos y festivos de 10.30 a 14 horas. Edificio construido en 1784 por Francisco Luis de Mora para servir de almacenamiento de aceite, vino y trigo procedentes de los diezmos eclesi�sticos. Posee dos plantas divididas en tres naves mediante gruesas pilastras que soportan b�vedas de crucer�a en la planta baja. La portada principal, de corte neocl�sico, da paso a un peque�o patio de entrada. A trav�s de paneles informativos puede conocer todo lo concerniente a la obtenci�n y comercializaci�n de este oro l�quido que tanta riqueza aporta a nuestra ciudad.",
				"", "", "Sor Josefa Artola 4, Montoro, C�rdoba"));
		datos.add(new ListaEntrada(
				R.drawable.igleisa_santiago,
				"Museo de la Semana Santa",
				"Iglesia de Santiago alberga actualmente el Museo de la Semana Santa de Montoro",
				"Horario: S�bados, domingos y festivos de 10.30 a 14 horas. La Semana Santa de Montoro ha sido declarada Fiesta de Inter�s Tur�stico Nacional de Andaluc�a en 1998. Iglesia de una nave con c�pula barroca sobre la capilla mayor. Posee dos portadas, siendo la principal de �poca anterior a la lateral, la cual est� fechada en el a�o 1730, correspondiendose con las reformas barrocas del interior.",
				"", "", "Calle Santiago, Montoro, C�rdoba"));

	}

}
