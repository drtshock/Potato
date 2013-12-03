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

  private class Condiment {
    private final String name;

    public Condiment(String name) {
      this.name = name;
    }

    public String getName() {
      return this.name;
    }
  }

  private static class GLaDOS extends Potato {
      public GLaDOS() {
          System.out.println("Oh hi, how are you holding up? BECAUSE I'M A POTATO... clap clap clap... oh good, my slow clap processor made it into this thing, at least we have that.");
      }

      @Override
      public boolean isDelicious() {
        return false; // robots are not delicious
      }
  }
  
  private static class AdirondackBluePotato extends Potato {
    private AdirondackBluePotato() {
      setColors(Color.BLUE, Color.BLUE);
    }
  }
  
  public static class AdirondackRedPotato extends Potato{
    public AdirondackRedPotato() {
      setColors(Color.RED, Color.RED);
    }
  }
  
  public static class AmandinePotato extends Potato{
    public AmandinePotato() {
      setColors(Color.YELLOW, Color.WHITE);
    }
  }
  
  public static class RangerRussetPotato extends Potato{
    public RangerRussetPotato() {
      setColors(new Color(100, 75, 40), //BROWN
              Color.WHITE);
    }
  }
  
  public static class RussetBurbankPotato extends Potato{
    public RussetBurbankPotato() {
      setColors(Color.YELLOW, Color.WHITE);
    }
  }
}
