package misc;

// External imports
import java.util.LinkedList;
import java.util.Queue;

// Internal imports
import platos.Plato;

public class Cola {

    private Queue<Plato> cola = null;

    public  Cola(){
        this.setCola(new LinkedList<Plato>());
    }

    // Methods    
    public synchronized Plato getNextPlato(){
        Queue<Plato> queueCocina = this.getCola();
        Plato plato = queueCocina.poll();
        return plato;
    }

    public synchronized void addNewPlato(Plato p_plato){
        Queue<Plato> queueCocina = this.getCola();
        queueCocina.add(p_plato);
    }
    
// Getters and setters
    public int getSize(){
        return this.getCola().size();
    }

    private Queue<Plato> getCola() {
        return this.cola;
    }

    private void setCola(Queue<Plato> p_cola) {
        this.cola= p_cola;
    }

}
