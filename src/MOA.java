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
    private int s,maxgen; //tamaño y maximo número de generaciones
    private int ciudades;
    private FuncionCosto costFun;

    /**
     * @param size el alto y ancho de la cuadricula,
     * por tanto el tamaño de la población es size*size
     */
    public MOA(int alpha, int rho, int s, int maxgen, int ciudades, FuncionCosto costFun){
	this.alpha = alpha;
	this.rho = rho;
	this.maxgen = maxgen;
	this.ciudades = ciudades;
	this.costFun = costFun;
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
		out.agrega(nueva, costFun);
	    }
	return out;
    }

    private void iteracion(Poblacion actual){
	objFun.evaluar(actual);
	//notthereyet
    }

    private ArrayList<Integer> cities(){
	ArrayList<Integer> cities = new ArrayList<>();
	for(int m = 1; m <= ciudades; m++)
	    cities.add(m);
	return cities;
    }
}
