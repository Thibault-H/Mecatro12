package algLin;

public class Quaternion implements TesteurNullite{
	double[] valeur;	//H = R4
	public static final Quaternion i = new Quaternion(0,1,0,0);
	public static final Quaternion j = new Quaternion(0,0,1,0);
	public static final Quaternion k = new Quaternion(0,0,0,1);

	public static final boolean POLAIRE = true;
	public static final boolean CARTESIEN = false;
	
	
	
	//Constructeurs
	
	private Quaternion() {
		valeur = new double[] {0,0,0,0};
	}
	
	public Quaternion(double vr, double vi, double vj, double vk) {
		this();
		valeur[0]=vr;
		valeur[1]=vi;
		valeur[2]=vj;
		valeur[3]=vk;
	}
	

	
	
	/**
	 * Definir un quaternion avec ses coordonnees polaires, si estPolaire == true.
	 * Par défaut, estPolaire == false ; dans ce cas, "alpha" et "axe" sont respectivement la partie re et im du quaternion
	 * @param alpha
	 * @param axe
	 * @param estPolaire
	 */
	public Quaternion(double alpha, R3 axe, boolean estPolaire) {
		this();
		double partieRe;
		R3 partieIm;
		if(!estPolaire) {
			partieRe = alpha;
			partieIm = axe;
		}
		else {
			partieRe = Math.cos(alpha/2);
			partieIm = axe.normer().prod(Math.cos(alpha/2));	
		}	

		valeur[0] = partieRe;
		valeur[1]=partieIm.get1();
		valeur[2]=partieIm.get2();
		valeur[3]=partieIm.get3();
	}
	
	public Quaternion(double partieRe, R3 partieIm) {
		this(partieRe,partieIm,false);
	}
	
	
	public R3 agirSur(R3 v) {
		Quaternion q = new Quaternion(0,v);
		return fois(q).fois(inv()).getPartieIm();
	}
	
	//Getters
	
	public double getPartieRe() {
		return valeur[0];
	}
	
	public R3 getPartieIm() {
		return new R3(valeur[1],valeur[2],valeur[3]);
	}
	
	
	//Operations algebriques
		
	
	public Quaternion plus(Quaternion q2) {
		return new Quaternion(valeur[0]+q2.valeur[0] , getPartieIm().plus(q2.getPartieIm()) );
	}
	
	public Quaternion opp() {
		return new Quaternion(-valeur[0],-valeur[1],-valeur[2],-valeur[3]);
	}
	
	public Quaternion moins(Quaternion q2) {
		return plus(q2.opp());
	}
	
	
	public Quaternion fois(Quaternion q2) {
		return new Quaternion(
				valeur[0]*q2.valeur[0] - valeur[1]*q2.valeur[1] - valeur[2]*q2.valeur[2] - valeur[3]*q2.valeur[3] ,
				valeur[0]*q2.valeur[1] + valeur[1]*q2.valeur[0] + valeur[2]*q2.valeur[3] - valeur[3]*q2.valeur[2] ,
				valeur[0]*q2.valeur[2] + valeur[2]*q2.valeur[0] + valeur[3]*q2.valeur[1] - valeur[1]*q2.valeur[3] ,
				valeur[0]*q2.valeur[3] + valeur[3]*q2.valeur[0] + valeur[1]*q2.valeur[2] - valeur[2]*q2.valeur[1] 
				);
	}
	
	public Quaternion fois(double re) {
		return new Quaternion(
				valeur[0]*re,
				valeur[1]*re,
				valeur[2]*re,
				valeur[3]*re);
	}
	
	public Quaternion conj() {
		return new Quaternion(getPartieRe(), getPartieIm().opp());
	}
	
	public double normeCarree() {
		return fois(conj()).getPartieRe();
	}
	
	public Quaternion inv() {
		Quaternion conjugue = conj();
		return conjugue.fois( 1/fois(conj()).getPartieRe());
	}
	
	
	//Autres
	
	
	
	
	public String toString() {
		String p0,p1,p2,p3,result;
		p0=p1=p2=p3= "";
		if (!estNul(valeur[0]))
			p0= valeur[0]+"  + ";
		if (!estNul(valeur[1]))
			p1= valeur[1]+" i + ";
		if (!estNul(valeur[2]))
			p2= valeur[2]+" j + ";
		if (!estNul(valeur[3]))
			p3= valeur[3] + " k + ";
		result = p0 + p1+p2+p3;
		return result.substring(0,result.length()-3);
	}
	
	
	public static void main(String[] args) {
		Quaternion rot1 = new Quaternion(0, R3.uz);
		System.out.println(rot1);
		R3 v = R3.ux;
		System.out.println(rot1.agirSur(v));
	}

}

