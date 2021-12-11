package org.turistmontoro.utils;

import org.turistmontoro.R;
import org.turistmontoro.adaptadores.CustomTabListener;
import org.turistmontoro.ocio.CartaRestaurantes;
import org.turistmontoro.ocio.DetailsBares;
import org.turistmontoro.ocio.OfferBares;

import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockFragmentActivity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import com.actionbarsherlock.app.ActionBar;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint("NewApi")
public class ManejadorBares extends SherlockFragmentActivity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true);

		// //Ponemos titulo al action bar y mostramos el bton home

		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setIcon(R.drawable.ic_escudo_montoro);

		/** Creating ANDROID Tab */
		Tab tab = actionBar
				.newTab()
				.setText("Detalles")
				.setTabListener(
						new CustomTabListener<DetailsBares>(this,
								"Detalles", DetailsBares.class));

		actionBar.addTab(tab);

		/** Creating APPLE Tab */
		tab = actionBar
				.newTab()
				.setText("Carta")
				.setTabListener(
						new CustomTabListener<CartaRestaurantes>(this,
								"Carta", CartaRestaurantes.class))
				.setIcon(R.drawable.menu_carta);
		actionBar.addTab(tab);
		
		/** Creating ANDROID Tab */
		 tab = actionBar
				.newTab()
				.setText("Ofertas")
				.setTabListener(
						new CustomTabListener<OfferBares>(this,
								"Ofertas", OfferBares.class));

		actionBar.addTab(tab);
	}
}
