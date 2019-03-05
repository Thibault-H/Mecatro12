package objets.editable;

import algLin.Point3;
import algLin.R3;

public class Point implements Entrable {

	private Point3 value;
	
	public Point(Point3 largeur) {
		value=largeur;
	}

	public Point3 getValue() {
		return value;
	}
	@Override 
	public TypeEntrable getTypeEntrable() {
		return TypeEntrable.Point;
	}

	@Override
	public boolean conformerA(Object... input) {
		switch (input.length) {
	case 1 :if (input[0] instanceof Point)
				value = ((Point) input[0]).value;
			else if (input[0] instanceof Point3)
				value = (Point3) input[0];
			else return false;
			return true;
		default : return false;
		}
	}

}
