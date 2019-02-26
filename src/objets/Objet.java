package objets;

import java.io.Serializable;

import objets.scene.Scene;

public abstract class Objet implements Serializable{
	  /**
	   * 
	   */
	  private static final long serialVersionUID = -8094289181451338202L;
	  private String name;
	  
	  public void setName(String s) {
	   name=s;
	  }
	 
	  public String getName() {
	    if (name==null)
	      return toString();
	    else return name;
	  }

	  
	}