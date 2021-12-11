package org.turistmontoro.utils;

import java.util.ArrayList;

import org.turistmontoro.datos.ListaEntrada;
import org.turistmontoro.datos.Oferta;

import android.app.ProgressDialog;
import android.os.AsyncTask;

public class AsynCall extends
		AsyncTask<ProgressDialog, Void, ArrayList<Oferta>> {

	private ArrayList<Oferta> resultado;
	private ProgressDialog progress;
	private String nameMethod;

	public AsynCall(ProgressDialog pg, String nameMethod) {
		resultado = new ArrayList<Oferta>();
		progress = pg;
		this.nameMethod = nameMethod;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onProgressUpdate(Void... values) {
	}

	@Override
	protected void onPostExecute(ArrayList<Oferta> result) {
		// Make ProgressBar invisible
		try {
			progress.dismiss();
		} catch (Exception e) {

		}

	}

	@Override
	protected ArrayList<Oferta> doInBackground(ProgressDialog... params){
		// TODO Auto-generated method stub
			try {
				resultado = WebService.invokeWS(nameMethod);
			} catch (Exception e) {
				// TODO Auto-generated catch block
//				return null;
			}

		return resultado;
	}

}
