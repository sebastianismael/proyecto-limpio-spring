package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.controladores.DatosRegistro;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("servicioUsuarios")
@Transactional
public class ServicioUsuarioDefault implements ServicioUsuario {

    private RepositorioUsuario repositorioUsuario;

    @Autowired
    public ServicioUsuarioDefault(RepositorioUsuario repositorioUsuario){
        this.repositorioUsuario = repositorioUsuario;
    }

    public Usuario registrar(DatosRegistro datos) {
        if(!datos.getPassword().equals(datos.getRepitePassword())){
            throw new ClavesNoCoinciden();
        }
        if(repositorioUsuario.buscar(datos.getEmail()) != null){
            throw new UsuarioExistente();
        }
        Usuario nuevoUsuario = new Usuario(datos.getEmail(), datos.getRepitePassword());
        repositorioUsuario.guardar(nuevoUsuario);
        return nuevoUsuario;
    }

    @Override
    public void cambiarClave(String email, String claveNueva, String repiteClaveNueva, String claveActual) {
        if(!claveNueva.equals(repiteClaveNueva))
            throw new ClavesNoCoinciden();
        if(claveNueva.equals(claveActual))
            throw new ClaveNuevaIgualActual();

        final Usuario usuario = repositorioUsuario.buscar(email);
        usuario.setPassword(claveNueva);
        repositorioUsuario.modificar(usuario);
    }
}
