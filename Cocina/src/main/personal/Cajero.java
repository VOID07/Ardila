package personal;

import java.util.Scanner;

import misc.Cola;
import platos.Plato;

public class Cajero extends Empleado{
    
    private Cola colaCocina = null;

    public Cajero(Cola p_cocina) {
        super("Cajero");
        this.colaCocina = p_cocina;
        setColaCocina(p_cocina);
        System.out.println(p_cocina==getColaCocina());
    }

    // Getters and setters
    
    private Cola getColaCocina() {
        return this.colaCocina;
    }

    private void setColaCocina(Cola p_cola_cocina) {
        this.colaCocina = p_cola_cocina;
    }

    // Overrided methods

    @Override
    public void manejarOrden(Plato p_orden){
        getColaCocina().addNewPlato(p_orden);
    }

    @Override
    public void run(){
        String[] platos = new String[]{"Hamburguesa","Chalupas", "Papas fritas", 
        "Perros calientes", "Cazados"};
        Scanner scanner = new Scanner(System.in);
        int numOrden = 1;
        int option = -1;
        Plato newOrden = null;
        while(true){
            System.out.println("Por favor introduzca el número de plato que desea ordenar");
            System.out.println("Menú:");
            for (int i = 0; i < platos.length; i++){
                System.out.println("\t" + i + ": "+ platos[i]);
            }
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                option =-1;
            }

            if (option < 0 || option >= platos.length){
                System.out.println("Opción inválida, intente nuevamente");
                continue;
            }
            newOrden = new Plato(option, numOrden);
            this.manejarOrden(newOrden);
            System.out.println("Su plato está siendo preparado, número de orden: " + numOrden);
            numOrden++;
            newOrden = null;
        }
    }
    
}
