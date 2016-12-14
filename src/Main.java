import org.moeaframework.problem.tsplib.*;
import java.io.File;
import java.io.IOException;

public class Main{
    public static TSPInstance problema;

    public static void main(String[] argumenta){

        if(argumenta.length != 1){
            System.err.println("La vida es ruda");
            return;
        }

        try{
            File tsp = new File(argumenta[0]);
            problema = new TSPInstance(tsp);
            String s = argumenta[0].substring(0, argumenta[0].lastIndexOf('.'));
            s += ".opt.tour";
            File tspout = new File(s);
            if(tspout.exists()) {
                problema.addTour(tspout);
                for (Tour tour : problema.getTours())
                    System.out.println("Distancia mínima: " + tour.distance(problema));
            }
        } catch(IOException ioe) {
            System.err.println("Hubo un problema al abrir el archivo TSP");
            ioe.printStackTrace();
        }
	
        if(problema == null)
            return;
	 int[] ciudades = problema.getDistanceTable().listNodes();
	 FuncionObjetivo objFun = new FuncionObjetivo(problema.getDistanceTable());
	 MOA moa = new MOA(100,100,15,600,ciudades.length,objFun);
	 moa.run();
    }
}
