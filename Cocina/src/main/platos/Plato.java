public class Plato{
    private String[] nombrePlatos = null;
    private int[] tiempoPlatos = null;
    private String nombre = null;
    private int tiempo = 0;

    public Platos(int p_opcion){
        this.setNombrePlatos();
        this.setTiempoPlatos();
        this.setPlato(p_opcion);
    }

    private void setNombrePlatos(){
        this.nombrePlatos = new String[]{"Hamburguesa","Chalupas", "Papas fritas", 
                                        "Perros calientes", "Cazados"};
    }
    private String getNombrePlatos(int p_pos){
        return this.nombrePlatos[p_pos];
    }

    private void setTiempoPlatos(){
        this.tiempoPlatos = new int[]{3000, 5000, 2000, 4000, 6000};
    }

    private int getTiempoPlatos(int p_pos){
        return this.tiempoPlatos[p_pos];
    }

    private void setPlato(int p_opcion){
        this.nombre = getNombrePlatos(p_opcion);
        this.tiempo = getTiempoPlatos(p_opcion);
    }

    public String getNombre(){
        return this.nombre;
    }

    public int getTiempo(){
        return this.tiempo;
    }
}