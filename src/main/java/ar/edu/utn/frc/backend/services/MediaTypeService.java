package ar.edu.utn.frc.backend.services;

import ar.edu.utn.frc.backend.models.Album;
import ar.edu.utn.frc.backend.models.MediaType;

import java.util.List;
import java.util.Optional;

public interface MediaTypeService {

    List<MediaType> findAll();

    Optional<MediaType> findById(final Integer id);

    MediaType create(String name);
    void delete(final Integer id);
    void update(Integer id, String name);
}
