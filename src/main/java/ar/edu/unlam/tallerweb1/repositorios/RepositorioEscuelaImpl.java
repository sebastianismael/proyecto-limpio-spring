package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Escuela;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioEscuelaImpl implements RepositorioEscuela{

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioEscuelaImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void guardar(Escuela escuela) {
        sessionFactory.getCurrentSession().save(escuela);
    }

    @Override
    public Escuela buscarPor(Long id) {
        return sessionFactory.getCurrentSession().get(Escuela.class, id);
    }

    @Override
    public List<Escuela> buscarPor(String nombre) {
        return null;
    }
}
