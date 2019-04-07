package Devolucion;

import java.util.ArrayList;

public class Estado {
	private int restante;
	private ArrayList<Integer> monedas = new ArrayList<Integer>();
	private int usadas;
	
	public Estado(int x, ArrayList<Integer> y)
	{
		restante = x;
		monedas = y;
		usadas = 0;
	}
	
	public int getRestante()
	{
		return restante;
	}
	
	public ArrayList<Integer> getMonedas()
	{
		return monedas;
	}
	
	public int getUsadas()
	{
		return usadas;
	}
	
	public void setRestante(int valor)
	{
		this.restante = valor;
	}
	
	public void setMonedas(ArrayList<Integer> valor)
	{
		this.monedas = valor;
	}
	
	public void setUsadas(int valor)
	{
		this.usadas = valor;
	}
	
	public String toString()
	{
		return "Resto: "+restante+"\nMonedas: "+monedas+"\nUsadas: "+usadas+"\n";
	}
}
