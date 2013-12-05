package org.drtshock;

import java.util.List;
import java.util.ArrayList;
import java.awt.Color;

public class Potato implements Tuber {
  
  private Color skinColor;
  private Color fleshColor;
  private final List<Condiment> condiments = new ArrayList<Condiment>();
  
  public static void main(String[] args) {
    AmandinePotato amadinePotato = new AmandinePotato();
    AdirondackBluePotato adirondackBluePotato = new AdirondackBluePotato();
    GLaDOS glados = new GLaDOS();
    
    if (amadinePotato.prepare()) System.out.println("Of course potato is prepared and delicious.");
    else System.err.println("Fatal error! How could potato not be delicious?");
  }

  public boolean prepare() {
    this.addCondiments("sour cream", "chives", "butter", "crumbled bacon", "grated cheese", "ketchup");
    return this.isDelicious();
  }

  public void addCondiments(String... names) {
    synchronized (condiments) {
      for (String condimentName : names) condiments.add(new Condiment(condimentName));
    }
  }
  
  public void setColors(Color newSkinColor, Color newFleshColor) {
    skinColor = newSkinColor;
    fleshColor = newFleshColor;
  }

  @Override
  public boolean isDelicious() {
    return true; // obviously, potatoes are always delicious

  }
  
  public String tuberType() {
      return "stem tuber"; // All potatoes are stem tubers
  }

  private class Condiment {
    private final String name;

    public Condiment(String name) {
      this.name = name;
    }

    public String getName() {
      return this.name;
    }
  }
}
