package ar.edu.unlam.tallerweb1.controladores;

public class DatosCambioClave {

    private String email;
    private String claveNueva;
    private String repiteClaveNueva;
    private String claveActual;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClaveNueva() {
        return claveNueva;
    }

    public void setClaveNueva(String claveNueva) {
        this.claveNueva = claveNueva;
    }

    public String getRepiteClaveNueva() {
        return repiteClaveNueva;
    }

    public void setRepiteClaveNueva(String repiteClaveNueva) {
        this.repiteClaveNueva = repiteClaveNueva;
    }

    public String getClaveActual() {
        return claveActual;
    }

    public void setClaveActual(String claveActual) {
        this.claveActual = claveActual;
    }
}
