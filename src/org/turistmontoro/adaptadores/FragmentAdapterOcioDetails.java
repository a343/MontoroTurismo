//package org.turistmontoro.adaptadores;
//
//import org.turistmontoro.genericDetails.CartaRestaurantesActivity;
//import org.turistmontoro.genericDetails.DetailsRestaurant;
//
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentPagerAdapter;
//
//public class FragmentAdapterOcioDetails extends FragmentPagerAdapter {
//
//	private String[] titles = { "Informacion", "Carta" };
//
//	public FragmentAdapterOcioDetails(FragmentManager fm) {
//
//		super(fm);
//
//	}
//
//	public CharSequence getPageTitle(int position) {
//
//		return titles[position];
//
//	}
//
//	@Override
//	public Fragment getItem(int position) {
//
//		Fragment fragment = null;
//		if (position == 0) {
//			fragment = new DetailsRestaurant();
//		} else {
//			fragment = new CartaRestaurantesActivity();
//		}
//		return fragment;
//
//	}
//
//	@Override
//	public int getCount() {
//
//		return titles.length;
//	}
//
//}
