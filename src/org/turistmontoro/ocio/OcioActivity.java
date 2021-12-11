package org.turistmontoro.ocio;

import org.turistmontoro.R;
import org.turistmontoro.adaptadores.FragmentAdapterOcio;
import org.turistmontoro.genericDetails.DetailsActivity;
import org.turistmontoro.utils.Utils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.google.gson.Gson;
import com.viewpagerindicator.TitlePageIndicator;

public class OcioActivity extends SherlockFragmentActivity {

	private FragmentAdapterOcio mAdapter;

	private ViewPager mPager;

	private TitlePageIndicator mIndicator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.view_pager);

		mAdapter = new FragmentAdapterOcio(getSupportFragmentManager());

		mPager = (ViewPager) findViewById(R.id.pager);

		mPager.setAdapter(mAdapter);

		mIndicator = (TitlePageIndicator) findViewById(R.id.indicator);
		mIndicator.setViewPager(mPager);

		Bundle bundle = getIntent().getExtras();
		String titulo = bundle.getString("titulo");
		// Ponemos titulo al action bar
		ActionBar ab = getSupportActionBar();
		ab.setTitle(titulo);
		ab.setIcon(R.drawable.ic_escudo_montoro);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		super.onOptionsItemSelected(item);

		Gson gson = new Gson();
		Intent i = new Intent(this, DetailsActivity.class);
		i.putExtra("detalle", gson.toJson(Utils.obtenerInfoOficinaTurismo()));
		startActivity(i);

		return true;

	}
}
