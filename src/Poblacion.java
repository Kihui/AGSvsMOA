import java.util.HashMap;
import java.util.Random;
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
        double bmin = particulas.get(new Posicion(0, 0)).getCampoM();
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
        for(Particula p : particulas.values()) {
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
        Random r = new Random(3);

        // Evaluando velocidades
        for(Particula p : particulas.values()) {
            for(int i = 0; i < p.size(); i++) {
                double v = (p.getFuerza(i) / p.getMasa()) * (r.nextDouble() * p.size());
                p.setVelocidad(i, v);
            }
        }

        // Normalizando velocidades
        for(Particula p : particulas.values()) {
            double vmin = p.getVelocidad(0);
            double vmax = 0;
            for(int i = 0; i < p.size(); i++) {
                if(p.getVelocidad(i) < vmin)
                    vmin = p.getVelocidad(i);
                if(p.getVelocidad(i) > vmax)
                    vmax = p.getVelocidad(i);
            }
            for(int i = 0; i < p.size(); i++)
                p.setVelocidad(i, (p.getVelocidad(i) - vmin) / (vmax - vmin));
        }
    }

    // Actualiza la posicion de todas las particulas menos de la que tiene menor costo
    public void actualizaPosicion(int costoMin) {
        Random r = new Random(1);
        for(Particula p : particulas.values()) {
            if(p.getCosto() > costoMin) {
                Particula[] vecinos = getVecinosParticula(p);
                Particula mv = vecinos[0];
                for(int i = 1; i < 4; i++)
                    if(vecinos[i].getCampoM() > mv.getCampoM())
                        mv = vecinos[i];                
                for(int i = 0; i < p.size(); i++)
                    if(r.nextDouble() < p.getVelocidad(i))
                        permuta(p, mv, i);
            }
        }
    }

    private void permuta(Particula p, Particula v, int i) {
        if(p.getCiudad(i) == v.getCiudad(i))
            return;
        int x1 = 0;
        for(int j = 0; j < v.size(); j++)
            if(p.getCiudad(i) == v.getCiudad(j))
                x1 = j;
        int x2 = 0;
        for(int j = 0; j < p.size(); j++)
            if(p.getCiudad(j) == v.getCiudad(x1))
                x2 = j;
        p.agregaCiudad(x2, p.getCiudad(i));
        p.agregaCiudad(i, v.getCiudad(x1));
    }
    
    public void agrega(Particula p, FuncionCosto fun){
	p.setCosto(fun.evaluar(p));
	particulas.put(p.getPosicion(), p);
    }    
}
