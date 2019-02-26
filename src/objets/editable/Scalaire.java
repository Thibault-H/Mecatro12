package objets.editable;

public class Scalaire implements Entrable{
	int value;
	
	public Scalaire(int valeur) {
		value=valeur;
	}

	@Override 
	public TypeEntrable getTypeEntrable() {
		return TypeEntrable.Scalaire;
	}
}
