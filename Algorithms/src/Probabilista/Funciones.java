package Probabilista;

public abstract class Funciones {

	double linf, lsup;
	double[] intervalo;
	double[] intervaloP;
	
	public Funciones(double inf, double sup){
		if(inf > sup){
			double	x = inf;
			inf = sup;
			sup = x;
		}
		linf = inf;
		lsup = sup;
		intervalo = new double[2];
		intervaloP = new double[2]; 
	}
	
	public double linf(){
		return linf;
	}
	
	public double lsup(){
		return lsup;
	}
	
	public void set_linf(double inf){
		linf = inf;
	}
	
	public void set_lsup(double sup){
		lsup = sup;
	}
	
	public double amplitud(){        
		return lsup() - linf();
	}
	
	public boolean vale(double x){
		boolean v = x>=linf() && x<=lsup();
		if(!v) 
			System.out.println(x+" no pertenece a "+ this);
		return v;
	}
	
	public abstract double f(double x, double y);
	
	public abstract double volNumericoP(int k);
	
	public abstract double volumen();
	
	public String toString(){
		return "Intervalo: ["+ linf()+" , "+lsup()+"]";
	}
	
	public void intervaloConfianza(double[] valores){
	
		double media = media(valores);
		double S = cuasiV(valores, media);
		
		intervalo[0] = media - 1.96*S/Math.sqrt(valores.length);
		intervalo[1] = media + 1.96*S/Math.sqrt(valores.length);
	}
	
	public double intervaloInf(){
		return intervalo[0];
	}
	
	public double intervaloSup(){
		return intervalo[1];
	}
	
	public double media(double valores[]){
		double media=0;
		for(int x = 0; x < valores.length; x++){
			media = media + valores[x];
		}
	
		return media/valores.length;
	}
	
	public double cuasiV(double[] valores, double media){
		double S=0;
		for(int x = 0; x < valores.length; x++){
			S = S + Math.pow(valores[x] - media,2);
		}
		return Math.sqrt(S/(valores.length - 1));
	}
	
	public void IntervaloConfProporciones(double p, int n){
		intervaloP[0] = p - 1.96 * Math.sqrt(Math.abs(p * (1 - p) / n));
		intervaloP[1] = p + 1.96 * Math.sqrt(Math.abs(p * (1 - p) / n));
	}
	
	public double intervaloInfP(){
		return intervaloP[0];
	}
	
	public double intervaloSupP(){
		return intervaloP[1];
	}
}