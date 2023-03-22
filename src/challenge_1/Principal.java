package challenge_1;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Principal {
	
	private static String datoIn;
	private static String listIn;
	private static String listOut;
	private static boolean on =false;
	public static boolean mostrar=true;
	
	public static void main(String[] args) {
		
		Ventana v = new Ventana();
		
		
		v.setVisible(true);
		
		v.radioBotonON.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	on=true;
		        System.out.println("modo ONLINE");
		        v.limpiar();
		        v.setListaEntrada(false);
		    }
		});

		v.radioBotonOFF.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	on=false;
		        System.out.println("modo OFFLINE");
		        v.limpiar();
		        v.setListaEntrada(true);
		    }
		});
		
		  v.boton1.addActionListener(new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	
	            	if(!on) {
	            		CambioOFFLINE CambioOFF = new CambioOFFLINE();
	            	if(v.getCajaIn().isEmpty()) {
	            		
	           		  v.limpiar(); 
	           		  JOptionPane.showMessageDialog(null, "ingrese valor a convertir");
	           		  }else {
	           			  datoIn=v.getCajaIn();
	           		  
	           			  listIn=v.getListaEntrada();
	           			  listOut=v.getListaSalida();
	           		  
	           			  CambioOFF.convertir(datoIn,listIn,listOut);
	           			  if(mostrar) {
	           			  v.setCajaOut(CambioOFF.resultado);
	           			  }
	           			  else {
	           				  v.limpiar();
	           			  }
	           		  
	            }
	            	}else {
	            		CambioONLINE CambioON = new CambioONLINE();
	            		
	            		if(v.getCajaIn().isEmpty()) {
	  	           		  v.limpiar(); 
	  	           		  JOptionPane.showMessageDialog(null, "ingrese valor a convertir");
	  	           		  }else {
	  	           			  System.out.println("online");
	  	           			  datoIn=v.getCajaIn();
	  	           		  
	  	           			  listIn=v.getListaEntrada();
	  	           			  listOut=v.getListaSalida();
	  	           		  
	  	           			  CambioON.convertir(datoIn,listIn,listOut);
	  	           			if(mostrar) {
	  	           			  v.setCajaOut(CambioON.resultado);
	  	           			  }
	  	           			  else {
	  	           				  v.limpiar();
	  	           			  }
	  	           			  
	  	           		  }
	            	}
	        }
		  });
		
	}
	
}
