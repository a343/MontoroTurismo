package org.turistmontoro.sqlLite;

import java.util.ArrayList;

import org.turistmontoro.datos.ListaEntrada;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLite extends SQLiteOpenHelper {
	// Métodos de SQLiteOpenHelper
	public SQLite(Context context) {
		super(context, "montoro_turismo", null, 1);
	}

	// Se ejecuta la sentencia SQL de creación de la tabla
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE bares ("
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "nombre TEXT, descripcion TEXT, LDescripcion TEXT, telefono TEXT, web TEXT, direccion TEXT, Table_name TEXT)");
		db.execSQL("CREATE TABLE restaurantes ("
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "nombre TEXT, descripcion TEXT, LDescripcion TEXT, telefono TEXT, web TEXT, direccion TEXT, Table_name TEXT)");
		db.execSQL("CREATE TABLE museos ("
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "nombre TEXT, descripcion TEXT, LDescripcion TEXT, telefono TEXT, web TEXT, direccion TEXT, Table_name TEXT)");
		db.execSQL("CREATE TABLE monumentos ("
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "nombre TEXT, descripcion TEXT, LDescripcion TEXT, telefono TEXT, web TEXT, direccion TEXT, Table_name TEXT)");
		db.execSQL("CREATE TABLE artesania ("
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "nombre TEXT, descripcion TEXT, LDescripcion TEXT, telefono TEXT, web TEXT, direccion TEXT, Table_name TEXT)");
		db.execSQL("CREATE TABLE pubs ("
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "nombre TEXT, descripcion TEXT, LDescripcion TEXT, telefono TEXT, web TEXT, direccion TEXT, Table_name TEXT)");
		db.execSQL("CREATE TABLE excursiones ("
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "nombre TEXT, descripcion TEXT, LDescripcion TEXT, telefono TEXT, web TEXT, direccion TEXT, Table_name TEXT)");
		db.execSQL("CREATE TABLE hoteles ("
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "nombre TEXT, descripcion TEXT, LDescripcion TEXT, telefono TEXT, web TEXT, direccion TEXT, Table_name TEXT)");
		db.execSQL("CREATE TABLE casa_rural ("
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "nombre TEXT, descripcion TEXT, LDescripcion TEXT, telefono TEXT, web TEXT, direccion TEXT, Table_name TEXT)");
		
	}

	// Métodos de AlmacenPuntuaciones

	public void insertarBD(ListaEntrada datos) throws Exception {
		SQLiteDatabase db = getWritableDatabase();
		Log.i("", "insetando en la bbdd" + datos.getTitulo());

		try {
			db.execSQL("INSERT OR REPLACE INTO "
					+ datos.getTable_name()
					+ " ('id', 'Nombre', 'Descripcion', 'LDescripcion', 'Telefono', 'Web', 'Direccion', 'table_name') VALUES (' "
					+ datos.getId() + "' , '" + datos.getTitulo() + "' , '"
					+ datos.getDescripcion() + "' , '"
					+ datos.getDescripcionLarga() + "' , '"
					+ datos.getTelefono() + "' , '" + datos.getWeb() + "' , '"
					+ datos.getDireccion() + "', '" + datos.getTable_name()
					+ "')");
		} catch (Exception e) {
			throw new Exception();
		}
	}

	public ArrayList<ListaEntrada> getDatos(String table_name) {
		ArrayList<ListaEntrada> result = new ArrayList<ListaEntrada>();
		try {
			SQLiteDatabase db = getReadableDatabase();
			Cursor c = db.rawQuery("SELECT * FROM " + table_name, null);
			while (c.moveToNext()) {
				result.add(new ListaEntrada(c.getInt(0), c.getString(1), c
						.getString(2), c.getString(3), c.getString(4), c
						.getString(5), c.getString(6)));
			}
			c.close();
		} catch (Exception e) {
			return null;
		}

		return result;
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVecion, int newVersion) {
		// TODO Auto-generated method stub

	}
}
