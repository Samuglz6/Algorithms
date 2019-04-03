package Devolucion;

import java.util.ArrayList;
import java.util.Hashtable;

public class Devolucion {
	
	Hashtable<Integer,ArrayList<Integer>> solucion = new Hashtable<Integer, ArrayList<Integer>>();
	ArrayList<Integer> monedas = new ArrayList<Integer>();
	Hashtable<Integer, Estado> sol = new Hashtable<Integer, Estado>();
	
	public Devolucion()
	{
		inicio();
		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(0);
		a.add(0);
		Estado inicial = new Estado(3, a);
		backward(inicial);
		System.out.println(sol);
	}
	
	private void forward(Estado e)
	{
		int i,j,k;
		
	}
	
	
	private void backward(int resto, ArrayList<Integer> usadas)
	{
		int i;
		if(resto == 0 || !esPosible(resto))
		{
			solucion.put(resto, usadas);
		}
		else
		{
			for(i = monedas.size()-1; i >= 0 ; i--)
			{
				if(resto-monedas.get(i) >= 0)
				{
					usadas.set(i, usadas.get(i)+1);
					if(solucion.contains(resto))
						if(esMejor(usadas, solucion.get(resto))) solucion.put(resto, usadas);
					backward(resto-monedas.get(i), usadas);	
					usadas.set(i, usadas.get(i)-1);
				}
			}
		}
	}
	
	private void backward(Estado e)
	{ 
		int i;
		if(e.getRestante() == 0 || !esPosible(e.getRestante()))
		{
			System.out.println(e);
			//sol.put(e.getRestante(), e);
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
					if(sol.contains(nuevo.getRestante()))
					{
						if(esMejor(sol.get(nuevo.getRestante()), nuevo))
						{
							sol.put(nuevo.getRestante(), nuevo);
						}
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
	
	private void backward(Estado estoy, int etapa)
	{
		int i;
		
		if(etapa == estoy.getRestante() || !esPosible(estoy, etapa))
		{
			
		}
		else
		{
			for(i = 0; i < monedas.size()-1; i++)
			{	
				if(estoy.getRestante()+monedas.get(i) <= etapa)
				{
					ArrayList<Integer> aux = new ArrayList<Integer>(estoy.getUsadas());
					Estado voy = new Estado(estoy.getRestante()+monedas.get(i), aux);
					sol.put(voy.getRestante(), voy);
					System.out.println(voy);
					backward(voy, etapa+monedas.get(i));
				}
			}
		}
	}
	
	private void matricial()
	{
		
	}
	
	private void inicio()
	{
		monedas.add(1);
		monedas.add(2);
		//monedas.add(5);
	}
	
	private boolean esPosible(Estado e, int x)
	{
		int i;
		boolean posible = false;
		for(i = 0; i < monedas.size(); i++)
			if(e.getRestante()+monedas.get(i) <= x)
			{
				posible = true;
				break;
			}
		return posible;
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
		System.out.println("origen: "+origen.getRestante()+"\ncontador: "+contadorO+"\ndestino: "+destino.getRestante()+"\ncontador: "+contadorD+"\n");
		
		return contadorD < contadorO;
	}
	
	private boolean esMejor(ArrayList<Integer> a, ArrayList<Integer> b)
	{
		int i, contadorA, contadorB;
		contadorA = contadorB = 0;
		
		for(i = 0; i < a.size()-1; i++)
			contadorA += a.get(i);
		
		for(i = 0; i < b.size()-1; i++)
			contadorB += b.get(i);
		
		return contadorB < contadorA;
	}
	
	private int obtenerMenor()
	{
		int minkey;
		minkey = 99999;
		java.util.Set<Integer> set = solucion.keySet();
		for(int key : set)
			if(key < minkey)
				minkey = key;
		return minkey;
	}
}

