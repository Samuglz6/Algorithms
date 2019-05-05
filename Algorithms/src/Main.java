import Combinatorio.Combinatorio;
import Devolucion.Devolucion;
import Probabilista.*;
import Probabilista.Cono;

import java.util.Scanner;

import BusquedaTexto.*;


public class Main {

	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String [] args)
	{
		try {
			
			
			int option = 0;
			
			do {
				System.out.println("Cual es la practica que queremos probar?: ");
				System.out.println("1.- Combinatorio.");
				System.out.println("2.- Devolucion.");
				System.out.println("3.- Probabilista.");
				System.out.println("4.- Busqueda de patrones.");
				System.out.print("-->");
				option = sc.nextInt();
			}while(option < 1 || option > 4);
			
			switch(option) {
				case 1:
					
					Combinatorio combo = new Combinatorio();
					
					break;
				case 2:
					
					Devolucion devol = new Devolucion();
					
					break;
				case 3:
					
					Probabilista proba  = new Probabilista();
					
					break;
				case 4:
					
					BusquedaTexto busqu = new BusquedaTexto();
					
					break;
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
