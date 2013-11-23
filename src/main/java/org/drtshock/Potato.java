package org.drtshock;

import java.util.List;
import java.util.ArrayList;

public class Potato implements Tuber {

  //Just adding a comment for experimenting with Git...
  
  private final List<Condiment> condiments = new ArrayList<Condiment>();

  public static void main(String[] args) {
    Potato potato = new Potato();
    GLaDOS glados = new GLaDOS();
    if (potato.prepare()) System.out.println("Of course potato is prepared and delicious.");
    else System.err.println("Fatal error! How could potato not be delicious?");
  }

  public boolean prepare() {
    this.addCondiments("sour cream", "chives", "butter", "crumbled bacon", "grated cheese", "broccoli", "ketchup");
    return this.isDelicious();
  }

  public void addCondiments(String... names) {
    synchronized (condiments) {
      for (String condimentName : names) condiments.add(new Condiment(condimentName));
    }
  }

  @Override
  public boolean isDelicious() {
    return true; // obviously, potatos are always delicious
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
}
