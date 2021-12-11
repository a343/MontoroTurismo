package org.turistmontoro.excursiones;

import org.turistmontoro.R;
import org.turistmontoro.datos.ListaEntrada;
import org.turistmontoro.genericDetails.DetailsActivity;
import org.turistmontoro.monumentos.MainActivity;
import org.turistmontoro.utils.MiLocationListener;
import org.turistmontoro.utils.Utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;

public class DetailExcursionesActivity extends SherlockFragmentActivity {

	public GoogleMap mapa = null;
	private ListaEntrada detalleRutas;
	private CameraPosition camPos;
	private LocationManager locationManager;
	private MiLocationListener locationListener = new MiLocationListener();
	Context context;
	private static final String MY_AD_UNIT_ID = "ca-app-pub-4583169902024944/3859746913";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.detail_excursiones);

		// Buscamos proveedores y cargamos mapa
		locationManager = (LocationManager) this
				.getSystemService(Context.LOCATION_SERVICE);
		iniciamosLaEscucha();

		// Obtenemos la info del activity anterior y la mostramos
		Gson gson = new Gson();
		Bundle bundle = getIntent().getExtras();
		String detalle = bundle.getString("DetalleRutas");
		detalleRutas = gson.fromJson(detalle, ListaEntrada.class);

		ActionBar ab = getSupportActionBar();
		ab.setDisplayHomeAsUpEnabled(true);
		ab.setIcon(R.drawable.ic_escudo_montoro);

		mapa = ((SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.mapview)).getMap();
		mapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);

		// seteamos para habilitar el boton de buscar nuestra ubicacion
		mapa.setMyLocationEnabled(true);
		mostrarLineas();
		mostrarMarcador();

		// //Centra la vista en el incio de la ruta
		CameraUpdate camUpd3 = CameraUpdateFactory.newCameraPosition(camPos);
		mapa.animateCamera(camUpd3);

		// Admob
		AdView adView = (AdView) this.findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);

		// Adaptador
		InfoWindowAdapter infoadapter = new InfoWindowAdapter() {

			@Override
			public View getInfoWindow(Marker marker) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public View getInfoContents(Marker marker) {

				View myContentsView = getLayoutInflater().inflate(
						R.layout.custom_info_windows_markers, null);
				TextView tvTitle = ((TextView) myContentsView
						.findViewById(R.id.title));
				tvTitle.setText(marker.getTitle());

				return myContentsView;
			}
		};

		mapa.setInfoWindowAdapter(infoadapter);

		// id -> m0,m1,m2....
		mapa.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {

			@Override
			public void onInfoWindowClick(Marker marker) {

				Intent i = new Intent(DetailExcursionesActivity.this,
						DetailPuntosInteres.class);
				rellenarInfo(marker, detalleRutas.getId(), i);

			}

		});

		mapa.setOnMarkerClickListener(new OnMarkerClickListener() {
			public boolean onMarkerClick(Marker marker) {
				return false;
			}
		});
	}

	private void mostrarMarcador() {

		switch (detalleRutas.getId()) {

		case 1:

			mapa.addMarker(new MarkerOptions()
					.position(new LatLng(38.0271474302382, -4.379090443253517))
					.title("Inicio ruta 'MONTORO EN �POCA ROMANA'")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.icon_poi_general)));

			mapa.addMarker(new MarkerOptions()
					.position(new LatLng(38.01978286804116, -4.381198324263096))
					.title("Mirador del Meandro del Guadalquivir")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.icon_poi_general)));

			mapa.addMarker(new MarkerOptions()
					.position(new LatLng(38.01750180666542, -4.382779151201248))
					.title("LLanete de los moros")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.icon_poi_general)));

			mapa.addMarker(new MarkerOptions()
					.position(
							new LatLng(38.024067986359114, -4.383097663521767))
					.title("Puerta de la Torremocha")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.icon_poi_general)));

			// vestigios arqueol�gicos m�s importantes del pasado montore�
			mapa.addMarker(new MarkerOptions()
					.position(new LatLng(38.02716387067059, -4.38098106533289))
					.title("Fin ruta 'MONTORO EN �POCA ROMANA'")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.icon_poi_general)));
			break;

		case 2:

			mapa.addMarker(new MarkerOptions()
					.position(
							new LatLng(38.026571748675174, -4.381343834102154))
					.title("Inicio ruta 'MONTORO EN LA EDAD MEDIA'")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.icon_poi_general)));

			mapa.addMarker(new MarkerOptions()
					.position(new LatLng(38.02711421923303, -4.381523542106152))
					.title("Museo Arqueol�gico")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.icon_poi_museo)));

			mapa.addMarker(new MarkerOptions()
					.position(
							new LatLng(38.022409857008256, -4.383345432579517))
					.title("Castillo de Cava")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.icon_poi_general)));

			mapa.addMarker(new MarkerOptions()
					.position(new LatLng(38.02375580240047, -4.382890127599239))
					.title("Puerta de la Torremocha")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.icon_poi_general)));

			mapa.addMarker(new MarkerOptions()
					.position(new LatLng(38.02608368020323, -4.381601996719837))
					.title("Fin de ruta")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.icon_poi_general)));

			break;
		case 3:

			mapa.addMarker(new MarkerOptions()
					.position(new LatLng(38.020813495690604, -4.38425000756979))
					.title("Inicio ruta 'MONTORO EN LOS SIGLOS XVI-XVIII'")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.icon_poi_general)));

			mapa.addMarker(new MarkerOptions()
					.position(
							new LatLng(38.02357673181377, -4.3828679993748665))
					.title("Iglesia Ntra Se�ora del Carmen")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.icon_marker_iglesia)));
			mapa.addMarker(new MarkerOptions()
					.position(new LatLng(38.025046259794074, -4.38211765140295))
					.title("Iglesia del Hospital Jesus Nazareno")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.icon_marker_iglesia)));

			mapa.addMarker(new MarkerOptions()
					.position(new LatLng(38.02621837474922, -4.38156109303236))
					.title("Casa Ducal (Ayuntamiento)")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.icon_poi_general)));

			mapa.addMarker(new MarkerOptions()
					.position(
							new LatLng(38.026261688236865, -4.381353221833706))
					.title("Iglesia de San Bartolom�")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.icon_marker_iglesia)));

			mapa.addMarker(new MarkerOptions()
					.position(new LatLng(38.0266513, -4.3814148))
					.title("Antigua Casa Se�orial Postigo")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.icon_poi_general)));

			mapa.addMarker(new MarkerOptions()
					.position(new LatLng(38.0265398, -4.3817597))
					.title("Antigua Casa Se�orial Manuel Criado Hoyo")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.icon_poi_general)));

			mapa.addMarker(new MarkerOptions()
					.position(new LatLng(38.02633035590872, -4.382657445967197))
					.title("Iglesia San Juan de Letr�n")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.icon_marker_iglesia)));

			mapa.addMarker(new MarkerOptions()
					.position(
							new LatLng(38.025764638016234, -4.382839165627956))
					.title("Tercias (Museo del Aceite)")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.icon_poi_museo)));

			mapa.addMarker(new MarkerOptions()
					.position(new LatLng(38.0260235, -4.3847863))
					.title("Casa Se�orial Plaza San Miguel")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.icon_poi_general)));

			mapa.addMarker(new MarkerOptions()
					.position(new LatLng(38.02676296076152, -4.377745985984802))
					.title("Iglesia de Santa Ana")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.icon_marker_iglesia)));

			mapa.addMarker(new MarkerOptions()
					.position(new LatLng(38.02712154810427, -4.378904700279236))
					.title("Puente de las donadas")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.icon_poi_general)));

			mapa.addMarker(new MarkerOptions()
					.position(new LatLng(38.02783627459135, -4.380449652671814))
					.title("FIN DE RUTA: Iglesia de Santiago")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.icon_marker_iglesia)));
			break;
		case 4:
			mapa.addMarker(new MarkerOptions()
					.position(new LatLng(38.0212968, -4.3852081))
					.title("Inicio ruta 'Her�ldica de Montoro'")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.icon_poi_general)));

			mapa.addMarker(new MarkerOptions()
					.position(
							new LatLng(38.023181084629584, -4.383692108094692))
					.title("Museo Pintor Rodriguez Luna")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.icon_poi_museo)));
			mapa.addMarker(new MarkerOptions()
					.position(new LatLng(38.02356405423352, -4.382877051830292))
					.title("Iglesia Ntra Se�ora del Carmen")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.icon_marker_iglesia)));

			mapa.addMarker(new MarkerOptions()
					.position(new LatLng(38.02621837474922, -4.38156109303236))
					.title("Casa Ducal (Ayuntamiento)")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.icon_poi_general)));

			mapa.addMarker(new MarkerOptions()
					.position(new LatLng(38.0266513, -4.3814148))
					.title("Antigua Casa Se�orial Postigo")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.icon_poi_general)));

			mapa.addMarker(new MarkerOptions()
					.position(new LatLng(38.0258802, -4.3833487))
					.title("Antigua Casa Se�orial Alvaro Perez")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.icon_poi_general)));

			mapa.addMarker(new MarkerOptions()
					.position(new LatLng(38.02783627459135, -4.380449652671814))
					.title("Iglesia de Santiago")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.icon_marker_iglesia)));

			mapa.addMarker(new MarkerOptions()
					.position(new LatLng(38.0266428, -4.3828618))
					.title("Antigua Casa Se�orial Antonio Garijo")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.icon_poi_general)));

			mapa.addMarker(new MarkerOptions()
					.position(new LatLng(38.0278847, -4.381863))
					.title("Antigua Casa Se�orial Coracha")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.icon_poi_general)));

			mapa.addMarker(new MarkerOptions()
					.position(new LatLng(38.0283027, -4.3830353))
					.title("Antigua Casa Se�orial Grajas")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.icon_poi_general)));

			mapa.addMarker(new MarkerOptions()
					.position(new LatLng(38.0283027, -4.3830353))
					.title("Antigua Casa Se�orial Grajas")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.icon_poi_general)));

			mapa.addMarker(new MarkerOptions()
					.position(new LatLng(38.0228864, -4.3828556))
					.title("Antigua Casa Se�orial Rosario")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.icon_poi_general)));

			mapa.addMarker(new MarkerOptions()
					.position(new LatLng(38.0250416, -4.3820927))
					.title("Antigua Casa Se�orial Salazar")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.icon_poi_general)));

			mapa.addMarker(new MarkerOptions()
					.position(new LatLng(38.0275532, -4.3802787))
					.title("Antigua Casa Se�orial Santiago")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.icon_poi_general)));

			mapa.addMarker(new MarkerOptions()
					.position(new LatLng(38.0240936, -4.3830532))
					.title("Antigua Casa Se�orial Santos Isasa")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.icon_poi_general)));

			mapa.addMarker(new MarkerOptions()
					.position(new LatLng(38.0259617, -4.3817138))
					.title("Antigua Casa Se�orial Plaza Espa�a")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.icon_poi_general)));

			mapa.addMarker(new MarkerOptions()
					.position(new LatLng(38.0261469, -4.3823263))
					.title("Fin de ruta: \n"
							+ "Antigua Casa Se�orial Plaza de Jesus")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.icon_poi_general)));
			break;
		case 5:
			mapa.addMarker(new MarkerOptions()
					.position(
							new LatLng(38.024022558657634, -4.377883784472942))
					.title("Inicio ruta: 'Montoro en los siglos XIX Y XX'")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.icon_poi_general)));
			mapa.addMarker(new MarkerOptions()
					.position(new LatLng(38.02830584444736, -4.383227415382862))
					.title("Casa de las conchas")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.icon_poi_museo)));
			mapa.addMarker(new MarkerOptions()
					.position(
							new LatLng(38.026082623774435, -4.381606020033359))
					.title("Arquitectura Modernista")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.icon_poi_general)));
			mapa.addMarker(new MarkerOptions()
					.position(new LatLng(38.01748807148146, -4.382766745984554))
					.title("FIN DE RUTA: Plaza de Toros")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.icon_poi_general)));

			break;
		}
	}

	private void mostrarLineas() {

		PolylineOptions lineas = new PolylineOptions();

		switch (detalleRutas.getId()) {

		case 1:
			camPos = new CameraPosition.Builder()
					.target(new LatLng(38.0271474302382, -4.379090443253517)) // Centramos
					.zoom(15) // Establecemos el zoom
					.build();
			lineas = new PolylineOptions().addAll(Utils.obtenerRuta1());

			break;
		case 2:
			camPos = new CameraPosition.Builder()
					.target(new LatLng(38.02649304521288, -4.3812184408307076)) // Centramos
					.zoom(17) // Establecemos el zoom
					.build();
			lineas = new PolylineOptions().addAll(Utils.obtenerRuta2());
			break;
		case 3:
			camPos = new CameraPosition.Builder()
					.target(new LatLng(38.020813495690604, -4.38425000756979)) // Centramos
					.zoom(15) // Establecemos el zoom
					.build();
			lineas = new PolylineOptions().addAll(Utils.obtenerRuta3());
			break;
		case 4:
			camPos = new CameraPosition.Builder()
					.target(new LatLng(38.0212968, -4.3852081)) // Centramos
					.zoom(15) // Establecemos el zoom
					.build();
			lineas = new PolylineOptions().addAll(Utils.obtenerRuta4());
			break;
		case 5:

			camPos = new CameraPosition.Builder()
					.target(new LatLng(38.024022558657634, -4.377883784472942)) // Centramos
					.zoom(15) // Establecemos el zoom
					.build();
			lineas = new PolylineOptions().addAll(Utils.obtenerRuta5());
			break;
		}

		lineas.width(5);
		lineas.color(Color.GRAY);

		mapa.addPolyline(lineas);
	}

	private void rellenarInfo(Marker marker, int id, Intent i) {

		if (detalleRutas.getId() == 1) {

			if ("m0".equals(marker.getId())) {
				i.putExtra(
						"detalle",
						"INICIO RUTA \n"
								+ "El trazado de la Ruta 1 emprende un recorrido que casi en su totalidad bordea el exterior del casco urbano de Montoro, por donde durante la �poca romana discurr�a la muralla. Tomamos la salida en el Puente de las Donadas y seguimos por la calle Camino Nuevo bordeando el escarpe que separa la cornisa de Montoro, donde se ubicaba la muralla y el r�o Guadalquivir.");
				i.putExtra("Titulo", "Resumen Trazado Ruta");
				startActivity(i);
			} else if ("m1".equals(marker.getId())) {
				i.putExtra(
						"detalle",
						"Llegamos a la calle Corredera por donde discurr�a la muralla as� como por la Plaza del Charco y la calle Herrer�as. En la Redonda llegamos al mirador en el que adem�s del R�o Guadalquivir podemos ver el escarpe por donde discurr�a la muralla que se extend�a hasta el Realejo.");
				i.putExtra("Titulo", "Mirador del Guadalquivir");
				startActivity(i);
			} else if ("m2".equals(marker.getId())) {
				i.putExtra(
						"detalle",
						"Cerca del Llanete de los Moros estar�a la Puerta principal de Montoro durante �poca romana, cuando el municipio era conocido como �pora y estaba integrado en el Conventos Cordubensis. Desde el Llanete de los Moros la muralla se dirig�a al Oeste por el camino de la Bastilla rodeando el Cerro del Palomarejo donde aparece gran cantidad de vestigios de origen ib�rico. Esta zona junto con el Llanete de los Moros supone la localizaci�n de poblaci�n en la zona anterior a la ocupaci�n romana. Llegamos a la zona de la Bastilla bordeando el Cerro del Palomarejo a trav�s de las calles Cervantes, M�sico Juan Mohedo Canales y Clara Campoamor donde la muralla hac�a un requiebro hacia el Norte y bajaba por las actuales calles General Casta�os, El Santo hasta llegar a la Plaza del Charco donde hac�a otro requiebro en direcci�n a la calle Santos Isasa.");
				i.putExtra("Titulo", "LLanete de los Moros");
				startActivity(i);
			} else if ("m3".equals(marker.getId())) {
				i.putExtra(
						"detalle",
						"En esta �ltima calle existi� otra puerta que dar�a acceso hacia el r�o a trav�s de la Vaguada de la Paloma. Esta puerta se remodelar� al igual que el resto de la muralla durante la Edad Media siendo conocida como Puerta de la Torremocha. Era una de las puertas que comunicaba el interior del recinto amurallado con el r�o Guadalquivir a trav�s de la Vaguada de la Paloma. Esta puerta parece ser de origen romano.");
				i.putExtra("Titulo", "Puerta Torremocha");
				startActivity(i);
			} else {

				i.putExtra(
						"detalle",
						"FIN DE RUTA \n"
								+ "Desde la Plaza de San Miguel la muralla bordeaba el r�o por las calles Clavel, Col�n, La Paz, Cantones, Los Laras, Criado, Grajas y Coracha hasta la calle Puente. En la calle Coracha tomamos las calles Dotes, Olivares y Capit�n para acceder a la Plaza de Santa M� de la Mota donde actualmente, la iglesia que lleva el mismo nombre, alberga el Museo Arqueol�gico de Montoro donde podemos completar nuestra visita viendo los vestigios arqueol�gicos m�s importantes del pasado montore�o.");
				i.putExtra("Titulo", "Museo Arqueol�gico");
				startActivity(i);
			}
		} else if (detalleRutas.getId() == 2) {
			if ("m0".equals(marker.getId())) {
				i.putExtra(
						"detalle",
						"INICIO RUTA \n"
								+ "Comenzamos la Ruta de los edificios de Montoro cuya cronolog�a se remonta a la Edad Media contemplando la Iglesia Parroquial de San Bartolom�. Nos introducimos por la calle Postigo en direcci�n a la calle Mota donde encontramos la antigua portada de entrada a la Iglesia de Santa Mar�a de la Mota, hoy Museo Arqueol�gico. ");
				i.putExtra("Titulo", "Museo Arqueol�gico");
				startActivity(i);
			} else if ("m1".equals(marker.getId())) {
				i.putExtra(
						"detalle",
						"A trav�s de las calles Olivares y Capit�n accedemos a la Plaza de Sta. M� de la Mota donde durante la Edad Media se ubicaba el Castillo o Fortaleza de la Mota, y donde durante el siglo XIII se edific� la Iglesia de Santa M� de la Mota, probablemente arrasando una mezquita previa.");
				startActivity(i);
			} else if ("m2".equals(marker.getId())) {
				i.putExtra(
						"detalle",
						"Tras la conquista cristiana la poblaci�n de Montoro comenz� a crecer y ocupar por tanto esta zona que hab�a sido abandonada durante las invasiones b�rbaras, reedificando la muralla y estableciendo una fortaleza nueva, conocida con el nombre de Castillo de la Cava o de Julia.");
				i.putExtra("Titulo", "Castillo de Cava");
				startActivity(i);
			} else if ("m3".equals(marker.getId())) {
				i.putExtra(
						"detalle",
						"Seguimos el per�metro de la ciudad amurallada durante la Edad Media a trav�s de las calles Antigua Guatemala, El Santo, General Casta�os, Antonio Enrique G�mez, Domingo de Lara, Molino, Plaza del Charco, Santos Isasa. En esta �ltima calle existi� otra puerta que dar�a acceso hacia el r�o a trav�s de la Vaguada de la Paloma. Esta puerta se remodelar� al igual que el resto de la muralla durante la Edad Media siendo conocida como Puerta de la Torremocha.");
				i.putExtra("Titulo", "Puerta Torremocha");
				startActivity(i);
			} else {

				i.putExtra(
						"detalle",
						"FIN DE RUTA\n"
								+ "Nos adentramos por la puerta de la Torremocha y pasamos por las calles Batalla de Lepanto y Sor Josefa Artola para llegar as�, a trav�s del descenso por la calle Salazar, a la Plaza de Espa�a donde podemos contemplar de nuevo la Parroquia de San Bartolom�. El interior de la parroquia ofrece planta de tres naves de arcos apuntados y capilla mayor de tipo poligonal. \n"
								+ "Escultura existente en la Parroquia \n"
								+ "- Crucificado de peque�as dimensiones: Este crucificado que a d�a se hoy se encuentra junto al p�lpito de la Parroquia de San Bartolom� pudo ser realizado entre 1600-1620 \n"
								+ "- Relieve marmolado de Mar�a con el ni�o: se encuentra ubicado el la Sacrist�a de la Parroquia de San Bartolom�, concretamente actualmente se encuentra justamente en frente de la entrada. Esta obra podemos enmarcarla en el estilo renacentista y nos muestra a Mar�a con el ni�o, entronizada y rodeada de Santos.\n"
								+ "- Arc�ngeles en escayola: Los arc�ngeles realizados por Amadeo Ruiz Olmo fueron San Miguel, San Rafael, San Grabriel y Custodio, todos ellos se encuentran en cuatro de las columnas de la nave central. "
								+ "Las otras cuatro columnas (las mas cercanas a la cabecera del templo) se encuentran ocupadas por efigies de los cuatro evangelistas");
				i.putExtra("Titulo", "Parroquia de San Bartolm�");
				startActivity(i);
			}
		} else if (detalleRutas.getId() == 3) {

			if ("m0".equals(marker.getId())) {

				i.putExtra(
						"detalle",
						"Montoro experimenta un fuerte desarrollo poblacional durante la Edad Moderna de ah� que se construya una nueva iglesia que complemente la Parroquia de San Bartolom�, la Iglesia de San Sebasti�n ( tambi�n conocida con el nombre de San Francisco Solano, ya que esta congregaci�n asisti� aqu� a sus enfermos durante el s. XVIII. Es aqu� donde comenzamos la ruta de Montoro y su arquitectura durante los siglos XVI-XVIII. Consta de tres naves, siendo la central la m�s ancha y elevada que las laterales. En la nave central y en un camar�n recientemente edificado, se encuentra la Virgen de las Angustias, a la derecha, la Virgen de los Dolores, y a la izquierda San Juan. En la nave del lado del Evangelio se encuentra la efigie de San Francisco Solano. La escultura perteneci� a la ermita que bajo la advocaci�n de este Santo existi� a la entrada de la calle del General Casta�os , y que fue derribada por reforma y ensanche de la v�a p�blica en 1867. La efigie de San Francisco Solano, lleva en la mano derecha un crucifijo en el que fija su mirada, y en la izquierda un coraz�n lanzando llamas, s�mbolo de la caridad. En la Sacrist�a hay un lienzo representando a la Virgen de las Angustias. En el muro de la iglesia y en el dintel de la puerta que comunica con las habitaciones del sacrist�n, hay una l�pida de m�rmol rojizo, muy gastada por el roce y por el tiempo, que seg�n se deduce del contenido, sirvi� de epitafio en la iglesia al sepulcro de Fray Buenaventura.");
				i.putExtra("Titulo", "Iglesia de San Sebastian ");
				startActivity(i);

			} else if ("m1".equals(marker.getId())) {

				i.putExtra(
						"detalle",
						"La Iglesia del Carmen, antiguo convento de San Juan de la Cruz de los Carmelitas Descalzos. Es una obra del siglo XVII, de estilo Barroco. Est� realizada en piedra molinaza. La planta es de cruz latina, con una nave central muy ancha y dos naves laterales bastantes angostas que se comunican con la nave central mediante arcos de medio punto. La cabecera est� rematada en un �bside, con lo que se trata de las t�picas iglesias de la orden carmelita.Tiene un coro alto a los pies de la iglesia que abarca los dos primeros tramos de la b�veda de la nave central cubierta por b�veda de ca��n. En este coro, nos encontramos con un �rgano, realizado entre 1790 y 1793. En el crucero nos encontramos con una c�pula de media naranja decorada con pinturas al fresco, dividida en ocho pa�os, en el centro de cada uno se ubica un �valo con pinturas realizadas en la postguerra, muy ennegrecidas. Fuera de estos �valos, en cada pa�o se reproducen un motivo b�blico (las Tablas de la Ley�) junto a otro de car�cter pasionista (clavos�). El �bside central est� ocupado por un retablo tambi�n barroco y policromado, donde se encuentra varias im�genes, a destacar la del Sant�simo Cristo de las Penas, del siglo XVII. Esta iglesia, que en la actualidad tiene la funci�n de Iglesia Parroquial, bajo el nombre de Ntra. Sra. Del Carmen, antiguamente recib�a el nombre de Convento Carmelita de San Juan de la Cruz, perteneciendo dicha iglesia al convento desaparecido, que se encuentra en lo que es hoy el casino primitivo.");
				i.putExtra("Titulo", "Parroquia de Ntra. Sra. Del Carmen");
				startActivity(i);
			} else if ("m2".equals(marker.getId())) {
				i.putExtra(
						"detalle",
						"Tomamos la calle Salazar, o como la llaman los montore�os 'calle alta', donde encontramos el Hospital de Jes�s Nazareno que fue mandado a construir por el Cardenal Salazar para ampliar las funciones de la Casa de la Caridad. Es una obra de siglo XVIII, de estilo barroco. Al pertenecer esta iglesia al hospital, en conjunto, tiene todo una planta irregular, estando compuesto el edificio en la iglesia, un patio central, el cual es de destacar y dos patios m�s en el interio. La Iglesia es de una sola nave cubierta de b�veda de ca��n con lunetos y dividida en tres tramos por arcos de medio punto con coro alto a los pies. La cabecera es plana y est� cubierta por c�pula sobre pechinas dividida en ocho segmentos, en cada uno de los cuales se puede observar una nutrida decoraci�n de escayola policromada a base de cartelas con s�mbolos pasionistas, angelotes y motivos vegetales y geom�tricos. En la fachada del edificio se destacan dos portadas, una perteneciente a la iglesia y otra al hospital. mbas portadas fueron realizadas en el siglo XVIII. Este hospital junto con la iglesia, pertenecen hoy en d�a a una residencia de ancianos. Descendemos la calle para llegar a la Plaza de Espa�a donde encontramos varios edificios de inter�s");
				i.putExtra("Titulo", "Parroquia del Hospital Jesus Nazareno");
				startActivity(i);
			} else if ("m3".equals(marker.getId())) {
				i.putExtra(
						"detalle",
						"Edificio construido en el siglo XVI y reformado en 1702, que sirvi� primero como sede de las Casas Consistoriales y posteriormente de la Casa de Alba en Montoro. En el interior destacan tres magn�ficos artesonados de tradici�n mud�jar. El actual edificio se ampli� con la inclusi�n de la antigua C�rcel, cuya portada se puede ver en el lateral de la fachada principal, as� como con parte del contiguo Colegio de Ni�as Educandas.");
				i.putExtra("Titulo", "Casa Ducal (Ayuntamiento)");
				startActivity(i);
			} else if ("m4".equals(marker.getId())) {
				i.putExtra(
						"detalle",
						"El interior de la parroquia ofrece planta de tres naves de arcos apuntados y capilla mayor de tipo poligonal. \n"
								+ "Escultura existente en la Parroquia \n"
								+ "- Crucificado de peque�as dimensiones: Este crucificado que a d�a se hoy se encuentra junto al p�lpito de la Parroquia de San Bartolom� pudo ser realizado entre 1600-1620 \n"
								+ "- Relieve marmolado de Mar�a con el ni�o: se encuentra ubicado el la Sacrist�a de la Parroquia de San Bartolom�, concretamente actualmente se encuentra justamente en frente de la entrada. Esta obra podemos enmarcarla en el estilo renacentista y nos muestra a Mar�a con el ni�o, entronizada y rodeada de Santos.\n"
								+ "- Arc�ngeles en escayola: Los arc�ngeles realizados por Amadeo Ruiz Olmo fueron San Miguel, San Rafael, San Grabriel y Custodio, todos ellos se encuentran en cuatro de las columnas de la nave central. "
								+ "Las otras cuatro columnas (las mas cercanas a la cabecera del templo) se encuentran ocupadas por efigies de los cuatro evangelistas");
				i.putExtra("Titulo", "Parroquia de San Bartolm�");
				startActivity(i);
			} else if ("m7".equals(marker.getId())) {
				i.putExtra(
						"detalle",
						"La Iglesia de San Juan de Letr�n es la antigua ermita del Colegio de Ni�as Educandas, fundada en 1764. La Iglesia posee una portada barroca del siglo XVII. Presenta una planta de tres naves, destacando la central que culmina en una c�pula con linterna sobre el altar mayor.");
				i.putExtra("Titulo", "Iglesia San Juan de Letr�n");
				startActivity(i);

			} else if ("m8".equals(marker.getId())) {

				i.putExtra(
						"detalle",
						"Edificio construido en 1784 para servir de almacenamiento de aceite, vino y trigo procedentes de los diezmos eclesi�sticos.\n"
								+ "Este edificio alberga el Museo del Aceite de Montoro, donde a trav�s de paneles informativos y puntos de informaci�n el visitante puede conocer de primera mano todo lo concerniente a la obtenci�n y comercializaci�n de este oro l�quido que tanta riqueza aporta a nuestra ciudad.");
				i.putExtra("Titulo", "Tercias (Museo del Aceite)");
				startActivity(i);

			} else if ("m10".equals(marker.getId())) {

				i.putExtra(
						"detalle",
						"Construida a finales del siglo XVI o principios del XVII, destaca por su doble p�rtico de arcos apuntados sobre columnas. Esta iglesia sufri� importantes da�os durante la Guerra Civil, por lo que tras la misma fue reformada");
				i.putExtra("Titulo", "Iglesia de Santa Ana");
				startActivity(i);

			} else if ("m11".equals(marker.getId())) {
				i.putExtra(
						"detalle",
						"Situado sobre el rio Guadalquivir enlaza el n�cleo principal de la localidad con el barrio de El Retamar. Su construcci�n se inici� en 1498 y se trata de uno de los puentes m�s bellos de la provincia de C�rdoba. Cuenta con cuatro arcos de medio punto labrados en piedra molinaza y es uno de los monumentos m�s emblem�ticos de Montoro. Las mujeres donaron sus joyas y enseres para sufragar su construcci�n, por lo que se conoce como Puente de las Donadas.\n");
				i.putExtra("Titulo", "Puente de las Donadas");
				startActivity(i);
			}

			else if ("m12".equals(marker.getId())) {
				i.putExtra(
						"detalle",
						"Seg�n sus caracter�sticas arquitect�nicas se fecha a mediados del siglo XVI. La fachada principal es de mediados del siglo XVI, aunque hay quien piensa que es de finales del XVII. La fachada lateral con el escudo del orden de Santiago est� fechada en 1730. La mayor�a de ella est� construida con piedra molinaza y piedra natural, esta piedra natural esta utilizada para salvar el gran desnivel. La iglesia presenta una planta rectangular que se divide en una nave, un presbiterio, la sacrist�a, la Casa del Santero y un patio. El presbiterio se cubre mediante c�pula sobre pechinas decoradas con los Cuatro Evangelistas y la c�pula con elementos figurativos y ornamentales de mediados del siglo XVII. En la fachada se encuentra la puerta principal de finales del siglo XVI compuesto por un arco de medio punto configurado por sillares de piedra encalados. En el pasado a�o 2005 ha sufrido una serie de ampliaciones y la rehabilitaci�n de la iglesia para dedicarla como museo de la Semana Santa y como sede de la Agrupaci�n de Hermandades y Cofrad�as de Montoro.\n");
				i.putExtra("Titulo", "Iglesia de Santiago");
				startActivity(i);
			}

		} else if (detalleRutas.getId() == 4) {

			if ("m0".equals(marker.getId())) {

				i.putExtra(
						"detalle",
						"El trazado de la Ruta 4 nos va a llevar a dar un paseo por todos aquellos edificios de Montoro que presentan en sus fachadas alg�n escudo her�ldico como reflejo de la importancia de las casas solariegas en Montoro, sobre todo durante los siglos XVII y XVIII. Se nos muestra, por tanto, un recorrido que podr�a simular al que ya hiciera cualquier montore�o de la �poca al visitar las casas de las familias que viv�an en ellas.");
				i.putExtra("Titulo", "Resumen Ruta 4");
				startActivity(i);

			} else if ("m1".equals(marker.getId())) {

				i.putExtra(
						"detalle",
						"Posee dos salas bien diferenciadas, una dedicada a los contenidos de tipo arqueol�gico, ubicadas en ala Iglesia de Santa Mar�a de la Mota, y la otra dedica a la colecci�n pict�rica del pintor montore�o Antonio Rodr�guez Luna.\n"
								+ "Primera Sala: Obra aparetemente abstrata en la que domina el azul agrisado en toda su composici�n . Si fijamos nuestra atenci�n en el cuadro, podremos observar, en su franja central, lo que podr�amos denominar bodeg�n: una mesa y sobre ella una cabeza de ajo, apenas se�alada por un fin�simo relieve.\n"
								+ "Segunda Sala: Obra completamente abstrata en la que predominan el negro, el amarillo y el ocre, aunque tambi�n participan el blanco y el rojo; todos ellos sabiamente combinados, para realizar una exquisita estructura pl�stica.\n"
								+ "Tercera Sala: Predominio del color negro, pero sin olvidar otros colores: el gris, azul, rojo y amarillo, formando en su centro, una peque�a referencia, como es habitual en �l, al bodeg�n.\n");
				i.putExtra("Titulo", "Museo Pintor Rodriguez Luna");
				startActivity(i);
			} else if ("m2".equals(marker.getId())) {

				i.putExtra(
						"detalle",
						"Es una obra del siglo XVII, de estilo Barroco. Est� realizada en piedra molinaza. La planta es de cruz latina, con una nave central muy ancha y dos naves laterales bastantes angostas que se comunican con la nave central mediante arcos de medio punto. La cabecera est� rematada en un �bside, con lo que se trata de las t�picas iglesias de la orden carmelita.Tiene un coro alto a los pies de la iglesia que abarca los dos primeros tramos de la b�veda de la nave central cubierta por b�veda de ca��n. En este coro, nos encontramos con un �rgano, realizado entre 1790 y 1793. En el crucero nos encontramos con una c�pula de media naranja decorada con pinturas al fresco, dividida en ocho pa�os, en el centro de cada uno se ubica un �valo con pinturas realizadas en la postguerra, muy ennegrecidas. Fuera de estos �valos, en cada pa�o se reproducen un motivo b�blico (las Tablas de la Ley�) junto a otro de car�cter pasionista (clavos�).  El �bside central est� ocupado por un retablo tambi�n barroco y policromado, donde se encuentra varias im�genes, a destacar la del Sant�simo Cristo de las Penas, del siglo XVII. Esta iglesia, que en la actualidad tiene la funci�n de Iglesia Parroquial, bajo el nombre de Ntra. Sra. Del Carmen, antiguamente recib�a el nombre de Convento Carmelita de San Juan de la Cruz, perteneciendo dicha iglesia al convento desaparecido, que se encuentra en lo que es hoy el casino primitivo.\n");
				i.putExtra("Titulo", "Iglesia de Ntra. Sra. Del Carmen");
				startActivity(i);
			} else if ("m3".equals(marker.getId())) {
				i.putExtra(
						"detalle",
						"Edificio construido en el siglo XVI y reformado en 1702, que sirvi� primero como sede de las Casas Consistoriales y posteriormente de la Casa de Alba en Montoro. En el interior destacan tres magn�ficos artesonados de tradici�n mud�jar. El actual edificio se ampli� con la inclusi�n de la antigua C�rcel, cuya portada se puede ver en el lateral de la fachada principal, as� como con parte del contiguo Colegio de Ni�as Educandas.");
				i.putExtra("Titulo", "Casa Ducal (Ayuntamiento)");
				startActivity(i);
			} else if ("m6".equals(marker.getId())) {
				i.putExtra(
						"detalle",
						"Seg�n sus caracter�sticas arquitect�nicas se fecha a mediados del siglo XVI.  La fachada principal es de mediados del siglo XVI, aunque hay quien piensa que es de finales del XVII. La fachada lateral con el escudo del orden de Santiago est� fechada en 1730. La mayor�a de ella est� construida con piedra molinaza y piedra natural, esta piedra natural esta utilizada para salvar el gran desnivel. La iglesia presenta una planta rectangular que se divide en una nave, un presbiterio, la sacrist�a, la Casa del Santero y un patio. El presbiterio se cubre mediante c�pula sobre pechinas decoradas con los Cuatro Evangelistas y la c�pula con elementos figurativos y ornamentales de mediados del siglo XVII. En la fachada se encuentra la puerta principal de finales del siglo XVI compuesto por un arco de medio punto configurado por sillares de piedra encalados. En el pasado a�o 2005 ha sufrido una serie de ampliaciones y la rehabilitaci�n de la iglesia para dedicarla como museo de la Semana Santa y como sede de la Agrupaci�n de Hermandades y Cofrad�as de Montoro.\n");
				i.putExtra("Titulo", "Iglesia de Santiago");
				startActivity(i);
			}
		} else {
			if ("m0".equals(marker.getId())) {

				i.putExtra(
						"detalle",
						"Comenzamos en la Barriada del Retamar donde tras la Guerra Civil se construyeron algunas casas de car�cter social como es el caso de la Barriada de la Paz.");
				i.putExtra("Titulo", "Barriada del Retamar");
				startActivity(i);
			} else if ("m1".equals(marker.getId())) {

				i.putExtra(
						"detalle",
						"Parece ser que un cami�n cargado de moluscos volc� en las inmediaciones de la casa. A ra�z de ah� D. Paco del R�o decidi� reciclar el cargamento decorando su casa concha por concha. Tanto fue su af�n de engalanar su casa, que muchas de las piezas se las mandaron de distintos puntos costeros. La casa dispone de tres patios los cuales estan totalmente cubiertos de mosaicos de conchas.  Desde el �ltimo patio hay unas vistas muy atractivas del r�o Guadalquivir a su paso por Montoro.");
				i.putExtra("Titulo", "Casa de las conchas");
				startActivity(i);
			} else if ("m2".equals(marker.getId())) {

				i.putExtra(
						"detalle",
						"Una vez visitada la Casa de las Conchas nos dirigiremos hacia el Centro de la ciudad de Montoro tomando para ello las calles Mar�n y Manuel Criado Hoyo, a trav�s de la cual llegamos a la Plaza de Espa�a, Corredera y Plaza del Charco donde, por haber sido las zonas m�s importantes durante el s. XX se alzan algunos edificios de car�cter modernista, destacando la casa n� 20 de la Plaza del Charco.");
				i.putExtra("Titulo", "Arquitectura Modernista");
				startActivity(i);
			} else {

				i.putExtra(
						"detalle",
						"Construida en 1951 sobre los restos de otras m�s antiguas. La primera corrida de importancia tuvo lugar el 7 de octubre de 1951, en la que tom� la alternativa Rafael Soria Lagartijo, de manos de Jos� Mar�a Martorell con �Calerito� de testigo, con el toro �Rodilla� del Duque de Pinohermoso. Reinaugurada en 1993 por Palomo Linares, El Soro y Chiquil�n, que lidiaron toros de El Toril.");
				i.putExtra("Titulo", "Plaza de toros");
				startActivity(i);
			}
		}

	}

	public void iniciamosLaEscucha() {
		LocationProvider low = locationManager.getProvider(locationManager
				.getBestProvider(MiLocationListener.crearCriteria(), false));

		if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			createGpsDisabledAlert();
			Log.d("", "GPS desactivado");
		} else {
			locationManager.requestLocationUpdates(low.getName(), 0, 0f,
					locationListener);
		}
	}

	// Alerta que avisa si el gps esta desactivado
	private void createGpsDisabledAlert() {

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Su GPS est� desactivado, �desea activarlo?")
				.setCancelable(false)
				.setPositiveButton("Activar GPS",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								showGpsOptions();
							}
						});
		builder.setNegativeButton("Cancelar",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});

		AlertDialog alert = builder.create();
		alert.show();

	}

	// Mostrar la opciones para activar GPS
	private void showGpsOptions() {
		Intent gpsOptionsIntent = new Intent(
				android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		startActivity(gpsOptionsIntent);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		super.onOptionsItemSelected(item);

		switch (item.getItemId()) {

		case R.id.infoTurist:

			Gson gson = new Gson();
			Intent i = new Intent(this, DetailsActivity.class);
			i.putExtra("detalle",
					gson.toJson(Utils.obtenerInfoOficinaTurismo()));
			startActivity(i);
			break;

		case android.R.id.home:
			Intent intent = new Intent(this, MainActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			break;
		}
		return true;

	}

	/**
	 * Vamos a detener la escucha de nuestro listener y todo tipo de
	 * actualizacion
	 */
	@Override
	protected void onStop() {
		super.onStop();
		locationManager.removeUpdates(locationListener);
	}

	/**
	 * Vamos a detener la escucha de nuestro listener y todo tipo de
	 * actualizacion
	 */
	@Override
	protected void onPause() {
		super.onPause();
		locationManager.removeUpdates(locationListener);
	}

	/**
	 * Volvemos a arrancar las actualizaciones
	 */
	@Override
	protected void onResume() {

		iniciamosLaEscucha();
		super.onResume();
	}

}
