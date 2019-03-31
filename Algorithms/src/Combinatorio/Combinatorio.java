package Combinatorio;

import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

public class Combinatorio {
	public Combinatorio() throws InterruptedException
	{
		menu();
	}
	
	private int iterativo(int n, int k)
	{
		return factorial(n)/(factorial(k) * factorial(n-k));
	} 
	
	private int recursivo(int n, int k)
	{
		if(k == 0 || k == n)
			return 1;
		if(k > n)
			return 0;
		return recursivo(n-1, k-1)+recursivo(n-1,k);
	}
	
	private int factorial(int n)
	{
		int i;
		int valor = 1;
		for(i = n; i >= 1; i--)
			valor *= i;
		return valor;
	}
	
	private void menu() throws InterruptedException{
		long t1,t2;
		int[] valores = pedirValores();
		
		System.out.printf("Tiempos de los algoritmos para:"
				+ "\n\tn = %d\tk = %d\n", valores[0], valores[1]);
	
	   imprimir(iterativo(valores[0], valores[1]), "Iterativo");
	   imprimir(recursivo(valores[0], valores[1]),"Recursivo");
	   imprimir(recursivoPilas(valores[0], valores[1]), "Recursivo (3 pilas)");
	   imprimir(recursivoConPilaLlamada(valores[0], valores[1]), "Recursivo (4 pilas)");
	}
	
	private void imprimir(int x, String s)
	{
		float t1, t2;
		t1 = System.currentTimeMillis();
		System.out.printf("Algoritmo %s :\nResultado: %d\n", s, x);
		System.out.printf("Tiempo: %f ms", (System.currentTimeMillis()-t1));
	}
	
	private int[] pedirValores() throws InterruptedException
	{
		int [] valores = new int[2];
		
		System.out.println("Introduce valor para la n:");
		valores[0] = integerController();
		
		System.out.println("Introduce valor para la k:");
		valores[1] = integerController();
		
		return valores;
	}
	
	private int recursivoPilas(int x, int y)
	{
		Stack<Integer> nStack = new Stack<Integer>();
		Stack<Integer> kStack = new Stack<Integer>();
		Stack<Integer> rStack = new Stack<Integer>();
		
		int n;
		int k;
		int r = 0;
		
		nStack.push(x);
		kStack.push(y);
		
		while(!nStack.isEmpty() && !kStack.isEmpty())
		{
			if(kStack.peek() == nStack.peek() || kStack.peek() == 0)
			{
				kStack.pop();
				nStack.pop();
				rStack.push(1);
			}
			else if(kStack.peek() > nStack.peek())
			{
				kStack.pop();
				nStack.pop();
				rStack.push(0);
			}
			else
			{
				n = nStack.pop();
				k = kStack.pop();
			
				nStack.push(n-1);
				kStack.push(k-1);
		
				nStack.push(n-1);
				kStack.push(k);
			}
		}
		
		while(!rStack.isEmpty())
		{
			r += rStack.pop();
		}
		if(r == 0) System.out.println("n no puede ser menor que k.");
		return r;
	}
	
	private int recursivoConPilaLlamada(int n, int k) 
	{
		Stack<Integer> pilaN = new Stack<Integer>();
		Stack<Integer> pilaK = new Stack<Integer>();
		Stack<Integer> pilaResult = new Stack<Integer>();
		Stack<Integer> pilaLlamada = new Stack<Integer>();
		int sol = 0;
		
		pilaN.push(n);
		pilaK.push(k);
		pilaLlamada.push(1);
		pilaResult.push(0);
		
		while(!pilaN.empty())
		{		
			
			while((pilaK.peek() >= pilaN.peek() || pilaK.peek() >= 0) && pilaLlamada.peek() <= 2)
			{
				switch(pilaLlamada.peek())
				{
					case 1:
						pilaN.push(pilaN.peek()-1);
						pilaK.push(pilaK.peek()-1);
					break;
					case 2:
						pilaN.push(pilaN.peek()-1);
						pilaK.push(pilaK.peek());
					break;
				}
				
				pilaLlamada.push(1);
				
				if(pilaK.peek() == pilaN.peek() || pilaK.peek() == 0)
				{
					pilaResult.push(1);
					pilaN.pop();
					pilaK.pop();
					pilaLlamada.pop();
					sol = pilaResult.pop();
				}
				else
				{
					pilaResult.push(0);
				}
				
				if(!pilaN.empty())
				{
					pilaLlamada.push(pilaLlamada.pop()+1);
					pilaResult.push(pilaResult.pop()+sol);
				}
			}
			
			if(sol == 0) sol = 1;
		}
		return sol;
	}
	
	private static int integerController() throws InterruptedException
	{
 	  	Scanner sc = new Scanner(System.in);
	    while(!sc.hasNextInt())
	    {
	    	System.err.println("The options can only be integers.");
	        TimeUnit.SECONDS.sleep(1);
	        System.out.println("Try again:");
            sc.next();
        }
	    int n = sc.nextInt();
	   
        return n;
    }
}
