package Probabilista;


public class Cono extends Funciones{

	private double r;
	private double h;
	
	public Cono(double linf, double lsup, double r, double h)
	{
		super(linf, lsup);
		this.r = r;
		this.h = h;
	}

	@Override
	public double f(double x, double y) {
		double z = h - (h * Math.sqrt(Math.pow(x, 2)  +  Math.pow(y, 2)))/r;
		return z;
	}

	@Override
	public double volNumericoP(int k) {
		
		int puntos = 0;
		double volumen = -1, x = -1, y = -1, z = -1;
		
		for(int i = 0; i < k; i++) {
			
			x = random(0, r);
			y = random(0, h);
			z = random(0, h); // en torno a r y h
			
			if(z <= f(x, y)) {
				puntos++;
			}
			
		}

		volumen = Math.PI * r * r * h * ((double)puntos/k);	//volumen del cilindro veces los aciertos del
		IntervaloConfProporciones(volumen, k);

		return volumen;
	}

	@Override
	public double volumen() {
		
		double volumen = (4 * Math.PI * r * r) / 3;
		return volumen;
		
	}

	public double random(double min, double max){
		return (Math.random() * (max - min)) + min;
	}

	public double getR() {
		return r;
	}

	public void setR(double r) {
		this.r = r;
	}

	public double getH() {
		return h;
	}

	public void setH(double h) {
		this.h = h;
	}
	
}
	
