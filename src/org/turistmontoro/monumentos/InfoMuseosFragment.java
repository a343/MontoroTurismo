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

	private void añadirImagen() {

		for (Iterator<ListaEntrada> it = datos.iterator(); it.hasNext();) {
			ListaEntrada auxiliar = (ListaEntrada) it.next();
			if (auxiliar.getTitulo().contains("Arqueológico")) {

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
				"Museo Arqueológico Municipal",
				"Se ubica en el interior de la Iglesia de Sta. Mª del Castillo de la Mota.",
				"Horario: Sábados, domingos y festivos de 10.30 a 14 horas. Abierto a partir del dia 6 de Diciembre. Las vitrinas del Museo Municipal de Montoro contienen materiales arqueológicos recuperados en el Término de Montoro, correspondientes a las diversas fases cronoculturales, desde materiales paleolíticos, neolíticos, de las Edades del Cobre y Bronce, Ibéricos romanos, medievales y modernos. Contienen también una importante colección de fósiles pertenecientes a las distintas etapas de la formación y evolución de la vida en la Tierra. Hay que destacar como más dignos de mención la colección de minerales, la colección de fósiles de plantas del Paleozoico, así como otras muchas muestras de fósiles de otros periodos geológicos. Una estela de guerrero datada hace unos 3.200 años y encontrada en la Campiña montoreña recientemente. Igualmente son interesantes algunas muestras de la cultura romana, entre ellas, una escultura de mármol blanco con coraza ( Thoracata ) del tiempo del Emperador Trajano, verdaderamente notable, y algunas muestras epigráficas curiosas.",
				"", "http://museodemontoro.es",
				"Plaza Sta. Mª de la Mota, Montoro, Córdoba"));
		datos.add(new ListaEntrada(
				R.drawable.museopintorrodriguezluna,
				"Museo Antonio Rodriguez Luna",
				"Se encuentra ubicado en la capilla barroca de San Jacinto",
				"Horario: Sábados, domingos y festivos de 10.30 a 14 horas. Este pintor, nombrado Hijo Predilecto de Montoro, donó a su pueblo doce cuadros, destacando el titulado 'Toro furioso'. Rodríguez Luna es una de las figuras claves en el proceso renovador, siendo uno de los pioneros del Surrealismo en España para posteriormente sufrir una de las más fructíferas evoluciones, primero hacia el realismo crítico cercano a su compromiso político, pues fue uno de los artistas participantes en la Exposición Internacional de París; luego en los años del exilio hacia la nueva figuración, evolucionando en sus últimos años hacia la abstracción. ",
				"", "http://museodemontoro.es",
				"Plaza Charco, Montoro, Córdoba"));
		datos.add(new ListaEntrada(
				R.drawable.museo_aceite,
				"Museo del Aceite",
				"Aceite con denominacion de origen Montoro-Adamuz",
				"Horario: Sábados, domingos y festivos de 10.30 a 14 horas. Edificio construido en 1784 por Francisco Luis de Mora para servir de almacenamiento de aceite, vino y trigo procedentes de los diezmos eclesiásticos. Posee dos plantas divididas en tres naves mediante gruesas pilastras que soportan bóvedas de crucería en la planta baja. La portada principal, de corte neoclásico, da paso a un pequeño patio de entrada. A través de paneles informativos puede conocer todo lo concerniente a la obtención y comercialización de este oro líquido que tanta riqueza aporta a nuestra ciudad.",
				"", "", "Sor Josefa Artola 4, Montoro, Córdoba"));
		datos.add(new ListaEntrada(
				R.drawable.igleisa_santiago,
				"Museo de la Semana Santa",
				"Iglesia de Santiago alberga actualmente el Museo de la Semana Santa de Montoro",
				"Horario: Sábados, domingos y festivos de 10.30 a 14 horas. La Semana Santa de Montoro ha sido declarada Fiesta de Interés Turístico Nacional de Andalucía en 1998. Iglesia de una nave con cúpula barroca sobre la capilla mayor. Posee dos portadas, siendo la principal de época anterior a la lateral, la cual está fechada en el año 1730, correspondiendose con las reformas barrocas del interior.",
				"", "", "Calle Santiago, Montoro, Córdoba"));

	}

}
