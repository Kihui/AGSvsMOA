/**
 * Clase para modelar una partícula magnética
 * 
 */
public class Particula{
    /**Atributos**/
    private int[] ruta;
    private double masa;
    private double campoM;
    private Posicion posicion; //posicion en la estructura
     //fuerza que se le es aplicada a la particula por sus vecinos
    private double[] fuerza;
    //costo de la ruta
    private double costo;
    
    public Particula(int numCiudades, int x, int y) {
        posicion = new Posicion(x, y);
        ruta = new int[numCiudades];;
        fuerza = new double[ruta.length];
    }

    public void setMasa(double m) {
        masa = m;
    }

    public double getMasa() {
        return masa;        
    }

    public void setCampoM(double cm) {
        campoM = cm;
    }

    public double getCampoM() {
        return campoM;
    }

    public void setPosicion(int x, int y) {
        posicion.setPosicion(x, y);
    }

    public Posicion getPosicion() {
        return posicion;
    }
    
    public void setFuerza(int i, double f) {
        fuerza[i] = f;
    }

    public double getFuerza(int i) {
        return fuerza[i];
    }

    public void agregaCiudad(int index, int c){
	if(index >= ruta.length || index < 0)
	    return;
	ruta[index] = c;	
    }

    public int size(){
	return ruta.length;
    }

    public int getCiudad(int index){
	if(index >= ruta.length || index < 0)
	    return -1;
	return ruta[index];
    }

    public void setCosto(double costo){
	this.costo = costo;
    }

    public double getCosto(){
	return costo;
    }
}
