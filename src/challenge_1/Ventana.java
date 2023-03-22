package challenge_1;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.*;


public class Ventana extends JFrame {
	
	private JPanel panel;
	private JLabel etiqueta;
	private JLabel etiqueta2;	  
	private JTextField cajaText1;
	private JTextField cajaText2;
	private JComboBox listaEntrada;
	private JComboBox listaSalida;
	private String [] monedas;
	private String datoIn,listIn, listOut;
	private String cajaIn;
	private String cajaOut;
	public JButton boton1;
	public JRadioButton radioBotonON;
	public JRadioButton radioBotonOFF;
	
	
	public Ventana() {
		setSize(400,400);
		setTitle("Conversor de moneda");
		
		Image icono = Toolkit.getDefaultToolkit().getImage(getClass().getResource("cambioMoneda.png"));
		setIconImage(icono);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(415,400));
		
		iniciarComponentes();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	private void iniciarComponentes() {
		paneles();
		etiquetas();
		cajaTexto();
		botones();
		lista();
		radioBoton();
		
	}
	private void paneles() {
		panel = new JPanel();
		panel.setBackground(Color.WHITE );
		panel.setLayout(null);
		this.getContentPane().add(panel);
	}
	private void etiquetas() {
		etiqueta =new JLabel();
		etiqueta2 =new JLabel();
		etiqueta.setOpaque(true);
		etiqueta2.setOpaque(true);
		etiqueta.setText("<html>CONVERSOR DE MONEDA<p align=\"center\">ALURA | ONE</p><html>");
		etiqueta.setBounds(50, 10, 300, 50 );
		etiqueta2.setText("De:                          A:");
		etiqueta2.setBounds(20, 70, 360, 20 );
		etiqueta.setHorizontalAlignment(SwingConstants.CENTER);
		etiqueta.setBackground(Color.white);
		etiqueta2.setBackground(Color.white);
		etiqueta.setFont(new Font("bankgothic lt bt",0,20));
		etiqueta2.setFont(new Font("bankgothic lt bt",0,15));
		panel.add(etiqueta);
		panel.add(etiqueta2);
	}
	
	private void cajaTexto() {
		cajaText1 = new JTextField();
		cajaText2 = new JTextField();
		cajaText1.setBounds(20,120,180,180);
		cajaText2.setBounds(200,120,180,180);
		cajaText1.setFont(new Font("proxy",0,30));
		cajaText2.setFont(new Font("proxy",0,30));
		panel.add(cajaText1);
		panel.add(cajaText2);

	}
	private void lista() {
		
		String [] monedas = {"USD","COP","EUR","GBP","JPY","CHF","CAD","AUD","NZD"};
		this.monedas =monedas;
		listaEntrada =new JComboBox(monedas);
		listaEntrada.setBounds(20, 100, 180, 20);
		panel.add(listaEntrada);
		
		listaSalida =new JComboBox(monedas);
		listaSalida.setBounds(200, 100, 180, 20);
		panel.add(listaSalida);
		
		
	}
	
	public void radioBoton() {
		radioBotonON = new JRadioButton("ONLINE",false);
		radioBotonON.setBounds(50,300 ,80 , 30);
		radioBotonON.setBackground(Color.white);
		panel.add(radioBotonON);
		radioBotonOFF = new JRadioButton("OFFLINE",true);
		radioBotonOFF.setBounds(270,300 ,80 , 30);
		radioBotonOFF.setBackground(Color.white);
		panel.add(radioBotonOFF);
		
		ButtonGroup grupoRadioBotones = new ButtonGroup();
		grupoRadioBotones.add(radioBotonON);
		grupoRadioBotones.add(radioBotonOFF);
		
	}
	private void botones() {
		boton1 =new JButton();
		
		boton1.setText("convertir");
		boton1.setBounds(145, 300,110, 30);
		panel.add(boton1);
		boton1.setFont(new Font("bankgothic lt bt",0,13));
		
	}
	public void setBoton1(JButton boton1) {
		this.boton1=boton1;
	}
	
	public String getListaEntrada() {
		return listIn=(String) listaEntrada.getSelectedItem();
	}
	public void setListaEntrada(boolean b) {
		//this.listaEntrada = listaEntrada;
		this.listaEntrada.setEnabled(b);
	}
	public String getListaSalida() {
		return listOut=(String)listaSalida.getSelectedItem();
	}
	public void setListaSalida(JComboBox listaSalida) {
		this.listaSalida = listaSalida;
	}
	
	public String getCajaIn() {
		cajaIn=cajaText1.getText();
		return cajaIn;
	}
	public void setCajaIn(String cajaIn) {
		this.cajaIn = cajaIn;
	}
	public String getCajaOut() {
		return cajaOut;
	}
	public void setCajaOut(double cajaOut) {

		this.cajaOut= String.format("%.4f",cajaOut);
		cajaText2.setText(this.cajaOut);
		System.out.println("el resultado en setCajaOut es: "+this.cajaOut);
	}
	public void limpiar() {
		System.out.println("limpiando");
		cajaText1.setText("");
		cajaText2.setText("");
	}
	
	
}

