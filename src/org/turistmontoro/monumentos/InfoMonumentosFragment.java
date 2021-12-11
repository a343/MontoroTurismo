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
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.gson.Gson;

public class InfoMonumentosFragment extends SherlockFragment {

	private ListView lista;
	ProgressDialog progress;
	ArrayList<ListaEntrada> datos;
	private static final String METHOD_NAME = "getMonumentos";

	public static InfoMonumentosFragment newInstance(String content) {

		InfoMonumentosFragment fragment = new InfoMonumentosFragment();

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

			if (auxiliar.getTitulo().contains("Ayuntamiento")) {

				auxiliar.setIdImagen(R.drawable.ayuntamiento);

			} else if (auxiliar.getTitulo().contains("Carmen")) {

				auxiliar.setIdImagen(R.drawable.iglesia_elcarmen);

			} else if (auxiliar.getTitulo().contains("Bartolomé")) {

				auxiliar.setIdImagen(R.drawable.san_bartololme);

			} else if (auxiliar.getTitulo().contains("Turismo")) {

				auxiliar.setIdImagen(R.drawable.oficina_turismo);

			} else if (auxiliar.getTitulo().contains("San Juan de Letrán")) {

				auxiliar.setIdImagen(R.drawable.san_juan_letran);

			} else if (auxiliar.getTitulo().contains("Sra. de Gracia")) {

				auxiliar.setIdImagen(R.drawable.virgen_de_gracia);

			} else if (auxiliar.getTitulo().contains("Casa de las conchas")) {

				auxiliar.setIdImagen(R.drawable.casa_conchas);
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
				R.drawable.ayuntamiento,
				"Ayuntamiento o Casa Ducal",
				"Edificio del siglo XVI y reformado en 1702, sirvió primero como sede de las Casas Consistoriales y posteriormente de la Casa de Alba.",
				"Destaca por su fachada plateresca labrada en piedra molinaza. Edificado en el siglo XVI por Hernán Ruiz I, su cuerpo superior es reedificado alrededor del año 1700. Tanto la sala de entrada en la planta baja, como el salón de plenos de la planta superior, destacan por sus bellos artesonados de estilo mudéjar. El actual edificio se amplió con la inclusión de la antigua Cárcel, cuya portada se puede ver en el lateral de la fachada principal, así como con parte del contiguo Colegio de Niñas Educandas.",
				"957 160 425", "http://www.montoro.es",
				"Plaza España, Montoro, Córdoba"));

		datos.add(new ListaEntrada(
				R.drawable.iglesia_elcarmen,
				"Parroquia de Nuestra Señora del Carmen",
				"Antigua Iglesia Barroca construida a comienzos del siglo XVII.",
				"Es la antigua Iglesia Barroca construida a comienzos del siglo XVII, correspondiente al convento de los Carmelitas Descalzos, que en 1891 pasa a convertirse en Parroquia. Se la conoce como Iglesia del Carmen, aunque su titular es San Juan  de la Cruz. Conserva el retablo Barroco del altar mayor, recientemente restaurado. Los colaterales al anterior y y el retablo de la Soledad con bicromia verde y oro.",
				"957 160 483", "", "Plaza del Charco, Montoro, Córdoba"));
		datos.add(new ListaEntrada(
				R.drawable.san_bartololme,
				"Parroquia de San Bartolomé",
				"La Parroquia de San Bartolomé construida a finales del siglo XV.",
				"Es de estilo gótico-mudéjar y está dividida en tres naves. La central, más alta y ancha que las laterales, se cubre con un valioso artesanado mudéjar con tirantas y labores estrelladas de lazo, mientras que las laterales lo hacen mediante bóvedas de crucería. En la actualidad también tiene su sitio en la Semana Santa de la ciudad ya que guarda una imagen de Cristo crucificado muy antiguo que el día de miércoles santo sale a procesionar por las calles de encrucijadas y estrechas de Montoro en la llamada Procesión del Silencio.",
				"957 160 243", "http://www.sanbartolomedemontoro.com",
				"Plaza España, Montoro, Córdoba"));
		datos.add(new ListaEntrada(
				R.drawable.oficina_turismo,
				"Oficina de Turismo",
				"Horario: L-V de 9:30 a 15 h. Sábados de 10 a 13 h. Domingos y festivos de 10 a 14 h.",
				"Albergó una de las posadas existentes en Montoro durante época moderna y contemporánea. Las actuaciones realizadas en el edificio han ido encaminadas a su adaptación como Oficina de Turismo, sala de exposición y venta de artesanía.",
				"957 160 089", "http://www.montoro.es",
				"C/ Corredera 25, Montoro, Córdoba"));
		datos.add(new ListaEntrada(
				R.drawable.san_juan_letran,
				"Iglesia de San Juan de Letrán",
				"Antigua ermita del Colegio de Niñas Educandas, fundado en el año 1764.",
				"Consta con tres naves cortas y, tras ellas, una cabecera cuadrada con cúpula y yeserías Rococó. En el exterior destaca la portada barroca del siglo XVII. En esta iglesia acoge a la imagen de Nuestro Padre Jesus Nazareno, imagen que procesiona en la madrugada del Viernes Santo.",
				"", "", "Plaza de Jesús, Montoro, Córdoba"));
		datos.add(new ListaEntrada(
				R.drawable.virgen_de_gracia,
				"Ermita de Ntra. Sra. de Gracia",
				"Construida  en el sigo XVIII sobre otra anterior. Iglesia que hace de sede de la cofradia de El resucitado",
				"Posee una única nave en la que se inserta el camarín de la Virgen de Gracia.",
				"", "", "Calle Virgen de Gracia 4, Montoro, Córdoba"));
		datos.add(new ListaEntrada(
				R.drawable.casa_conchas,
				"Casa de las conchas",
				"Situada al borde del Guadalquivir y con vistas a la sierra cordobesa."
						+ "",
				"Francisco del Río construyó una casa en la localidad cordobesa de Montoro y en lugar de encalar y pintar sus paredes y zócalos decidió cubrirla de conchas. Cuenta la leyenda que volcó un camión repleto de conchas y moluscos, tras lo cual, la calle quedó repleta de éstos moluscos. Viendo el desperdicio, Francisco quiso aprovecharlo, y decidió que podría pegar sus conchas a las paredes de su casa. La Casa de Conchas cuenta con dos patios de columnas, una torre, un molino de viento y numerosas motivos ornamentales unidos por un elemento común, las conchas.",
				"", "", "Calle Grajas, Montoro, Córdoba"));

		datos.add(new ListaEntrada(
				R.drawable.puentedonadas_opt,
				"Puente de las Donadas.",
				"Situada al borde del Guadalquivir enlaza el núcleo principal de la localidad con el barrio de El Retamar.",
				"Su construcción se inició en 1498 y se trata de uno de los puentes más bellos de la provincia de Córdoba. Las mujeres donaron sus joyas y enseres para sufragar su construcción, por lo que se conoce como Puente de las Donadas.",
				"", "", "Calle Nuevo, Montoro, Córdoba"));
		datos.add(new ListaEntrada(
				R.drawable.iglesia_san_sebastian_opt,
				"Iglesia de San Sebastian",
				"Se halla situada en la plazuela de su nombre.",
				"Montoro experimenta un fuerte desarrollo poblacional durante la Edad Moderna de ahí que se construya una nueva iglesia que complemente la Parroquia de San Bartolomé, la Iglesia de San Sebastián ( también conocida con el nombre de San Francisco Solano, ya que esta congregación asistió aquí a sus enfermos durante el s. XVIII. Es aquí donde comenzamos la ruta de Montoro y su arquitectura durante los siglos XVI-XVIII. Consta de tres naves, siendo la central la más ancha y elevada que las laterales. En la nave central y en un camarín recientemente edificado, se encuentra la Virgen de las Angustias, a la derecha, la Virgen de los Dolores, y a la izquierda San Juan. En la nave del lado del Evangelio se encuentra la efigie de San Francisco Solano. La escultura perteneció a la ermita que bajo la advocación de este Santo existió a la entrada de la calle del General Castaños , y que fue derribada por reforma y ensanche de la vía pública en 1867. La efigie de San Francisco Solano, lleva en la mano derecha un crucifijo en el que fija su mirada, y en la izquierda un corazón lanzando llamas, símbolo de la caridad. En la Sacristía hay un lienzo representando a la Virgen de las Angustias. En el muro de la iglesia y en el dintel de la puerta que comunica con las habitaciones del sacristán, hay una lápida de mármol rojizo, muy gastada por el roce y por el tiempo, que según se deduce del contenido, sirvió de epitafio en la iglesia al sepulcro de Fray Buenaventura.",
				"", "", "Calle General Castaños, Montoro, Córdoba"));
		datos.add(new ListaEntrada(
				R.drawable.ermita_santa_ana,
				"Ermita Santa Ana",
				"Se halla situada en el barrio del retamar.",
				"Construida a finales del siglo XVI o principios del XVII, destaca por su doble pórtico de arcos apuntados sobre columnas. Esta iglesia sufrió importantes daños durante la Guerra Civil, por lo que tras la misma fue reformada.",
				"", "", "Calle Calvario, Montoro, Córdoba"));

	}

}
