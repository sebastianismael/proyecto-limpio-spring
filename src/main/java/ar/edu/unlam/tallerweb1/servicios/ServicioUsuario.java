package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.controladores.DatosRegistro;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioUsuario {
    Usuario registrar(DatosRegistro datos);

    void cambiarClave(String email, String claveNueva, String repiteClaveNueva, String claveActual);
}
