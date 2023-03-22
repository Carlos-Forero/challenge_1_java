package challenge_1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JOptionPane;

import org.json.JSONObject;

public class CambioONLINE extends Cambio{
	static double resultado;
	 public void convertir(String importe, String listIn,String listOut) {
		 Ventana v = new Ventana();
		 Principal p=new Principal();
		 CambioONLINE conv=new CambioONLINE();
		 	String monedaOrigen = listIn; 
		    String monedaDestino = listOut; 
		    double imp=0,tasaCambio;

		    try {
	    		p.mostrar=true;
				System.out.println("importe: "+importe);
				imp=Double.parseDouble(importe);
			
			}catch(NumberFormatException nfe){
				p.mostrar=false;
				System.out.println("valor "+importe+" en formato no deseado");
				v.limpiar();
				JOptionPane.showMessageDialog(null ,"El valor ("+importe+") tiene un formato no deseado");
				
			}
		    if(p.mostrar) {
		    try {
		    	
		    	
		      // construir la URL de la API con los parámetros necesarios
		      String urlStr = "https://openexchangerates.org/api/latest.json?app_id=b326196fea8d401abd785670f7b8c37e";
		  		      
		      // establecer la conexión HTTP
		      URL url = new URL(urlStr);
		      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		      conn.setRequestMethod("GET");
		      conn.connect();

		      // leer la respuesta de la API
		      BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		      String inputLine;
		      StringBuffer response = new StringBuffer();
		      while ((inputLine = in.readLine()) != null) {
		        response.append(inputLine);
		      }
		      in.close();
		     
		      // analizar los datos de respuesta para extraer la tasa de cambio
		      JSONObject jsonResponse = new JSONObject(response.toString());
		      System.out.println(jsonResponse);
		      double tasaDeCambio = jsonResponse.getJSONObject("rates").getDouble(monedaDestino);

		      resultado=imp*tasaDeCambio;

		    } catch (Exception e) {
		      e.printStackTrace();
		      JOptionPane.showMessageDialog(null, "Se ha producido un error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		    }
		    }
		    
	 }
	 
}
