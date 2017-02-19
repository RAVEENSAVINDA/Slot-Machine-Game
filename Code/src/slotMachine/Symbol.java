package slotMachine;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Symbol implements ISymbol {
	/*Declaring the private variables to hold the value and the image of the Symbol Object */
	private int value;
	private ImageIcon image;
	
	/* Declaring the constructor for the type Symbol*/
	public Symbol(int value,String url){
		this.value=value;
		this.setImage(url);
	}
	/*Setter method for the image of the Symbol object*/
	@Override
	public void setImage(String url){
		ImageIcon imgObj = new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(100,100, Image.SCALE_DEFAULT));
		this.image=imgObj;
	}
	/*Returns the image of the Symbol object*/
	@Override
	public ImageIcon getImage(){
		return this.image;
	}
	/*Setter method for the value of the Symbol object*/
	@Override
	public void setValue(int value){
		this.value=value;
	}
	/*Returns the value of the Symbol object*/
	@Override
	public int getValue(){
		return this.value;
	}
	
	/*Compares two Symbol objects and returns true if both objects have same Value. Returns false if not */
	public boolean compareSymbols(Symbol obj1,Symbol obj2){
		return obj1.value==obj2.value;	
	}
}
