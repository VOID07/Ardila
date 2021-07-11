package personal;

import misc.Cola;
import platos.Plato;

public class Chef extends Empleado {
    
    private Cola colaCocina = null;
    private Cola mesa = null;

    public Chef(Cola p_cocina, int p_chefID, Cola p_mesa) {
        super("Chef " + p_chefID);
        setColaCocina(p_cocina);
        setMesa(p_mesa);
    }

    // Getters and setters
    
    private Cola getColaCocina() {
        return this.colaCocina;
    }

    private void setColaCocina(Cola p_cola_cocina) {
        this.colaCocina = p_cola_cocina;
    }

    private Cola getMesa() {
        return this.mesa;
    }

    private void setMesa(Cola p_mesa) {
        this.mesa = p_mesa;
    }

    // Overrided methods
    @Override
    public void manejarOrden(Plato p_orden) {
        System.out.println(this.getRol() + " está preparando la orden: " + p_orden.getNumorden());
        try {
            Thread.sleep(p_orden.getTiempo());
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("Error preparando la orden " + p_orden.getNumorden() + ". " +this.getRol() + " se encuentra reintentando");
            this.manejarOrden(p_orden);
        }
        
        while(this.getMesa().getSize() >= 5){
            System.out.println("La mesa de salida se encuentra llena, "+ this.getRol() + " esperando para colocar la orden " + p_orden.getNumorden());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("La orden: " + p_orden.getNumorden() + " fue completada por " + this.getRol() + ". Esperando al próximo mesero disponible para servir");

        this.getMesa().addNewPlato(p_orden);
    }

    @Override
    public void run() {
        Plato tmpPlato = null;
        while(true){
            tmpPlato =  this.getColaCocina().getNextPlato();

            if (tmpPlato == null){
                // System.out.println("No hay platos");
                try {
                    Thread.sleep(1000);
                    continue;
                } catch (InterruptedException e) {
                    continue;
                }
            }
            this.manejarOrden(tmpPlato);
        }
    }
    
}
