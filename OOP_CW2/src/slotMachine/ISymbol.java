package slotMachine;

import javax.swing.ImageIcon;

public interface ISymbol {
	// Setter method for the image of the Symbol object
	public abstract void setImage(String url);

	// Returns the image of the Symbol object
	public abstract ImageIcon getImage();

	// Setter method for the value of the Symbol object
	public abstract void setValue(int v);

	// Returns the value of the Symbol object
	public abstract int getValue();

}
