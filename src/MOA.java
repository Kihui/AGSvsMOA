import java.util.ArrayList;
import java.util.Random;

/**
 * Clase para modelar el Algoritmo de Optimización inspirado en Magnetismo
 * MOA
 */

public class MOA{
    /**Atributos**/
    private int alpha;
    private int rho;
    private Poblacion poblacion;
    private int s,maxGen; //tamaño y maximo número de generaciones
    private int ciudades;
    //private FuncionCosto objFun;
    private FuncionObjetivo objFun;

    /**
     * @param size el alto y ancho de la cuadricula,
     * por tanto el tamaño de la población es size*size
     */
    public MOA(int alpha, int rho, int s, int maxGen, int ciudades, FuncionObjetivo objFun){
	this.alpha = alpha;
	this.rho = rho;
	this.maxGen = maxGen;
	this.ciudades = ciudades;
	this.objFun = objFun;
    }

    private Poblacion randomValidP(){
	Poblacion out = new Poblacion(1,s);
	Random r = new Random(4);
	for(int i = 1; i < s; i++ )
	    for(int j = 1; j < s; j++){
		ArrayList<Integer> cities = cities();
		Particula nueva = new Particula(ciudades,i,j);
		nueva.agregaCiudad(0,cities.remove(0));
		for(int k = 1; k < ciudades; k++){
		    int random = r.ints(1,0,cities.size()).findFirst().getAsInt();
		    nueva.agregaCiudad(k,cities.remove(random));
		}
		out.agrega(nueva, objFun);
	    }
	return out;
    }

    private void iteracion(Poblacion actual){
	int mejorCosto = objFun.evaluar(actual);
	actual.normalizarCamposM();
	actual.evaluaMasa(alpha, rho);
	actual.evaluaFuerza();
	actual.actualizaPosicion(mejorCosto);

	actual.nuevaGen();
	//notthereyet
    }

    public void run(){
	Poblacion p = randomValidP();
	for(int i = 1; i <= maxGen; i++){
	    iteracion(p);
	    System.out.println(p.getMejorCalificado());
	}
    }

    private ArrayList<Integer> cities(){
	ArrayList<Integer> cities = new ArrayList<>();
	for(int m = 1; m <= ciudades; m++)
	    cities.add(m);
	return cities;
    }
}
