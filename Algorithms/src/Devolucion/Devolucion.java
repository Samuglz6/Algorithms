package Devolucion;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Devolucion {
	
	private Estado[][] array;
	private int dinero;
	private ArrayList<Integer> monedas = new ArrayList<Integer>();
	private Hashtable<Integer, Estado> sol = new Hashtable<Integer, Estado>();
	private static Scanner sc = new Scanner(System.in);
	
	public Devolucion() throws InterruptedException
	{	
		inicio();
		
		System.out.println("Seleccione el algoritmo de busqueda que desea utilizar:");
		int option = -1;
		do {
			System.out.println("1.-Backwards.");
			System.out.println("2.-Forwards.");
			System.out.println("3.-Matricial.");
			System.out.print("-->");
			option = sc.nextInt();
		}while(option<1 || option>3);
		
		int[] iniciales = new int[monedas.size()];
		for(int i = 0; i < monedas.size(); i++) iniciales[i] = 0;
		Estado inicial = new Estado(dinero, iniciales);
		
		switch(option) {
			case 1:
				back();
				break;
			case 2:
				forwards();
				break;
			case 3:
				crearSol(matricial());
				break;
		}
		
	}
		
	private void matBack(Estado[][] array, int fila, int columna) {
		System.out.println("CONDICIONES :" + (columna < monedas.size()) + " " + (fila != 0));
		if(columna < (monedas.size() )) {
			if(fila != 0) {
				int nuevaColumna, nuevaFila;
				int[] usadas = array[fila][columna].getMonedas();		//obtenemos las monedas usadas hasta ahora
				double calculo = Math.floor(fila/monedas.get(columna)); //maximo de monedas que entran en ese numero
				usadas[columna] += (int)calculo;		  		 //modificar las monedas usadas
				Estado hijo = new Estado((int)(fila - calculo*monedas.get(columna)), usadas); //crear el hijo
			
				nuevaFila = (int)(fila - calculo*monedas.get(columna));
				System.out.println("NUEVONUM: " + nuevaFila);
				nuevaColumna = columna + 1;
				array[nuevaFila][nuevaColumna] = hijo;
				matBack(array, nuevaFila, nuevaColumna);
			}
		}
	}

	private void matForw(Estado[][] array) {
		
		array[array.length-1][0].setRestante(0);
		for (int columna = 0; columna < array[0].length-1; columna++) {
			
			for (int fila = 0; fila < array.length; fila++) {
				
				if(array[fila][columna].getRestante() > 0) {
					
					int nuevaColumna = columna+1;
					for(int i = 0; i < monedas.size(); i++) {
							//if(array[nuevoDinero][nuevaColumna] < array[fila][columna])
					}
				}
			}
		}	
	}
	
	private void back()
	{
		
		ArrayList<Estado> nodeList = new ArrayList<Estado>();
		int[] lista = new int[monedas.size()];
		for(int i : lista) {
			lista[i] = 0;
		}
		Estado initial  = new Estado(dinero, lista);
		nodeList.add(initial);
		
		backwards(initial, nodeList);
		
		
		/*int i;
		if(actual.getRestante() == 0 || !esPosible(actual.getRestante()))
		{
			actual.setMonedas(ceroMonedas());
			actual.setUsadas(calcularUsadas(actual.getMonedas()));
			sol.put(actual.getRestante(), actual);
		}
		else
		{
			for(i = 0; i < monedas.size(); i++)
			{
				if(actual.getRestante()-monedas.get(i) >= 0)
				{
					Estado nuevo = new Estado(actual.getRestante()-monedas.get(i), null);
					System.out.println(nuevo);
					back(nuevo);
					if(!sol.containsKey(actual.getRestante()))
					{
						int[] list = new ArrayList<Integer>(nuevo.getMonedas());
						list.set(i, list.get(i)+1);
						actual.setMonedas(list);
						actual.setUsadas(calcularUsadas(actual.getMonedas()));
						sol.put(actual.getRestante(), actual);
					}
					else
					{
						if(nuevo.getUsadas() < actual.getUsadas() || nuevo.getRestante() == 0)
						{
							ArrayList<Integer> list = new ArrayList<Integer>(sol.get(nuevo.getRestante()).getMonedas());
							list.set(i, list.get(i)+1);
							actual.setMonedas(list);
							actual.setUsadas(calcularUsadas(actual.getMonedas()));
							sol.put(actual.getRestante(), actual);
						}
					}
				}
			}
		}*/
	}
	
	private void backwards(Estado current, ArrayList<Estado> nodeList) {
		
		if(current.getRestante() < monedas.get(monedas.size()-1)) {
			System.out.println("FINIQUITAUN CON VALOR: " + current.getRestante());
			return;
			
		}else {
			
			int multiplicador;
			ArrayList<Estado> adjacents = new ArrayList<Estado>();
			
			for(int i = 0; i < monedas.size(); i ++) {
				multiplicador = 1;
				Estado foo = new Estado();
				int[] thing = current.getMonedas().clone();
				
				while(multiplicador*monedas.get(i)<current.getRestante()) {
					thing[i]++;
					foo.setRestante(current.getRestante() - (multiplicador*monedas.get(i)));
					foo.setMonedas(thing);
					multiplicador++;
					//System.out.println("CREANDO HIJO: " + foo.getRestante() + " CON LAS MONEDAS USADAS: " + foo.getMonedas()[0] + " " + foo.getMonedas()[1]);
					adjacents.add(foo);
					System.out.println("AÑADIENDO HJIJO: " + foo.getRestante() + " CON LAS MONEDAS USADAS: " + foo.getMonedas()[0] + " " + foo.getMonedas()[1]);
					
				};
				
			}
			
			for(int i = 0; i < adjacents.size(); i++) {
				System.out.println("ADYACENTE DE: " + current.getRestante() + " ES EL: " + adjacents.get(i).getRestante() + " CON LAS MONEDAS:" + adjacents.get(i).getMonedas()[0] + " " + adjacents.get(i).getMonedas()[1]);
			}
			
			
			for(Estado adjacent : adjacents) {
				
				//System.out.println("HIJO: " + adjacent.getRestante());
				
				if(nodeList.contains(adjacent)) {
					//System.out.println("NodeList contiene HIJO");
					adjacent = nodeList.get(nodeList.indexOf(adjacent));
				}else {
					//System.out.println("NodeList no contiene HIJO");
					nodeList.add(adjacent);
					backwards(adjacent, nodeList);
				}
				
				if(current.getSucc() == null) {
					//System.out.println("Actual no tiene sucesor");
					current.setSucc(adjacent);
				}else {
					//System.out.println("Actual tiene sucesor");
					if(esMejor(current.getSucc(), adjacent)) {
						//System.out.println("Pero el nuevo es mejor");
						current.setSucc(adjacent);
					}
				}
				if(current.getSucc().isSolution() || current.getSucc().getRestante() == 0){
					current.setSolution(true);
				}
			}
			
			
			
			
			
		}
		
	}

	private void forwards() {
		
	}
	
	private Hashtable<Integer,Integer> matricial(){
		 
		array = new Estado[dinero+1][monedas.size()+1]; 
		Hashtable<Integer,Integer> sol = new Hashtable<Integer, Integer>();
		
		
		for (int i = 0; i < array.length; i++) {
			for(int j = 0; j < array[0].length; j++) {
				array[i][j] = new Estado();
			}	
		}
		int[] iniciales = new int[monedas.size()]; 
		Estado Inicial = new Estado(dinero, iniciales);
		array[dinero][0] = Inicial;
		
		int option = 0;
		do {
			System.out.println("Ahora selecciona que metodo matricial utilizar:\n" + 
							   "1.-Backwards." +
							   "2.-Forwards.");
			option = sc.nextInt();
		}while(option <= 0 || option > 2);
		switch(option) {
		
			case 1:
				matBack(array, dinero, 0);
				break;
			case 2: 
				matForw(array);
				break;
		}
		
		
		sol = matriz(array);
		
		return sol;
		
	}
	
	
	/************************************************************************
	 ********************** METODOS AUXILIARES ******************************
	 ************************************************************************/
	
	private void crearSol(Hashtable<Integer, Integer> matricial) {
		
	}

	private void inicio() throws InterruptedException
	{	
		lectura();
		inicializarMonedas();
	}
	
	private void inicializarMonedas()
	{
		//Aqui ponemos las monedas que se van a poder utilizar para dar el cambio
		monedas.add(3);
		monedas.add(2);
		//monedas.add(5);
		//monedas.add(10);
	}
	
	private ArrayList<Integer> setMonedas()
	{
		int i;

		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for(i = 0; i < monedas.size(); i++)
		{
			list.add(0);
		}

		return list;
		
	}
	
	private boolean esPosible(int x)
	{
		int i;
		boolean posible = false;
		for(i = 0; i < monedas.size(); i++)
			if(x-monedas.get(i) < dinero)
			{
				posible = true;
				break;
			}
		return posible;
	}
	
	private boolean esMejor(Estado origen, Estado destino)
	{
		
		int i;
		int contadorO, contadorD;
		contadorO = contadorD = 0;
		
		for(i = 0; i < monedas.size(); i++)
		{
			
			contadorO += origen.getMonedas()[i];
			contadorD += destino.getMonedas()[i];
		}
		
		if(contadorO < contadorD) return false;
		else return true;
	}

	private int obtenerMayor()
	{
		int minkey;
		minkey = 0;
		java.util.Set<Integer> set = sol.keySet();
		for(int key : set)
			if(key > minkey)
				minkey = key;
		return minkey;
	}
	
	private int calcularUsadas(ArrayList<Integer> list)
	{
		int i;
		int contador = 0;
		for(i = 0; i < list.size(); i++)
			contador += list.get(i);
		return contador;
	}
	
	private void imprimirSolucion(Estado e)
	{
		System.out.printf("Para la cantidad de %d € se han empleado:\n" , dinero);
		for(int i= 0; i < e.getMonedas().length; i++)
		{
			if(e.getMonedas()[i] > 1)
				System.out.printf("%d monedas de %d €\n", e.getMonedas()[i], monedas.get(i));
			else
				System.out.printf("%d moneda de %d €\n", e.getMonedas()[i], monedas.get(i));
		}
		if(e.getRestante() > 1)
			System.out.printf("Y faltarían %d €\n", resto(e));
		else if (e.getRestante() == 1)
			System.out.printf("Y faltaría %d € \n", resto(e));
	}
	
	private int resto(Estado e)
	{
		int i;
		int contador = 0;
		for(i = 0; i < monedas.size(); i++)
		{
			contador += monedas.get(i)*e.getMonedas()[i];
		}
		return e.getRestante()-contador;
	}
	
	private void lectura() throws InterruptedException
	{
		System.out.println("Seleccione la cantidad a cambiar:");
		dinero = integerController();
	}
	
	@SuppressWarnings("resource")
	private static int integerController() throws InterruptedException
	{
		Scanner sc = new Scanner(System.in);
	    while(!sc.hasNextInt())
	    {
	    	System.err.println("Inserte un numero.");
	        TimeUnit.SECONDS.sleep(1);
	        System.out.println("Inténtelo otra vez:");
            sc.next();
        }
	    int n = sc.nextInt();
	   
        return n;
    }
	/*
	private void backward(Estado actual)
	{ 
		int i;
		if(actual.getRestante() == 0 || !esPosible(actual.getRestante()))
		{
			sol.put(actual.getRestante(), actual);
		}
		else
		{
			for(i = 0 ; i <= monedas.size()-1; i++)
			{
				if(actual.getRestante()-monedas.get(i) >= 0)
				{
					ArrayList<Integer> m = new ArrayList<Integer>(actual.getMonedas());
					m.set(i, actual.getMonedas().get(i)+1);
					Estado nuevo = new Estado(actual.getRestante()-monedas.get(i), m);
					nuevo.setUsadas(calcularUsadas(m));
					
					if(sol.containsKey(actual.getRestante()-monedas.get(i)))
					{
						if(sol.get(nuevo.getRestante()).getUsadas() > nuevo.getUsadas())
						{
							sol.put(nuevo.getRestante(), nuevo);
							System.out.println(sol);
							backward(sol.get(nuevo.getRestante()));
						}
					}
					else
					{
						sol.put(nuevo.getRestante(), nuevo);
						System.out.println(sol);
						backward(sol.get(nuevo.getRestante()));
					}
				}
			}
		}
		
		
	}*/

	private ArrayList<Integer> ceroMonedas()
	{
		int i;
		ArrayList<Integer> list = new ArrayList<>();
		for(i = 0; i < monedas.size(); i++)
			list.add(0);
		return list;
	}
	
	private Hashtable<Integer, Integer> matriz(Estado[][] ruta) {
		
		Hashtable<Integer, Integer> solution = new Hashtable<Integer, Integer>();
		/*
		for(int i = 0; i < monedas.size(); i++) {
			solution.put(monedas.get(i), 0);	
		}

		for (int columna = 0; columna < ruta[0].length - 1; columna++) {
			for(int fila = 1; fila < ruta.length - 1; fila++) {
				
				System.out.print(ruta[fila][columna] + " ");
				//int ultimaFila = 0;
				//if(ruta[fila][columna] == 1) {
					//int monedaUsada = fila - ultimaFila;
					//solution.replace(monedaUsada, solution.get(monedaUsada) + 1);	
				//}
			}
		}*/
		return solution;
	}

	
	/*public void forward()
	{
		int i, j, k;
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(i = 0; i < monedas.size(); i++)
			for(j = 0; j < )
				for(k = 0; k < )
					list();
					
	}*/
}

