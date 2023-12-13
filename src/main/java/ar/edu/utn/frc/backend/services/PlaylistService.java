package ar.edu.utn.frc.backend.services;

import ar.edu.utn.frc.backend.models.Album;
import ar.edu.utn.frc.backend.models.Playlist;

import java.util.List;
import java.util.Optional;

public interface PlaylistService {

    List<Playlist> findAll();

    Optional<Playlist> findById(final Integer id);

    Playlist create(String name);
    void delete(final Integer id);
    void update(Integer id, String name);

    void addTrack(Integer playlistId, Integer trackId);

    void deleteTrack(Integer playlistId, Integer trackId);
}
