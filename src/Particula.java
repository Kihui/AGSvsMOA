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
    private double fuerza;
    
    public Particula(int numCiudades, int x, int y) {
        ruta = new int[numCiudades];
        posicion = new Posicion(x, y);
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

    public void setFuerza(double f) {
        fuerza = f;
    }

    public double getFuerza() {
        return fuerza;
    }
}
