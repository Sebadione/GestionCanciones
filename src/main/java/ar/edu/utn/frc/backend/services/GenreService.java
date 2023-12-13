package ar.edu.utn.frc.backend.services;

import ar.edu.utn.frc.backend.models.Album;
import ar.edu.utn.frc.backend.models.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {

    List<Genre> findAll();

    Optional<Genre> findById(final Integer id);

    Genre create(String name);
    void delete(final Integer id);
    void update(Integer id, String name);
}
