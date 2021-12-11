package org.turistmontoro.ocio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

import org.turistmontoro.R;
import org.turistmontoro.adaptadores.Lista_adaptador;
import org.turistmontoro.datos.ListaEntrada;
import org.turistmontoro.datos.Oferta;
import org.turistmontoro.utils.AsynCall;
import org.turistmontoro.utils.Utils;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.provider.SyncStateContract.Constants;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.gson.Gson;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class OfferBares extends SherlockFragment {

	private ListView lista;
	ProgressDialog progress;
	ArrayList<Oferta> datos;
	private String METHOD_NAME = "";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		datos = new ArrayList<Oferta>();

		if (Utils.hasInternet(getActivity())) {
			progress = ProgressDialog.show(this.getActivity(),
					"Cargando Informacion", "Cargando...", true);
			Gson gson = new Gson();
			
			Bundle bundle = this.getActivity().getIntent().getExtras();
			String auxDetalle = bundle.getString("detalle");
			ListaEntrada detalle = gson.fromJson(auxDetalle, ListaEntrada.class);
			createMethodName(detalle.getTitulo());
			obtenerInfo();
			añadirImagen();

		}

		if (datos.isEmpty()) {
			Toast.makeText(getSherlockActivity(),
					"No hay ofertas en este momento", Toast.LENGTH_SHORT).show();
		}

	}

	private void createMethodName(String titulo) {
		// TODO Auto-generated method stub
		if("Bar El Gordo".equals(titulo)){
			METHOD_NAME = "getOfertaElGordo";
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,

	Bundle savedInstanceState) {
		View myView = inflater.inflate(R.layout.fragment_list_offer, null);

		// Admob
		AdView adView = (AdView) myView.findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);

		lista = (ListView) myView.findViewById(R.id.ListView_listado_detail);
		lista.setAdapter(new Lista_adaptador(this.getActivity(),
				R.layout.list_item_offer, datos) {
			@Override
			public void onEntrada(Object entrada, View view) {
				Log.i("OfertaBares", "seteando informacions en la lista....");
				if (entrada != null) {
					TextView titulo = (TextView) view
							.findViewById(R.id.titulo);
					if (titulo != null)
						titulo.setText(((Oferta) entrada)
								.getTitulo());

					TextView fechaFin = (TextView) view
							.findViewById(R.id.fechaFin);
					if (fechaFin != null)
						fechaFin.setText("Fecha de fin : " +((Oferta) entrada)
								.getFechaFin());

					ImageView imagen = (ImageView) view
							.findViewById(R.id.imageView_imagen);

					if (imagen != null)
						imagen.setImageResource(((Oferta) entrada)
								.getIdImagen());

					TextView descripcion = (TextView) view
							.findViewById(R.id.descripcion);
					if (descripcion != null)
						descripcion.setText(((Oferta) entrada)
								.getDescripcion());
				}
			}

		});

		return myView;
	}

	private void obtenerInfo() {

		AsynCall task = new AsynCall(progress, METHOD_NAME);
		// Call execute
		try {
			datos = task.execute().get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void añadirImagen() {

		for (Iterator<Oferta> it = datos.iterator(); it.hasNext();) {
			Oferta auxiliar = (Oferta) it.next();

			auxiliar.setIdImagen(R.drawable.oferta1);

		}
	}

}
