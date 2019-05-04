package Probabilista;

public class Probabilista {

	public Probabilista() {
		
		int r = 5;
		int h = 12;
		Cono cono = new Cono(0, 12, 5, 12);
		
		System.out.println("El volumen real del cono con r= " + r + " y h= " + h + " es: " + cono.volumen());
		System.out.println("El volumen probabilistico es: " + cono.volNumericoP(1000) + " con el intervalo de confianza: " +
						   "[" + cono.intervaloInfP() + ", " + cono.intervaloSupP() + "].");
		
		
		
		
	}
	
}
