import java.util.HashMap;

/** 
 * Clase para modelar la población de partículas 
 * para el MOA
 */
public class Poblacion{
    /**Atributos**/
    public HashMap<Posicion, Particula> particulas;

    public Poblacion() {
        particulas = new HashMap<>();        
    }
}
