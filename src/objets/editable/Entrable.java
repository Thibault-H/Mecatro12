package objets.editable;

public interface Entrable {
	
	/**Renvoie le type de l'entrable
	 * 
	 * @return
	 */
	public TypeEntrable getTypeEntrable();
	
	/**Modifie l'instance de sorte à ce qu'elle se conforme à l'input.
	 * L'instance vit ensuite indépendemment des objets passés en input.
	 * 
	 * @param input
	 * @return
	 */
	public boolean conformerA(Object... input);
	
	
/*	*//**Verifie l'égalité des types des deux Entrables
	 * 
	 * @param input
	 * @return
	 *//*
	public default boolean isConformableA(Entrable input) {
		return getTypeEntrable().equals(input.getTypeEntrable());
	}*/
}
