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
    /*
    private Particula derecha;
    private Particula izquierda;
    private Particula superior;
    private Particula inferior;
    */

    public Particula(int numCiudades, int x, int y) {
        ruta = new int[numCiudades];
        posicion = new Posicion(x, y);
    }

    public void setMasa(int m) {
        masa = m;
    }

    public double getMasa() {
        return masa;        
    }

    public void setCampoM(int cm) {
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
}
