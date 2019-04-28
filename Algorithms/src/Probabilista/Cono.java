package Probabilista;


public class Cono extends Funciones{

	private double r;
	private double h;
	
	public Cono(double inf, double sup, double r, double h) {
		super(inf, sup);
		this.h = h;
		this.r = r;
	}

	@Override
	public double f(double x, double y) {
		double z = h - (h * Math.sqrt(Math.pow(x, 2)  +  Math.pow(y, 2)))/r;
		return z;
	}

	@Override
	public double volNumericoP(int k) {
		
		int aux = 0;
		double volumen = -1, x = -1, y = -1, z = -1;
		
		for(int i = 0; i < k; i++) {
			
			x = 0;
			y = 0;
			z = 0;
			
			if(z <= f(x, y)) {
				aux++;
			}
			
		}
		
		volumen = 0;
		
		return 0;
	}

	@Override
	public double volumen() {
		
		double volumen = (Math.PI * Math.pow(r, 2) * h)/3;
		return volumen;
		
	}

	
	
	
}
