# CHALLENGE 1 JAVA (CONVERSOR DE MONEDA)
## ALURA |ONE  G4
### Resumen  
En el presente repositorio se encuentra el codigo en java, asi como demas archivos necesarios. para generar un conversor de moneda con interfaz grafica, que funciona online(consumo de API) y offline.
___
### Descripcion.
La estructura del proyecto contempla la construcción de 5 clases cada una destinada a un comportamiento especifico como se describe a continuacion:
* **_class_ Principal**: Contiene el metodo **main**, se encarga de realizar accion al detectar cambio de estado tanto del boton **convertir**, como de los _radio botones_  **ONLINE** y **OFFLINE**, por medio de los _ActionListener_.
  * Al seleccionar entre ONLINE u OFFLINE se ajusta a _true_ o _false_ la variable ```on=false;```la cual sera usada seguidamente por la accion del _boton CONVERTIR_. seguido llama al metodo        limpiar de la clase _Ventana_ para dejar vacio el espacio donde el usuario ingresa el importe o cantidad a convertir. tambien se realiza el llamado al metodo _setListaEntrada()_ de la clase _Ventana_ para ajustar la manpulación de las listas desplegables de moneda ya que para el caso del modo ONLINE como moneda base solo es posible usar USD como tasa de cambio.
 ```  
v.radioBotonOFF.addActionListener(new ActionListener() {
	@Override
	public  void actionPerformed(ActionEvent e) {
		on=false;
		System.out.println("modo OFFLINE");
		v.limpiar();
		v.setListaEntrada(true);
}
});
```
  * Cuando damos click en el boton _CONVERTIR_ este identifica si hemos elegido _modoONLINE_ o _modo OFFLINE_ y en concordancia se dispone a realizar el llamado a la clase _CambioONLINE_ o _CambioOFFLINE_ segun corresponda enviando los valores necesarios para la operación de conversión por medio del metodo ```CambioON.convertir(datoIn,listIn,listOut);```. cuyos datos son obtenidos de la clase _Ventana_ que es la encargada de capturar los datos ingresados por el usuario siendo ```datoIn```el importe, ```listIn```la tasa de cambio base seleccionada y ```listOut```la tasa de cambio a la cual se desea saber el equivalente.
```
@Override
public  void actionPerformed(ActionEvent e) {
	if(!on) {
		CambioOFFLINE CambioOFF = new CambioOFFLINE();
	if(v.getCajaIn().isEmpty()) {
		v.limpiar();
		JOptionPane.showMessageDialog(null, "ingrese 	valor a convertir");
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
```

* **_class_  Ventana:** Esta clase contiene todo el visual de la aplicacion, tambien es la encargada de capturar y mostrar los datos correspondientes a la conversion.
  * Con el metodo constructor ```public Ventana()```ajustamos el tamaño de la ventana, le agregamos titulo, colocamos icono, ajustamos la posicion donde aparecera nuestra ventana, llamamos al metodo ```iniciarComponentes()``` y por ultimo especificamos que detenga la ejecucion del programa al cerrar la ventana con ```setDefaultCloseOperation(EXIT_ON_CLOSE);```.
  * el metodo ```private  void iniciarComponentes()```basicamente llama al conjunto de motodos que conforman esta clase.
  * Metodo ```private  void paneles()``` genera el panel base para la colocacion de los demas componentes.
  * Metodo ```private  void etiquetas()``` genera dos etiquetas de texto para el titulo y la identificacion de los campos _DE:  A:_
  * Metodo ```private  void cajaTexto()```muestra dos campos de texto en los que el usuario ingresara el importe o monto a convertir y la salida que genera el conversor.
  * En el metodo ```private  void lista()```se generan igualmente dos lstas desplegables en las cuales el usuario podra elegir el tipo de moneda (base y a convertir).
  * El metodo ```public  void radioBoton()```muestra selectores _ONLINE_, _OFFLINE_y se realiza una agrupación con el fin de que solo sea posible elegir una opción.
  *   Metodo ```private  void botones()```Genera el boton CONVERTIR, encargado de dar inicio a la operacion.
  * El metodo ```public  void limpiar()```como su nombre lo indica permite limpiar los campos de entrada y salida de datos.

* **_class_  Cambio:** Es una clase de tipo abstracta con la finalidad de que las subclases requieran implementar el metodo
correspondiente a la conversion.
```
public  abstract  class Cambio {

public  abstract  void convertir(String importe, String listIn,String listOut);

}
```
* **_class_  CambioONLINE:** Esta clase extiende de la clase Cambio por ende debe implementar el metodo _convertir_,  tambien se encarga de hacer la consulta a la API de [OpenExchangeRates](https://openexchangerates.org/) para la obtencion de las tasas de cambio actuales.
  1. Como parametros del metodo ```public  void convertir(String importe, String listIn,String listOut)```obtenemos los valores ingresados por el usuario.
  2. Realizamos un _try, catch_ para capturar la _exception_ de tipo ```NumberFormatException```generada cuando el usuario ingresar texto ya que solo se desea un valor numerico para efectuar la operacion matematica.
```
try {
	//p es un objeto de la clase Principal
	p.mostrar=true;
	System.out.println("importe: "+importe);
	imp=Double.parseDouble(importe);
}catch(NumberFormatException nfe){
	p.mostrar=false;
	System.out.println("valor "+importe+" en formato no 	deseado");
	//v es un objeto de la clase Ventana
	v.limpiar();
	JOptionPane.showMessageDialog(null ,"El valor ("+importe+") tiene un formato no deseado");
}
```
  3. Generamos otro _try, catch_ para capturar los posibles errores generados por la conexion a la API este de tipo mas amplio ```Èxception```
  4. En el anterior _try_ realizamos:
      1. La construccion de la URL con los parametros adecuados de consulta. 
      
     2. Establecer la conexion HTTP. 
     3. Leer respuesta de la API.
     4. Debido a que la API nos entrega los datos en formato Json debemos adaptarlos a nuestro lenguaje en este caso _java_, para utilizarlos adecuadamente.
     

```
String urlStr = "https://openexchangerates.org/api/latest.json?app_id=[aqui el id unico generado por la API]";
```
```
URL url = new URL(urlStr);
HttpURLConnection conn = (HttpURLConnection) url.openConnection();
conn.setRequestMethod("GET");
conn.connect();
```

```
BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
response.append(inputLine);
}
in.close();
```
```
JSONObject jsonResponse = new JSONObject(response.toString());
System.out.println(jsonResponse);
double  tasaDeCambio = jsonResponse.getJSONObject("rates").getDouble(monedaDestino);
```
     
   5.  Realizar operacion de conversion. 

* **_class_  CambioOFFLINE:** Del mismo modo que la clase _CambioONLINE_ esta tambien hereda de la clase _Cambio_. con la diferencia que esta no requere de una conexion a internet ya que las constantes de _tasa de cambio_ se encuentra almacenadas en el mismo codigo, con la desventaja que pueden no coincidir en su totalidad con las tasas actuales, pero puede darnos una idea de cuanto seria el valor aproximado de la moneda. otro aspecto a favor es que en este caso el usuario si podra elegir otras monedas base. 
  * El codigo tiene una estructura similar si no que en lugar de realizar toda la configuracion para el llamado de la API, este asigna en un _map_ las constantes de cambio de la siguente forma:
```
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
```
 
  * para obtener el valor almacenado en el _map_ (_clave  - valor_), asignamos en la variable _clave_ los valores elegidos de las listas ```clave =monedaOrigen+"-"+monedaDestino;```luego en otra variable denominada _tasaCambio_ guardamos el _valor _ de la siguiente forma: ```tasaCambio=tasaDeCambio.get(clave);```por ultimo mediante el uso de un if comparamos el tipo de cambio elegido por el usuario con el almacenado en el map y si coincide realiza la operacion correcta.



---
### Resultados.
* Vista inicial  

![conversor](https://github.com/Carlos-Forero/challenge_1_java/blob/main/img/conversor.png)  

* Insertando valor a convertir en modo OFFLINE(5 Dolares a Pesos Colombianos.)  

![modo OFFLINE](https://github.com/Carlos-Forero/challenge_1_java/blob/main/img/conversor_offline.png)

* Insertando valor a convertir en modo ONLINE.

![modo OFFLINE](https://github.com/Carlos-Forero/challenge_1_java/blob/main/img/conversor_online.png)

* Ingresando texto:

![formato erroneo](https://github.com/Carlos-Forero/challenge_1_java/blob/main/img/formato_erroneo.png)

* error en ID de la API

![error id](https://github.com/Carlos-Forero/challenge_1_java/blob/main/img/error_appID.png)

* Dejando el campo vacio y dando click en CONVERTIR  

![campo vacio](https://github.com/Carlos-Forero/challenge_1_java/blob/main/img/campo_vacio.png)
