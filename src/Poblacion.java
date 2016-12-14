import java.util.HashMap;
import java.util.Random;
import java.util.LinkedList;

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
    private Random r;

    public Poblacion(int gen, int s) {
        generacion = gen;
        particulas = new HashMap<>();
	//comprobacion de que s sea mayor que 2 please
        this.s = s;
        r  = new Random(2);
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
	double bmin = particulas.get(new Posicion(1, 1)).getCampoM();
        //double bmin = particulas.get(new Posicion(0, 0)).getCampoM();
        double bmax = 0;
        for(Particula p : getParticulas()) {
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
                    double f = 0;
                    if(getDistancia(p,v) != 0)
                        f = p.getFuerza(i) + ((p.getCiudad(i) - v.getCiudad(i))* v.getCampoM()) / getDistancia(p, v);
                    p.setFuerza(i, f);
                }
            }
        }
    }

    private double getDistancia(Particula p, Particula v) {
        double suma = 0;
        for(int j = 0; j < p.size(); j++) {
            double dif = (p.getCiudad(j) - v.getCiudad(j));
            suma += dif * dif;
        }
        return Math.sqrt(suma / p.size());
    }

    public void evaluaVelocidad() {
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
                if(p.getVelocidad(i) != 0)
                    p.setVelocidad(i, (p.getVelocidad(i) - vmin) / (vmax - vmin));            
        }
    }

    // Actualiza la posicion de todas las particulas menos de la que tiene menor costo
    public void actualizaPosicion(double costoMin) {
        for(Particula p : particulas.values()) {
            if(p.getCosto() > costoMin) {
                Particula[] vecinos = getVecinosParticula(p);
                Particula mv = vecinos[0];
                for(int i = 1; i < 4; i++)
                    if(vecinos[i].getCampoM() > mv.getCampoM())
                        mv = vecinos[i];                
                for(int i = 1; i < p.size(); i++)
                    if(r.nextDouble() <= p.getVelocidad(i)) {
                        permuta(p, mv, i);
                    }
            }
        }
    }

    private void permuta(Particula p, Particula v, int i) {
        if(p.getCiudad(i) == v.getCiudad(i))
            return;
        int x = 0;
        for(int j = 0; j < p.size(); j++)
            if(p.getCiudad(j) == v.getCiudad(i)) {
                x = j;
            }
        p.agregaCiudad(x, p.getCiudad(i));
        p.agregaCiudad(i, v.getCiudad(i));
    }
    
    public void agrega(Particula p, FuncionObjetivo fun){
	fun.evaluar(p);
	particulas.put(p.getPosicion(), p);
    }

    public void agrega(Particula p){
	particulas.put(p.getPosicion(), p);
    }

    public int getGeneracion(){
	return generacion;
    }

    public int getS(){
	return s;
    }

    public LinkedList<Particula> getParticulas(){
	LinkedList<Particula> parts = new LinkedList<>(particulas.values());
	return parts;
    }

    public Particula getMejor(){
	LinkedList<Particula> particles = getParticulas();
	Particula mejor = particles.get(0);
	for(Particula part : particles)
	    if(part.getCosto() < mejor.getCosto())
		mejor = part;
	return mejor;
    }

    public Particula getPeor(){
	LinkedList<Particula> particles = getParticulas();
	Particula peor = particles.element();
	for(Particula part : particles)
	    if(part.getCosto() > peor.getCosto())
		peor = part;
	return peor;
    }

    public void nuevaGen(){
	generacion++;
    }

    public Particula getMejorCalificado(){
	LinkedList<Particula> particles = getParticulas();
	Particula mejor = particles.get(0);
	for(Particula part : particles)
	    if(part.getCampoM() > mejor.getCampoM())
		mejor = part;
	return mejor;
    }

    private void permuta(Particula p, int i) {
        int x = r.nextInt(p.size() - 1) + 1;
        if(x != i){
            int c1 = p.getCiudad(i);
            p.agregaCiudad(i, p.getCiudad(x));
            p.agregaCiudad(x, c1);
        }            
    }
    
    public void SRR(double t, double c) {
        for(Particula p : particulas.values())
            for(Particula v : getVecinosParticula(p))
                if(getDistancia(p, v) < t){
                    double[] probabilidades = new double[p.size()];
                    double min = 1000000, max = 0;
                    for(int i = 0; i < p.size(); i++) {
                        probabilidades[i] = c * r.nextDouble() * p.size();
                        if(probabilidades[i] > max)
                            max = probabilidades[i];
                        if(probabilidades[i] < min)
                            min = probabilidades[i];
                    }
                    for(int i = 0; i < p.size(); i++){
                        probabilidades[i] = (probabilidades[i] -  min) / ( max - min);
                    }
                    for(int i = 1; i < p.size(); i++)
                        if(r.nextDouble() <= probabilidades[i])
                            permuta(p, i);
                }                    
    }

    @Override
    public String toString() {
        String s = "";
        for(Particula p : particulas.values()) {
            s += p.toString() + "\n";
        }
        return s;
    }
}
