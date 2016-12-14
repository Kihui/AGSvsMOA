/**
 * Función objetivo para evaluar una partícula
 * dado su costo.
 */

public class FuncionObjetivo{

    public void evaluar(Poblacion p){

        double maxfit = p.getPeor().getCosto();
        double minfit = p.getMejor().getCosto();
        
        for(Particula part : p.getParticulas()){
            double eval = 0;
            eval = (maxfit + minfit) - part.getCosto();
            part.setCampoM(eval);
        }
    }
}
