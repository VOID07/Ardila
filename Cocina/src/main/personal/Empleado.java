public class Empleado extends Thread {

    private String rol = null;

    // Constructor modificado
    public Empleado(String p_rol){
        this.setRol(String p_rol);
    }

    public manejarOrden(Orden p_orden);

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