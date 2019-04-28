package BusquedaTexto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
	
	static Scanner sc = new Scanner(System.in);
	static BufferedReader br = null;
	
	public static void main(String[] args) throws IOException{
		
		String algoritmo = elegirAlgoritmo();
		String patron = elegirPatron();
		int porcentaje = elegirPorcentaje();	//d = {0...100}
		String salida = "";
		String ruta = "quijote1.txt";
		File texto = new File(ruta);
		
		try{
			br = new BufferedReader(new InputStreamReader(new FileInputStream(texto), "Cp1252"));
		}catch(FileNotFoundException fnf){
			System.out.println("ERROR CON EL ARCHIVO");
			System.exit(0);
		}
		System.out.println("Archivo abierto correctamente.\n...procesando...");
		
		if(algoritmo == "Knuth-Morris-Prat") salida = preKMP(patron, porcentaje);
		else if(algoritmo == "Karp-Rabin") salida = preKR(patron, porcentaje);
		
		
		System.out.println(salida);
	}

	
	
	
	
	private static String preKR(String patron, int porcentaje) throws IOException {
		
		String salida = "---ANALISIS TERMINADO---\n";
		String lineaActual = "";
		int numLinea = 0;
		int leerCada = (int)(100/porcentaje);
		
		while((lineaActual = br.readLine()) != null){
			
			if(numLinea % leerCada == 0){
				
				if(lineaActual.length() >= patron.length()){
					ArrayList<Integer> ocurrenciasLinea = new ArrayList<Integer>();
					KarpRabin(patron, lineaActual, ocurrenciasLinea);
					salida += "\nLinea Nº " + (numLinea-1) + ".\n\t Ocurrencias en:" + ocurrenciasLinea.toString();
				}
				
			}
			numLinea++;
		}
		
		return salida;
	}

	private static String preKMP(String patron, int porcentaje) throws IOException {
		
		ArrayList<Integer> ocurrenciasLinea = new ArrayList<Integer>();
		int leerCada = (int) (100/porcentaje); 	// leerCada X lineas segun el porcentaje total del texto
		String salida = "---ANALISIS TERMINADO---\n";
		String lineaActual = "";
		int numLinea = 0;
		int[] matrizPrepro = preproceso(patron);
		
		while((lineaActual = br.readLine()) != null){
			
			if(numLinea % leerCada == 0){
				
				if(lineaActual.length() >= patron.length()){
					
				ocurrenciasLinea = 	KnuthMorrisPrat(patron, lineaActual, matrizPrepro);
				
				salida += "\nLinea Nº" + (numLinea-1) + ".\n\t Ocurrencias en: " + ocurrenciasLinea.toString(); 
				
				}
				
			}
			
			numLinea++;
			
		}
		
		return salida;
		
	}

	private static int elegirPorcentaje() {
		int foo = -1;
		while (foo <= 0 || foo>100) {
			System.out.println("[SISTEMA] Introduce un porcentaje a analizar (100 para todo el texto).");
			System.out.print("-->");
			foo = Integer.parseInt(sc.next());
			System.out.println();
		}
		return foo;
	}

	private static String elegirPatron() {
		
		String aux = "";
		
		do{
			System.out.print("Por favor introduzca el patron que desea busar: " + "\n-->");
			aux = sc.next();
			System.out.println();
		}while(aux.length() < 1);
		
		
		return aux;
	}

	private static String elegirAlgoritmo() {
		
		String aux = "";
		int foo;
		do{
			System.out.println("Ahora por favor seleccione el metodo que desea ejecutar: " + 
						   "\n 1.- Knuth-Morris-Prat." + 
						   "\n 2.- Karp-Rabin.");
			System.out.print("-->");
			foo = sc.nextInt();
			switch(foo){
				case 1:
						aux = "Knuth-Morris-Prat";
					break;
				case 2:
						aux = "Karp-Rabin";
					break;
	
				
					default:
						System.out.println("Please introduce one of both.");
				
			}
		}while(foo !=1 && foo!=2);

		return aux;
	}

	public static int[] preproceso(String patron){
		
		int[] fallo = new int[patron.length()];
		int i = 2;
		int j = 0;
		fallo[0] = -1;
		
		if(patron.length()>1){
			fallo[1] = 0;
			while (i < patron.length()) {
				if(patron.charAt(i-1) == patron.charAt(j)){
					j++;
					fallo[i] = j;
					i++;
				}
				else{
					if(j > 0){
						j = fallo[j];
					}
					else{
						fallo[i] = 0;
						i++;
					}
				}
			}
		}
		return fallo;
	}

	public static ArrayList<Integer> KnuthMorrisPrat(String patron,String texto,int[] fallo){
		
		ArrayList<Integer> pos = new ArrayList<Integer>();
		int t = 0;
		int p = 0;
		
		while(texto.length()-t>=patron.length()){
			if(p == patron.length()){
				pos.add(t);
				p = 0;
				t++;
			}
			else{
				if(texto.charAt(t + p) == patron.charAt(p)){
					p++;
				}
				else{
					t = t + p - fallo[p];
					if(p > 0){
						p = fallo[p];
					}
				}
			}
		}
		return pos;
	}

	private static void KarpRabin(String patron, String texto, ArrayList<Integer> ocurrencias) {
		
		int m = patron.length();
		
		for (int n = 0; n <= texto.length() - m; n++) {
			
			String aux = texto.substring(n, n + m);
			
			if (aux.hashCode() == patron.hashCode() && aux.equals(patron))
				
				ocurrencias.add(n+1); //Añadimos uno por consistencia con editores de texto
		
		}
	}
}
