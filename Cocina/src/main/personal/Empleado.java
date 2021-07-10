package personal;

import platos.Plato;

public class Empleado extends Thread {

    private String rol = null;

    // Constructor modificado
    public Empleado(String p_rol){
        this.setRol(p_rol);
    }

    public void manejarOrden(Plato p_orden){};

    public void run(){
       System.out.println("MyThread running");
    }

    private void setRol(String p_rol){
        this.rol = p_rol;
    }

    public String getRol(){
        return this.rol;
    }
  }