package ar.edu.utn.frc.backend.services;

import ar.edu.utn.frc.backend.models.Album;

import java.util.List;
import java.util.Optional;

public interface AlbumService {

    List<Album> findAll();

    Optional<Album> findById(final Integer id);

    Album create(String title, Integer artistId);
    void delete(final Integer id);
    void update(Integer id, String title, Integer artistId);

}
