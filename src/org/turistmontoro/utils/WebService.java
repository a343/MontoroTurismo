package org.turistmontoro.utils;

import java.lang.reflect.Type;
import java.util.ArrayList;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.turistmontoro.datos.ListaEntrada;
import org.turistmontoro.datos.Oferta;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class WebService {

	private static final String NAMESPACE = "http://turistmontoro.org/";
	private static String URL = "http://192.168.1.128:8080/MontoroTWSApp/MontoroTWebService";
	private static String SOAP_ACTION = "http://turistmontoro.org/";

	// Declaracion de variables para consuymir el web service
	private static SoapObject request = null;
	private static SoapSerializationEnvelope envelope = null;
	private static SoapPrimitive resultsRequestSOAP = null;

	// Declaracion de variables para serealziar y deserealizar
	// objetos y cadenas JSON
	static Gson gson;

	public static ArrayList<Oferta> invokeWS(String nameMethod)
			throws Exception {
		ArrayList<Oferta> resultado = new ArrayList<Oferta>();
		try {
			gson = new Gson();
			// Se crea un objeto SoapObject para poder realizar la peticion
			// para consumir el ws SOAP. El constructor recibe
			// el namespace. Por lo regular el namespace es el dominio
			// donde se encuentra el web service
			request = new SoapObject(NAMESPACE, nameMethod);
			
			// Se crea un objeto SoapSerializationEnvelope para serealizar la
			// peticion SOAP y permitir viajar el mensaje por la nube
			// el constructor recibe la version de SOAP
			envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

			// Se envuelve la peticion soap
			envelope.setOutputSoapObject(request);
			envelope.implicitTypes = true;
			// Objeto que representa el modelo de transporte
			// Recibe la URL del ws
			HttpTransportSE transporte = new HttpTransportSE(URL);

			// Hace la llamada al ws
			transporte.call(SOAP_ACTION, envelope);

			// Se crea un objeto SoapPrimitive y se obtiene la respuesta
			// de la peticion
			resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();

			// Almacenamos el resultado en un String ya que lo que represa
			// el ws es una cadena json, representando una lista AndroidOS
			// de objetos del tipo
			String strJSON = resultsRequestSOAP.toString();
			Type lstT = new TypeToken<ArrayList<Oferta>>() {
			}.getType();

			resultado = gson.fromJson(strJSON, lstT);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception();

		}

		return resultado;
	}
}
