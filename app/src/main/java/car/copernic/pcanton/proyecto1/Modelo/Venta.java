package car.copernic.pcanton.proyecto1.Modelo;

public class Venta {

    String  direccion,idproducto;

    public Venta() {
    }

    public Venta(String direcciom, String idproducto) {
        this.direccion = direcciom;
        this.idproducto = idproducto;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getIdproducto() {
        return idproducto;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setIdproducto(String idproducto) {
        this.idproducto = idproducto;
    }
}
