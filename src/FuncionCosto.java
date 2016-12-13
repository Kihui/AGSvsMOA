import org.moeaframework.problem.tsplib.*;

/**
 * Clase para evaluar a las partículas
 * y establecer su campo magnético.
 */
public class FuncionCosto{

    private DistanceTable dt;

    public FuncionCosto(DistanceTable dt){
        this.dt = dt;
    }

    public double evaluar(Particula p){
        double fitness = 0;
        for(int i = 0; i < p.size() - 1;i++)
            fitness += dt.getDistanceBetween(p.getCiudad(i),p.getCiudad(i+1));
        fitness += dt.getDistanceBetween(p.getCiudad(0),p.getCiudad(p.size()-1));
        return fitness;
    }    
}
