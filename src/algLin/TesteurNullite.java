package algLin;

//Permet de fixer la precision de la geometrie : si abs(d)<eps , alors on considere que d=0
public interface TesteurNullite {
	
	public default boolean estNul(double d) {
		//eps 10^-4
		return 10000 * d*d < 0.001 * 0.001; 
	}
}
