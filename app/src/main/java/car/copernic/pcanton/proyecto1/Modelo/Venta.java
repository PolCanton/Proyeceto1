package car.copernic.pcanton.proyecto1.Modelo;

public class Venta {

    String  direccion,idproducto,correo;

    public Venta() {
    }

    public Venta(String direccion, String idproducto, String correo) {
        this.direccion = direccion;
        this.idproducto = idproducto;
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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
