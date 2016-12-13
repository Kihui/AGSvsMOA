import java.util.HashMap;

/** 
 * Clase para modelar la población de partículas 
 * para el MOA
 */
public class Poblacion{
    /**Atributos**/
    private HashMap<Posicion, Particula> particulas;
    private int generacion;
    // Longitud y ancho de la estructura
    private int s;

    public Poblacion(int gen, int s) {
        generacion = gen;
        particulas = new HashMap<>();
        this.s = s;
    }

    public void evaluaMasa(int alfa, int rho) {
        for (Particula p : particulas.values())
            p.setMasa(alfa + rho * p.getCampoM());   
    }

    private Particula[] getVecinosParticula(Particula p) {
        Particula[] vecinos = new Particula[4];
        int x = p.getPosicion().getX();
        int y = p.getPosicion().getY();
        int x1 = x != 1 ? x - 1 : s;
        int x2 = x != s ? x + 1 : 1;
        int y1 = y != 1 ? y - 1 : s;
        int y2 = y != s ? y + 1 : 1;
        vecinos[0] = particulas.get(new Posicion(x1, y));
        vecinos[1] = particulas.get(new Posicion(x, y1));
        vecinos[2] = particulas.get(new Posicion(x, y2));
        vecinos[3] = particulas.get(new Posicion(x2, y));
        return vecinos;
    }

    public void evaluaFuerza() {
        double f = 0;
        for (Particula p : particulas.values()) {
            for(Particula v : getVecinosParticula(p)) {
                // wtf que son las dimensiones k?????????
//                f += 
            }
        }
    }

    public void agrega(Particula p, FuncionCosto fun){
	p.setCosto(fun.evaluar(p));
	particulas.put(p.getPosicion(), p);
    }    
}
