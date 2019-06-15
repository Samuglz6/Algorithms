package Devolucion;

import java.util.ArrayList;

public class Estado {
	private int restante;
	private int[] monedas;
	private int usadas;
	
	
	public Estado() {
		
	}
	public Estado(int x, int[] y)
	{
		restante = x;
		monedas = y;
		usadas = 0;
	}
	
	public int getRestante()
	{
		return restante;
	}
	
	public int[] getMonedas()
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
	
	public void setMonedas(int[] valor)
	{
		this.monedas = valor;
	}
	
	public void setUsadas(int valor)
	{
		this.usadas = valor;
	}
	
	public String toString()
	{
		return "Resto: "+restante+" Monedas: "+monedas+" Usadas: "+usadas+"\n";
	}
}
