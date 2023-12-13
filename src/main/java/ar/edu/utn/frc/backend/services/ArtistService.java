package ar.edu.utn.frc.backend.services;

import ar.edu.utn.frc.backend.models.Album;
import ar.edu.utn.frc.backend.models.Artist;

import java.util.List;
import java.util.Optional;

public interface ArtistService {

    List<Artist> findAll();

    Optional<Artist> findById(final Integer id);

    Artist create(String name);

    void delete(final Integer id);

    void update(Integer id, String name);
}
