package challenge_1;
import java.util.Map;

import javax.swing.JOptionPane;

import java.util.HashMap;

public class CambioOFFLINE extends Cambio {
	static double resultado;
	
	
	public void convertir(String importe, String listIn,String listOut) {
		Ventana v = new Ventana();
		Principal p=new Principal();
		
	String clave,monedaOrigen,monedaDestino;
	double imp,tasaCambio;
	Object seleccionMonedaIn =new Principal();
	Object seleccionMonedaOut =new Principal();
	CambioOFFLINE conv=new CambioOFFLINE();
	
	
	Map<String, Double> tasaDeCambio = new HashMap<>();
	//base dolar	
	tasaDeCambio.put("USD-USD", 1.0);
	tasaDeCambio.put("USD-COP", 4786.7);
	tasaDeCambio.put("USD-EUR", 0.9417);
	tasaDeCambio.put("USD-GBP", 0.8333);
	tasaDeCambio.put("USD-JPY", 136.03);
	tasaDeCambio.put("USD-CHF", 0.9381);
	tasaDeCambio.put("USD-CAD", 1.3617);
	tasaDeCambio.put("USD-AUD", 1.4806);
	tasaDeCambio.put("USD-NZD", 1.6086);
	//base peso colombiano
	tasaDeCambio.put("COP-USD", 0.0002);
	tasaDeCambio.put("COP-COP", 1.0);
	tasaDeCambio.put("COP-EUR", 0.00019);
	tasaDeCambio.put("COP-GBP", 0.00017);
	tasaDeCambio.put("COP-JPY", 0.02742);
	tasaDeCambio.put("COP-CHF", 0.00019);
	tasaDeCambio.put("COP-CAD", 0.0002);
	tasaDeCambio.put("COP-AUD", 0.0003);
	tasaDeCambio.put("COP-NZD", 0.0003);
	//base Euro
	tasaDeCambio.put("EUR-USD", 1.0619);
	tasaDeCambio.put("EUR-COP", 5263.15);
	tasaDeCambio.put("EUR-EUR", 1.0);
	tasaDeCambio.put("EUR-GBP", 0.8849);
	tasaDeCambio.put("EUR-JPY", 144.44);
	tasaDeCambio.put("EUR-CHF", 0.9961);
	tasaDeCambio.put("EUR-CAD", 1.4459);
	tasaDeCambio.put("EUR-AUD", 1.5722);
	tasaDeCambio.put("EUR-NZD", 1.7085);
	//base libra esterlina
	tasaDeCambio.put("GBP-USD", 1.2001);
	tasaDeCambio.put("GBP-COP", 5882.35);
	tasaDeCambio.put("GBP-EUR", 1.1301);
	tasaDeCambio.put("GBP-GBP", 1.0);
	tasaDeCambio.put("GBP-JPY", 163.22);
	tasaDeCambio.put("GBP-CHF", 1.1255);
	tasaDeCambio.put("GBP-CAD", 1.6342);
	tasaDeCambio.put("GBP-AUD", 1.7767);
	tasaDeCambio.put("GBP-NZD", 1.9305);
	//base yen japones
	tasaDeCambio.put("JPY-USD", 0.0075);
	tasaDeCambio.put("JPY-COP", 36.5847);
	tasaDeCambio.put("JPY-EUR", 0.0071);
	tasaDeCambio.put("JPY-GBP", 0.00612);
	tasaDeCambio.put("JPY-JPY", 1.0);
	tasaDeCambio.put("JPY-CHF", 0.00697);
	tasaDeCambio.put("JPY-CAD", 0.01001);
	tasaDeCambio.put("JPY-AUD", 0.01089);
	tasaDeCambio.put("JPY-NZD", 0.0122);
	//base franco suizo
	tasaDeCambio.put("CHF-USD", 1.0661);
	tasaDeCambio.put("CHF-COP", 5263.15);
	tasaDeCambio.put("CHF-EUR", 1.004);
	tasaDeCambio.put("CHF-GBP", 0.8885);
	tasaDeCambio.put("CHF-JPY", 145.02);
	tasaDeCambio.put("CHF-CHF", 1.0);
	tasaDeCambio.put("CHF-CAD", 1.4518);
	tasaDeCambio.put("CHF-AUD", 1.5784);
	tasaDeCambio.put("CHF-NZD", 1.7149);
	//vase dolar canadiense
	tasaDeCambio.put("CAD-USD", 0.7344);
	tasaDeCambio.put("CAD-COP", 5000.0);
	tasaDeCambio.put("CAD-EUR", 0.6915);
	tasaDeCambio.put("CAD-GBP", 0.612);
	tasaDeCambio.put("CAD-JPY", 99.9);
	tasaDeCambio.put("CAD-CHF", 0.6888);
	tasaDeCambio.put("CAD-CAD", 1.0);
	tasaDeCambio.put("CAD-AUD", 1.0873);
	tasaDeCambio.put("CAD-NZD", 1.1812);
	//base dolar australiano
	tasaDeCambio.put("AUD-USD", 0.6754);
	tasaDeCambio.put("AUD-COP", 3333.3);
	tasaDeCambio.put("AUD-EUR", 0.6361);
	tasaDeCambio.put("AUD-GBP", 0.563);
	tasaDeCambio.put("AUD-JPY", 91.87);
	tasaDeCambio.put("AUD-CHF", 0.6336);
	tasaDeCambio.put("AUD-CAD", 0.9197);
	tasaDeCambio.put("AUD-AUD", 1.0);
	tasaDeCambio.put("AUD-NZD", 1.0867);
	//dolar neozelandes
	tasaDeCambio.put("NZD-USD", 0.6216);
	tasaDeCambio.put("NZD-COP", 3333.3);
	tasaDeCambio.put("NZD-EUR", 0.5854);
	tasaDeCambio.put("NZD-GBP", 0.518);
	tasaDeCambio.put("NZD-JPY", 84.54);
	tasaDeCambio.put("NZD-CHF", 0.583);
	tasaDeCambio.put("NZD-CAD", 0.8463);
	tasaDeCambio.put("NZD-AUD", 0.9204);
	tasaDeCambio.put("NZD-NZD", 1.0);
	
	
	monedaOrigen=listIn;
	monedaDestino=listOut;
	clave =monedaOrigen+"-"+monedaDestino;
	tasaCambio=tasaDeCambio.get(clave);
	
	if(tasaDeCambio.containsKey(clave)) {
		boolean operar=true;		
		imp=0;
		try {
			p.mostrar=true;
			System.out.println("importe: "+importe);
			imp=Double.parseDouble(importe);
		
		}catch(NumberFormatException nfe){
			p.mostrar =false;
			//v.limpiar();
			JOptionPane.showMessageDialog(null ,"El valor ("+importe+") tiene un formato no deseado");
			
		}
		
		resultado=imp*tasaCambio;
		//v.setCajaOut(resultado); 
		
	}
	}
	
	
	
	
	
}
