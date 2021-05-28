package ar.edu.unlam.tallerweb1.persistencia;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Escuela;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import javax.transaction.Transactional;
import static org.assertj.core.api.Assertions.*;

public class EscuelaPersistenceTest extends SpringTest {

    @Test @Transactional @Rollback
    public void poderGuardarUnaEscuela(){
        Escuela escu = givenExisteUnaEscuela();

        Long id = whenGuardoLaEscuela(escu);

        thenLaPuedoBuscarPorSuId(id);
    }

    private Escuela givenExisteUnaEscuela() {
        Escuela escuela = new Escuela();
        escuela.setNombre("General San Martin");
        return escuela;
    }

    private Long whenGuardoLaEscuela(Escuela escu) {
        session().save(escu);
        return escu.getId();
    }

    private void thenLaPuedoBuscarPorSuId(Long id) {
        Escuela buscada = session().get(Escuela.class, id);
        assertThat(buscada).isNotNull();
    }
}
