package org.turistmontoro.adaptadores;

import java.util.ArrayList;

import org.turistmontoro.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Adaptador de ListView universal
 */
public abstract class Lista_adaptador extends BaseAdapter {

	private ArrayList<?> entradas;
	private int R_layout_IdView;
	private Context contexto;

	public Lista_adaptador(Context contexto, int R_layout_IdView,
			ArrayList<?> entradas) {
		super();
		this.contexto = contexto;
		this.entradas = entradas;
		this.R_layout_IdView = R_layout_IdView;
	}

	@Override
	public View getView(int posicion, View view, ViewGroup pariente) {
		if (view == null) {
			LayoutInflater vi = (LayoutInflater) contexto
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = vi.inflate(R_layout_IdView, null);
		}

		if (!((posicion % 2) == 1)) {
			view.setBackgroundResource(R.drawable.lista_pulsada_style_row_par);

		} else if ((posicion % 2) == 1) {
			view.setBackgroundResource(R.drawable.lista_pulsada_style_row_impar);
		}

		onEntrada(entradas.get(posicion), view);
		return view;
	}

	@Override
	public int getCount() {
		return entradas.size();
	}

	@Override
	public Object getItem(int posicion) {
		return entradas.get(posicion);
	}

	@Override
	public long getItemId(int posicion) {
		return posicion;
	}

	/**
	 * Devuelve cada una de las entradas con cada una de las vistas a la que
	 * debe de ser asociada
	 * 
	 * @param entrada
	 *            La entrada que será la asociada a la view. La entrada es del
	 *            tipo del paquete/handler
	 * @param view
	 *            View particular que contendrá los datos del paquete/handler
	 */
	public abstract void onEntrada(Object entrada, View view);

}
