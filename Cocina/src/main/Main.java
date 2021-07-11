import misc.Cola;
import personal.Cajero;
import personal.Chef;
import personal.Mesero;

public class Main {
  public static void main(String[] args) {
    
    Cola cocina = new Cola();
    Cola mesa = new Cola();
    System.out.println(cocina);
    Cajero cajero = new Cajero(cocina);
    cajero.start();
    Chef[] listaChefs = new Chef[6];
    Mesero[] listaMeseros = new Mesero[10];

    for (int i = 0; i < 6; i++) {
      listaChefs[i] = new Chef(cocina, i, mesa);
      listaChefs[i].start();
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    for (int i = 0; i < 6; i++) {
      listaMeseros[i] = new Mesero(mesa, i);
      listaMeseros[i].start();
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    try {
      cajero.join();
    } catch (InterruptedException e) {
      System.out.println("Execution failed");
      e.printStackTrace();
    }
    System.out.println("Execution completed");
  }
}