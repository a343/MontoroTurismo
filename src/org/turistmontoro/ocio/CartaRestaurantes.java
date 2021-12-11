package org.turistmontoro.ocio;

import java.util.ArrayList;

import org.turistmontoro.R;
import org.turistmontoro.adaptadores.Lista_adaptador;
import org.turistmontoro.datos.ListaCarta;
import org.turistmontoro.datos.ListaEntrada;
import org.turistmontoro.genericDetails.DetailsActivity;
import org.turistmontoro.utils.Utils;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.gson.Gson;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class CartaRestaurantes extends SherlockFragment {

	private ListView lista;
	ProgressDialog progress;
	ArrayList<ListaCarta> datos;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		datos = new ArrayList<ListaCarta>();

		rellenarInfo(datos);

		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		View myView = inflater.inflate(R.layout.carta_list, null);
		// Admob
		AdView adView = (AdView) myView.findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);
		
		lista = (ListView) myView.findViewById(R.id.ListView_listado_detail);
		lista.setAdapter(new Lista_adaptador(getActivity(),
				R.layout.list_item_carta, datos) {
			@Override
			public void onEntrada(Object entrada, View view) {
				if (entrada != null) {
					TextView texto_superior_entrada = (TextView) view
							.findViewById(R.id.textView_superior);
					if (texto_superior_entrada != null)
						texto_superior_entrada.setText(((ListaCarta) entrada)
								.getDescripcion());

					ImageView imagen_entrada = (ImageView) view
							.findViewById(R.id.imageView_imagen);
					if (imagen_entrada != null)
						imagen_entrada.setImageResource(((ListaCarta) entrada)
								.getIdImagen());
				}

				ImageView imagen_entrada2 = (ImageView) view
						.findViewById(R.id.imageView_imagen);
				if (imagen_entrada2 != null)
					imagen_entrada2.setImageResource(((ListaCarta) entrada)
							.getIdImagen2());
			}

		});
		return myView;
	}

	private void rellenarInfo(ArrayList<ListaCarta> datos) {

		datos.add(new ListaCarta(
				R.drawable.pizza,
				R.drawable.pizza2,
				"Pruebe nuestra insuperable pizza montoreña hecha a mano, y con los mejores ingredientes locales. Precio: 7.80 €"));

		datos.add(new ListaCarta(
				R.drawable.carne_asada,
				R.drawable.carne_asada2,
				"Pruebe nuestra insuperable carne del Alto Guadalquivir, con la mejor guarnicion al mejor precio. Precio: 9.80 €"));
		datos.add(new ListaCarta(
				R.drawable.pizza,
				R.drawable.pizza2,
				"Pruebe nuestra insuperable pizza montoreña hecha a mano, y con los mejores ingredientes locales. Precio: 7.80 €"));

		datos.add(new ListaCarta(
				R.drawable.carne_asada,
				R.drawable.carne_asada2,
				"Pruebe nuestra insuperable carne del Alto Guadalquivir, con la mejor guarnicion al mejor precio. Precio: 9.80 €"));
		datos.add(new ListaCarta(
				R.drawable.pizza,
				R.drawable.pizza2,
				"Pruebe nuestra insuperable pizza montoreña hecha a mano, y con los mejores ingredientes locales. Precio: 7.80 €"));

		datos.add(new ListaCarta(
				R.drawable.carne_asada,
				R.drawable.carne_asada2,
				"Pruebe nuestra insuperable carne del Alto Guadalquivir, con la mejor guarnicion al mejor precio. Precio: 9.80 €"));
		datos.add(new ListaCarta(
				R.drawable.pizza,
				R.drawable.pizza2,
				"Pruebe nuestra insuperable pizza montoreña hecha a mano, y con los mejores ingredientes locales. Precio: 7.80 €"));

		datos.add(new ListaCarta(
				R.drawable.carne_asada,
				R.drawable.carne_asada2,
				"Pruebe nuestra insuperable carne del Alto Guadalquivir, con la mejor guarnicion al mejor precio. Precio: 9.80 €"));
	}

}
