package org.turistmontoro.excursiones;

import org.turistmontoro.R;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

public class DetailPuntosInteres extends SherlockActivity {

	TextView descripcion;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_interest_point);
		Bundle bundle = getIntent().getExtras();
		String detalle = bundle.getString("detalle");
		String titulo = bundle.getString("Titulo");
		descripcion = (TextView) findViewById(R.id.descripcion);
		descripcion.setText(detalle);
		descripcion.setMovementMethod(new ScrollingMovementMethod());
		// //Ponemos titulo al action bar
		setTitle(titulo);
		
		

	}

	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		super.onOptionsItemSelected(item);

		return true;

	}

}
