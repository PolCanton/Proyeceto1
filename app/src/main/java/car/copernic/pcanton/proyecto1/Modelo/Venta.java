package car.copernic.pcanton.proyecto1.Modelo;

public class Venta {

    String direccion, nombre,comprador,foto;

    public Venta() {
    }

    public Venta(String direccion, String nombre, String comprador, String foto) {
        this.direccion = direccion;
        this.nombre = nombre;
        this.comprador = comprador;
        this.foto = foto;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }
}
