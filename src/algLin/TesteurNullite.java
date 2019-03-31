package algLin;

//Permet de fixer la precision de la geometrie : si abs(d)<eps , alors on considere que d=0
public interface TesteurNullite {
	
	
	public default boolean estNul(double d) {
		//eps 10^-4
		return 10000 * d*d < 0.001 * 0.001; 
	}
	
	/*Renvoie true ssi a < b.
	 * En cas de doute (a-b odg de epsilon), renvoie true.
	 * En pratique, a et b sont des carrés de distances
	 * 
	 */
	public default boolean estPlusPetit(double a, double b) {
		return 10000*a - 10000 * b < 10 * 0.001;
	}
}
