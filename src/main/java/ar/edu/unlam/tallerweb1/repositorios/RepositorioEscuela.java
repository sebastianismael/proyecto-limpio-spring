package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Escuela;

import java.util.List;

public interface RepositorioEscuela {
    void guardar(Escuela escuela);
    Escuela buscarPor(Long id);
    List<Escuela> buscarPor(String nombre);
}
