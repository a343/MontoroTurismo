package org.turistmontoro.adaptadores;

import org.turistmontoro.monumentos.InfoArtesaniaFragment;
import org.turistmontoro.monumentos.InfoMonumentosFragment;
import org.turistmontoro.monumentos.InfoMuseosFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentAdapterTurist extends FragmentPagerAdapter {

	private String[] titles = { "Artesania", "Museos", "Monumentos" };

	public FragmentAdapterTurist(FragmentManager fm) {

		super(fm);

	}

	public CharSequence getPageTitle(int position) {

		return titles[position];

	}

	@Override
	public Fragment getItem(int position) {

		Fragment fragment = null;
		if (position == 0) {
			fragment = new InfoArtesaniaFragment();
		} else if (position == 1) {
			fragment = new InfoMuseosFragment();
		} else {
			fragment = new InfoMonumentosFragment();
		}
		return fragment;

	}

	@Override
	public int getCount() {

		return titles.length;
	}

}
