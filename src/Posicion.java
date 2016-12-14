public class Posicion {

    private int x;
    private int y;

    public Posicion(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPosicion(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Posicion))
            return false;
        Posicion otro = (Posicion) o;
        return x == otro.getX() && y == otro.getY();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + x;
        hash = 71 * hash + y;
        return hash;
    }

    /*
    @Override
    public int hashCode() {
	return toString().hashCode();
    }*/

    @Override
    public String toString(){
	return "("+x+","+y+")";       	
    }
}
