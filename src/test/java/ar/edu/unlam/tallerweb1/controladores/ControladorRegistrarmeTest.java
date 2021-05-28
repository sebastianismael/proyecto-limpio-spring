package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ClaveNuevaIgualActual;
import ar.edu.unlam.tallerweb1.servicios.ClavesNoCoinciden;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
import ar.edu.unlam.tallerweb1.servicios.UsuarioExistente;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;


public class ControladorRegistrarmeTest {
    private final String LOGIN_VIEW = "redirect:/login";
    private final Usuario USUARIO = usuario("hector@mail.com", "clave1234");
    private ModelAndView mav;
    private ControladorRegistrarme controladorRegistro;
    private ServicioUsuario servicioUsuario;

    @Before
    public void init(){
        servicioUsuario = mock(ServicioUsuario.class);
        controladorRegistro = new ControladorRegistrarme(servicioUsuario);
    }

    @Test
    public void siElUsuarioNoExisteDeberiaPoderRegistrarse(){
        givenUsuarioNoRegistrado(USUARIO);

        whenRegistroElUsuario(USUARIO.getEmail(), USUARIO.getPassword(), USUARIO.getPassword());

        thenElUsuarioSeRegistraConExito();
    }

    @Test
    public void siElUsuarioYaExisteNoDeberiaPoderRegistrarse(){
        givenUsuarioRegistrado(USUARIO);

        whenRegistroElUsuario(USUARIO.getEmail(), USUARIO.getPassword(), USUARIO.getPassword());

        thenRegistraFallaPor("usuario ya existe");
    }

    @Test
    public void siLaClavesNoCoincidenNoDeberiaPoderRegistrarse(){
        givenRegistrarUsuarioFallaPorClavesNoCoinciden();

        whenRegistroElUsuario(USUARIO.getEmail(), USUARIO.getPassword(), USUARIO.getPassword() + "356829");

        thenRegistraFallaPor("claves no coinciden");
    }

    @Test
    public void sePuedeCambiarLaClaveAUnaClaveNueva(){
        String claveActual = "clave123";
        String claveNueva = "clave22";

        whenActualiaClave(USUARIO.getEmail(), claveNueva, claveNueva, claveActual);

        thenLaClaveSeCambiaConExito();
    }

    @Test
    public void SiLaClaveNuevaEsIgualALaActualElCambioFalla(){
        String claveActual = "clave123";
        String claveNueva = "clave123";

        givenClavesSonIguales(claveActual, claveNueva);

        whenActualiaClave(USUARIO.getEmail(), claveNueva, claveNueva, claveActual);

        thenElCambioDeClaveFallaConError("La clave nueva no puede ser igual a la actual");
    }

    @Test
    public void SiLasClavesNuevasNoCOincidenElCambioFalla(){
        String claveActual = "clave123";
        String claveNueva = "clave123";

        givenClaveNuevaNoCoincide(claveActual, claveNueva);

        whenActualiaClave(USUARIO.getEmail(), claveNueva, claveNueva+"t", claveActual);

        thenElCambioDeClaveFallaConError("Las claves no coinciden");
    }

    private void givenClaveNuevaNoCoincide(String claveActual, String claveNueva) {
        doThrow(ClavesNoCoinciden.class)
                .when(servicioUsuario)
                .cambiarClave(USUARIO.getEmail(), claveNueva, claveNueva +"t", claveActual);
    }

    private void givenClavesSonIguales(String claveActual, String claveNueva) {
        doThrow(ClaveNuevaIgualActual.class)
                .when(servicioUsuario)
                .cambiarClave(USUARIO.getEmail(), claveNueva, claveNueva, claveActual);
    }

    private void givenRegistrarUsuarioFallaPorClavesNoCoinciden() {
        when(servicioUsuario.registrar(any())).thenThrow(ClavesNoCoinciden.class);
    }

    private void givenUsuarioRegistrado(Usuario usuario) {
        when(servicioUsuario.registrar(any())).thenThrow(UsuarioExistente.class);
    }

    private void givenUsuarioNoRegistrado(Usuario usuario) {
        DatosRegistro datosRegistro = new DatosRegistro();
        datosRegistro.setEmail(usuario.getEmail());
        datosRegistro.setPassword(usuario.getPassword());
        datosRegistro.setRepitePassword(usuario.getPassword());
        when(servicioUsuario.registrar(datosRegistro)).thenReturn(new Usuario());
    }

    private void whenActualiaClave(String email, String claveNueva, String repiteClave, String claveActual) {
        DatosCambioClave datos = new DatosCambioClave();
        datos.setClaveActual(claveActual);
        datos.setClaveNueva(claveNueva);
        datos.setRepiteClaveNueva(repiteClave);
        datos.setEmail(email);
        mav = controladorRegistro.cambiarClave(datos);
    }

    private void whenRegistroElUsuario(String mail, String clave, String confirmaClave) {
        DatosRegistro datosRegistro = new DatosRegistro();
        datosRegistro.setEmail(mail);
        datosRegistro.setPassword(clave);
        datosRegistro.setRepitePassword(confirmaClave);
        mav = controladorRegistro.registrar(datosRegistro);
    }

    private void thenElUsuarioSeRegistraConExito() {
        assertThat(mav.getModel().get("registrado")).isEqualTo(Boolean.TRUE);
        assertThat(mav.getViewName()).isEqualTo(LOGIN_VIEW);
    }

    private void thenRegistraFallaPor(String motivo) {
        assertThat(mav.getModel().get("registrado")).isEqualTo(Boolean.FALSE);
        assertThat(mav.getModel().get("error")).isEqualTo(motivo);
        assertThat(mav.getViewName()).isEqualTo("registro");
    }

    private void thenLaClaveSeCambiaConExito() {
        assertThat(mav.getViewName()).isEqualTo(LOGIN_VIEW);
    }

    private void thenElCambioDeClaveFallaConError(String mensaje) {
        assertThat(mav.getModel().get("error")).isEqualTo(mensaje);
        assertThat(mav.getViewName()).isEqualTo("cambio-clave");
    }

    private Usuario usuario(String mail, String clave) {
        Usuario usuario = new Usuario();
        usuario.setEmail(mail);
        usuario.setPassword(clave);
        return usuario;
    }
}
