package cocina;

// External imports
import java.util.LinkedList;
import java.util.Queue;

// Internal imports
import platos.Plato;

public class Cocina {

    private Queue<Plato> colaCocina = null;

    public  Cocina(){
        this.setColaCocina(new LinkedList<Plato>());
    }

    // Methods    
    public synchronized Plato getNextPlato(){
        Queue<Plato> queueCocina = this.getColaCocina();
        Plato plato = queueCocina.poll();
        return plato;
    }
    
// Getters and setters

    private Queue<Plato> getColaCocina() {
        return this.colaCocina;
    }

    private void setColaCocina(Queue<Plato> p_cola_cocina) {
        this.colaCocina = p_cola_cocina;
    }

}
