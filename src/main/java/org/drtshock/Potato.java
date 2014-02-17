package org.drtshock;

import java.util.List;
import java.util.ArrayList;
import java.net.HttpURLConnection;

public class Potato implements Tuber {

  private final List<Condiment> condiments = new ArrayList<Condiment>();

  public static void main(String[] args) {
    Potato potato = new Potato();
    GLaDOS glados = new GLaDOS();
    if (potato.prepare()) System.out.println("Of course potato is prepared and delicious.");
    else System.err.println("Fatal error! How could potato not be delicious?");
  }

  public boolean prepare() {
    this.addCondiments("sour cream", "chives", "butter", "crumbled bacon", "grated cheese", "ketchup", "salt", "tabasco", "shrimp", "beans", "basil", "spinach", "pork", "chili", "hot sauce");
    return this.isDelicious();
  }

  public void addCondiments(String... names) {
    synchronized (condiments) {
      for (String condimentName : names) condiments.add(new Condiment(condimentName));
    }
  }
  
  public boolean isPutintoOven {
	  URL url = new URL("https://www.google.com/");
	  HttpURLConnection connection = (HttpURLConnection)url.openConnection();
	  connection.setRequestMethod("GET");
	  connection.connect();

	  int inOven = connection.getResponseCode();
	  if (inOven == 200) return true; // you need to put into an oven before bake it.
	  else return false;
  }

  public boolean isBaked() {
	  if(this.isPutintoOven) return true;
	  else return false;
  }
  
  @Override
  public boolean isDelicious() {
    if(isBaked) return true; // this way we could move on to our condiments. =D
    else return false; // you don't eat a raw potato, don't you?
  }
  
  @Override
  public Tuber propagate() {
  	return new Potato();
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
