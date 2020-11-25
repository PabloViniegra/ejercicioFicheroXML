import java.io.Serializable;

public class Direccion implements Serializable {
    private String calle;
    private String provincia;

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return "calle='" + calle + '\'' +
                ", provincia='" + provincia + '\'' +
                '}';
    }
}
