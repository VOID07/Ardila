package personal;

import misc.Cola;
import platos.Plato;

public class Mesero extends Empleado {

    private Cola mesa = null;

    public Mesero(Cola p_mesa, int p_mesID) {
        super("Mesero " + p_mesID);
        setMesa(p_mesa);

    }

    // Getters and setters
    private Cola getMesa() {
        return this.mesa;
    }

    private void setMesa(Cola p_mesa) {
        this.mesa = p_mesa;
    }

    // Métodos heredados
    @Override
    public void manejarOrden(Plato p_orden) {
        int tiempoEntrega = ((int)(Math.random()*((6-2)+1))+2)*1000;
        System.out.println(this.getRol() + " ha recibido la orden " + p_orden.getNumorden() + " se entregará en aproximadamente en " + tiempoEntrega + " ms");
        try {
            Thread.sleep(tiempoEntrega);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.getRol()  + " ha entregado satisfactoriamente la orden " + p_orden.getNumorden());
    }

    @Override
    public void run() {
        Plato tmpPlato = null;
        while (true) {
            tmpPlato = this.getMesa().getNextPlato();

            if (tmpPlato == null) {
                // System.out.println("No hay platos");
                try {
                    Thread.sleep(500);
                    continue;
                } catch (InterruptedException e) {
                    continue;
                }
            }
            this.manejarOrden(tmpPlato);
        }
    }

}
