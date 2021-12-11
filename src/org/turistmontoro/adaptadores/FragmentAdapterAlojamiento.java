package org.turistmontoro.adaptadores;

import org.turistmontoro.alojamiento.InfoCasaRuralFragment;
import org.turistmontoro.alojamiento.InfoHotelesFragment;
import org.turistmontoro.monumentos.InfoArtesaniaFragment;
import org.turistmontoro.monumentos.InfoMonumentosFragment;
import org.turistmontoro.monumentos.InfoMuseosFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentAdapterAlojamiento extends FragmentPagerAdapter {

	private String[] titles = { "Hotel", "Casa Rural" };

	public FragmentAdapterAlojamiento(FragmentManager fm) {

		super(fm);

	}

	public CharSequence getPageTitle(int position) {

		return titles[position];

	}

	@Override
	public Fragment getItem(int position) {

		Fragment fragment = null;
		if (position == 0) {
			fragment = new InfoHotelesFragment();
		} else {
			fragment = new InfoCasaRuralFragment();
		}
		return fragment;

	}

	@Override
	public int getCount() {

		return titles.length;
	}

}
