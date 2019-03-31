package Devolucion;

import java.util.ArrayList;

public class Estado {
	private int restante;
	private ArrayList<Integer> usadas = new ArrayList<Integer>();
	
	public Estado(int x, ArrayList<Integer> y)
	{
		restante = x;
		usadas = y;
	}
	
	public int getRestante()
	{
		return restante;
	}
	
	public ArrayList<Integer> getUsadas()
	{
		return usadas;
	}
	
	public void setRestante(int valor)
	{
		this.restante = valor;
	}
	
	public void setUsadas(ArrayList<Integer> valor)
	{
		this.usadas = valor;
	}
	
	public String toString()
	{
		return "Resto: "+restante+"\nMonedas: "+usadas.toString()+"\n";
	}
}
