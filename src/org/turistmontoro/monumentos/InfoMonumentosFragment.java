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

			if (auxiliar.getTitulo().contains("Ayuntamiento")) {

				auxiliar.setIdImagen(R.drawable.ayuntamiento);

			} else if (auxiliar.getTitulo().contains("Carmen")) {

				auxiliar.setIdImagen(R.drawable.iglesia_elcarmen);

			} else if (auxiliar.getTitulo().contains("Bartolom�")) {

				auxiliar.setIdImagen(R.drawable.san_bartololme);

			} else if (auxiliar.getTitulo().contains("Turismo")) {

				auxiliar.setIdImagen(R.drawable.oficina_turismo);

			} else if (auxiliar.getTitulo().contains("San Juan de Letr�n")) {

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
				"Edificio del siglo XVI y reformado en 1702, sirvi� primero como sede de las Casas Consistoriales y posteriormente de la Casa de Alba.",
				"Destaca por su fachada plateresca labrada en piedra molinaza. Edificado en el siglo XVI por Hern�n Ruiz I, su cuerpo superior es reedificado alrededor del a�o 1700. Tanto la sala de entrada en la planta baja, como el sal�n de plenos de la planta superior, destacan por sus bellos artesonados de estilo mud�jar. El actual edificio se ampli� con la inclusi�n de la antigua C�rcel, cuya portada se puede ver en el lateral de la fachada principal, as� como con parte del contiguo Colegio de Ni�as Educandas.",
				"957 160 425", "http://www.montoro.es",
				"Plaza Espa�a, Montoro, C�rdoba"));

		datos.add(new ListaEntrada(
				R.drawable.iglesia_elcarmen,
				"Parroquia de Nuestra Se�ora del Carmen",
				"Antigua Iglesia Barroca construida a comienzos del siglo XVII.",
				"Es la antigua Iglesia Barroca construida a comienzos del siglo XVII, correspondiente al convento de los Carmelitas Descalzos, que en 1891 pasa a convertirse en Parroquia. Se la conoce como Iglesia del Carmen, aunque su titular es San Juan  de la Cruz. Conserva el retablo Barroco del altar mayor, recientemente restaurado. Los colaterales al anterior y y el retablo de la Soledad con bicromia verde y oro.",
				"957 160 483", "", "Plaza del Charco, Montoro, C�rdoba"));
		datos.add(new ListaEntrada(
				R.drawable.san_bartololme,
				"Parroquia de San Bartolom�",
				"La Parroquia de San Bartolom� construida a finales del siglo XV.",
				"Es de estilo g�tico-mud�jar y est� dividida en tres naves. La central, m�s alta y ancha que las laterales, se cubre con un valioso artesanado mud�jar con tirantas y labores estrelladas de lazo, mientras que las laterales lo hacen mediante b�vedas de crucer�a. En la actualidad tambi�n tiene su sitio en la Semana Santa de la ciudad ya que guarda una imagen de Cristo crucificado muy antiguo que el d�a de mi�rcoles santo sale a procesionar por las calles de encrucijadas y estrechas de Montoro en la llamada Procesi�n del Silencio.",
				"957 160 243", "http://www.sanbartolomedemontoro.com",
				"Plaza Espa�a, Montoro, C�rdoba"));
		datos.add(new ListaEntrada(
				R.drawable.oficina_turismo,
				"Oficina de Turismo",
				"Horario: L-V de 9:30 a 15 h. S�bados de 10 a 13 h. Domingos y festivos de 10 a 14 h.",
				"Alberg� una de las posadas existentes en Montoro durante �poca moderna y contempor�nea. Las actuaciones realizadas en el edificio han ido encaminadas a su adaptaci�n como Oficina de Turismo, sala de exposici�n y venta de artesan�a.",
				"957 160 089", "http://www.montoro.es",
				"C/ Corredera 25, Montoro, C�rdoba"));
		datos.add(new ListaEntrada(
				R.drawable.san_juan_letran,
				"Iglesia de San Juan de Letr�n",
				"Antigua ermita del Colegio de Ni�as Educandas, fundado en el a�o 1764.",
				"Consta con tres naves cortas y, tras ellas, una cabecera cuadrada con c�pula y yeser�as Rococ�. En el exterior destaca la portada barroca del siglo XVII. En esta iglesia acoge a la imagen de Nuestro Padre Jesus Nazareno, imagen que procesiona en la madrugada del Viernes Santo.",
				"", "", "Plaza de Jes�s, Montoro, C�rdoba"));
		datos.add(new ListaEntrada(
				R.drawable.virgen_de_gracia,
				"Ermita de Ntra. Sra. de Gracia",
				"Construida  en el sigo XVIII sobre otra anterior. Iglesia que hace de sede de la cofradia de El resucitado",
				"Posee una �nica nave en la que se inserta el camar�n de la Virgen de Gracia.",
				"", "", "Calle Virgen de Gracia 4, Montoro, C�rdoba"));
		datos.add(new ListaEntrada(
				R.drawable.casa_conchas,
				"Casa de las conchas",
				"Situada al borde del Guadalquivir y con vistas a la sierra cordobesa."
						+ "",
				"Francisco del R�o construy� una casa en la localidad cordobesa de Montoro y en lugar de encalar y pintar sus paredes y z�calos decidi� cubrirla de conchas. Cuenta la leyenda que volc� un cami�n repleto de conchas y moluscos, tras lo cual, la calle qued� repleta de �stos moluscos. Viendo el desperdicio, Francisco quiso aprovecharlo, y decidi� que podr�a pegar sus conchas a las paredes de su casa. La Casa de Conchas cuenta con dos patios de columnas, una torre, un molino de viento y numerosas motivos ornamentales unidos por un elemento com�n, las conchas.",
				"", "", "Calle Grajas, Montoro, C�rdoba"));

		datos.add(new ListaEntrada(
				R.drawable.puentedonadas_opt,
				"Puente de las Donadas.",
				"Situada al borde del Guadalquivir enlaza el n�cleo principal de la localidad con el barrio de El Retamar.",
				"Su construcci�n se inici� en 1498 y se trata de uno de los puentes m�s bellos de la provincia de C�rdoba. Las mujeres donaron sus joyas y enseres para sufragar su construcci�n, por lo que se conoce como Puente de las Donadas.",
				"", "", "Calle Nuevo, Montoro, C�rdoba"));
		datos.add(new ListaEntrada(
				R.drawable.iglesia_san_sebastian_opt,
				"Iglesia de San Sebastian",
				"Se halla situada en la plazuela de su nombre.",
				"Montoro experimenta un fuerte desarrollo poblacional durante la Edad Moderna de ah� que se construya una nueva iglesia que complemente la Parroquia de San Bartolom�, la Iglesia de San Sebasti�n ( tambi�n conocida con el nombre de San Francisco Solano, ya que esta congregaci�n asisti� aqu� a sus enfermos durante el s. XVIII. Es aqu� donde comenzamos la ruta de Montoro y su arquitectura durante los siglos XVI-XVIII. Consta de tres naves, siendo la central la m�s ancha y elevada que las laterales. En la nave central y en un camar�n recientemente edificado, se encuentra la Virgen de las Angustias, a la derecha, la Virgen de los Dolores, y a la izquierda San Juan. En la nave del lado del Evangelio se encuentra la efigie de San Francisco Solano. La escultura perteneci� a la ermita que bajo la advocaci�n de este Santo existi� a la entrada de la calle del General Casta�os , y que fue derribada por reforma y ensanche de la v�a p�blica en 1867. La efigie de San Francisco Solano, lleva en la mano derecha un crucifijo en el que fija su mirada, y en la izquierda un coraz�n lanzando llamas, s�mbolo de la caridad. En la Sacrist�a hay un lienzo representando a la Virgen de las Angustias. En el muro de la iglesia y en el dintel de la puerta que comunica con las habitaciones del sacrist�n, hay una l�pida de m�rmol rojizo, muy gastada por el roce y por el tiempo, que seg�n se deduce del contenido, sirvi� de epitafio en la iglesia al sepulcro de Fray Buenaventura.",
				"", "", "Calle General Casta�os, Montoro, C�rdoba"));
		datos.add(new ListaEntrada(
				R.drawable.ermita_santa_ana,
				"Ermita Santa Ana",
				"Se halla situada en el barrio del retamar.",
				"Construida a finales del siglo XVI o principios del XVII, destaca por su doble p�rtico de arcos apuntados sobre columnas. Esta iglesia sufri� importantes da�os durante la Guerra Civil, por lo que tras la misma fue reformada.",
				"", "", "Calle Calvario, Montoro, C�rdoba"));

	}

}
