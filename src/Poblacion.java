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

    public void normalizarCamposM() {
        double bmin = particulas(new Posicion(0, 0));
        double bmax = 0;
        for(Particula p : particulas.values()) {
            if(p.getCampoM() < bmin)
                bmin = p.getCampoM();
            if(p.getCampoM() > bmax)
                bmax = p.getCampoM();
        }
        for(Particula p : particulas.values())
            p.setCampoM((p.getCampoM() - bmin) / (bmax - bmin));
    }
    
    public void evaluaFuerza() {
        for (Particula p : particulas.values()) {
            for(int i = 0; i < p.size(); i++)
                p.setFuerza(i, 0);
            for(Particula v : getVecinosParticula(p)) {
                for(int i = 0; i < p.size(); i++) {
                    double suma = 0;
                    for(int j = 0; j < p.size(); j++) {
                        double dif = (p.getCiudad(j) - v.getCiudad(j)) / (p.size());
                        suma += dif * dif;
                    }
                    double distancia = Math.sqrt((1/p.size()) * suma);
                    double f = p.getFuerza(i) + ((p.getCiudad(i) - v.getCiudad(i))* v.getCampoM()) / distancia;
                    p.setFuerza(i, f);
                }
            }
        }
    }

    public void evaluaVelocidad() {
    }

    public void actualizaPosicion() {
    }
    
    public void agrega(Particula p, FuncionCosto fun){
	p.setCosto(fun.evaluar(p));
	particulas.put(p.getPosicion(), p);
    }    
}
