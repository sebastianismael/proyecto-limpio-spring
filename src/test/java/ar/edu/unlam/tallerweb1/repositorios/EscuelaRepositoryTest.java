package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Escuela;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class EscuelaRepositoryTest extends SpringTest {

    @Autowired
    private RepositorioEscuela repositorio;

    @Test @Transactional @Rollback
    public void guardarUnaEscuelaDeberiaPersistirla() {
        Escuela escuela = new Escuela();
        escuela.setNombre("General San Martin");
        repositorio.guardar(escuela);

        Escuela buscada = repositorio.buscarPor(escuela.getId());
        assertThat(buscada).isNotNull();
    }

    @Ignore
    @Test @Transactional @Rollback
    public void buscarPorNombre() {
        Escuela escuela = new Escuela();
        escuela.setNombre("General San Martin");
        repositorio.guardar(escuela);

        Escuela escuela2 = new Escuela();
        escuela2.setNombre("Cabo Saldanio");
        repositorio.guardar(escuela2);

        List<Escuela> buscadas = repositorio.buscarPor(escuela.getNombre());
        assertThat(buscadas).hasSize(1);
    }

}
