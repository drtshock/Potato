import java.util.List;
import java.util.ArrayList;

public class Potato {

  private final List<Condiment> condiments = new ArrayList<Condiment>();

  public Potato () {
    System.out.println("Potato in the house!);
  }

  public static void main(String[] args) {
    Potato potato = new Potato();
    Glados glados = new Glados();
    if (potato.prepare()) System.out.println("Of course potato is prepared and delicious.");
    else System.out.println("Fatal error! How could potato not be delicious?");
  }

  public boolean prepare() {
    this.addCondiment("sour cream");
    this.addCondiment("chives");
    this.addCondiment("butter");
    return this.isDelicious();
  }

  public void addCondiment(String name) {
    synchronized (condiments) {
      condiments.add(new Condiment(name));
    }
  }

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

  private static class Glados extends Potato {
      public Glados() {
          System.out.println("Oh hi, how are you holding up? BECAUSE I'M A POTATO... clap clap clap... oh good, my slow clap processor made it into this thing, at least we have that.");
      }
  }
}
