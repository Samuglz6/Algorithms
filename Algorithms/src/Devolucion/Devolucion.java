package Devolucion;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Devolucion {
	
	private int dinero;
	private ArrayList<Integer> monedas = new ArrayList<Integer>();
	private Hashtable<Integer, Estado> sol = new Hashtable<Integer, Estado>();
	
	public Devolucion() throws InterruptedException
	{
		backward(inicio());
		imprimirSolucion(sol.get(obtenerMenor()));
	}

	private Estado inicio() throws InterruptedException
	{
		lectura();
		Estado inicial = new Estado(dinero, setMonedas());
		return inicial;
	}
	
	private ArrayList<Integer> setMonedas()
	{
		int i;
		
		//Aqui ponemos las monedas que se van a poder utilizar para dar el cambio
		monedas.add(1);
		monedas.add(2);

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
			if(x-monedas.get(i) >= 0)
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
		
		for(i = 0; i < origen.getUsadas().size()-1; i++)
		{
			contadorO += origen.getUsadas().get(i);
			contadorD += destino.getUsadas().get(i);
		}
		
		return contadorD < contadorO;
	}
	
	private int obtenerMenor()
	{
		int minkey;
		minkey = 99999;
		java.util.Set<Integer> set = sol.keySet();
		for(int key : set)
			if(key < minkey)
				minkey = key;
		return minkey;
	}
	
	private void imprimirSolucion(Estado e)
	{
		System.out.printf("Para la cantidad de %d € se han empleado:\n" , dinero);
		for(int i= 0; i < e.getUsadas().size(); i++)
		{
			if(e.getUsadas().get(i) > 1)
				System.out.printf("%d monedas de %d €\n", e.getUsadas().get(i), monedas.get(i));
			else
				System.out.printf("%d moneda de %d €\n", e.getUsadas().get(i), monedas.get(i));
		}
		if(e.getRestante() > 1)
			System.out.printf("Y faltarían %d €\n", e.getRestante());
		else
			System.out.printf("Y faltaría %d € \n", e.getRestante());
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
	
	private void forward(Estado e)
	{
		int i,j,k;
	}
	
	
	private void backward(Estado e)
	{ 
		int i;
		if(e.getRestante() == 0 || !esPosible(e.getRestante()))
		{
			sol.put(e.getRestante(), e);
		}
		else
		{
			for(i = monedas.size()-1; i >= 0 ; i--)
			{
				if(e.getRestante()-monedas.get(i) >= 0)
				{
					ArrayList<Integer> m = new ArrayList<Integer>(e.getUsadas());
					m.set(i, e.getUsadas().get(i)+1);
					Estado nuevo = new Estado(e.getRestante()-monedas.get(i), m);
					
					if(sol.containsKey(nuevo.getRestante()))
					{
						if(esMejor(sol.get(nuevo.getRestante()) , nuevo))
							sol.put(nuevo.getRestante(), nuevo);
					}
					else
					{
						sol.put(nuevo.getRestante(), nuevo);
					}
					backward(sol.get(nuevo.getRestante()));
				}
			}
		}
	}
}

