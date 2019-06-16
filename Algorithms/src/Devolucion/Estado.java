package Devolucion;

import java.util.ArrayList;

public class Estado {
	private int restante;
	private int[] monedas;
	private Estado succ;
	private boolean solution;
	
	public Estado() {
		
	}
	public Estado(int x, int[] y)
	{
		restante = x;
		monedas = y;
		succ = null;
		solution = false;
	}
	
	public int getRestante()
	{
		return restante;
	}
	
	public int[] getMonedas()
	{
		return monedas;
	}
	
	public void setRestante(int valor)
	{
		this.restante = valor;
	}
	
	public void setMonedas(int[] valor)
	{
		this.monedas = valor;
	}
	
	public String toString()
	{
		return "Resto: "+restante+" Monedas: "+monedas+"\n";
	}
	public Estado getSucc() {
		return succ;
	}
	public void setSucc(Estado succ) {
		this.succ = succ;
	}
	public boolean isSolution() {
		return solution;
	}
	public void setSolution(boolean solution) {
		this.solution = solution;
	}
}
