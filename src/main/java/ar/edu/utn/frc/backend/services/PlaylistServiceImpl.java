package ar.edu.utn.frc.backend.services;

import ar.edu.utn.frc.backend.models.Playlist;
import ar.edu.utn.frc.backend.models.Track;
import ar.edu.utn.frc.backend.repositories.IdentifierRepository;
import ar.edu.utn.frc.backend.repositories.PlaylistRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistService{

    PlaylistRepository playlistRepository;
    IdentifierRepository identifierRepository;
    TrackService trackService;

    @Override
    public List<Playlist> findAll() {
        return playlistRepository.findAll();
    }

    @Override
    public Optional<Playlist> findById(Integer id) {
        return playlistRepository.findById(id);
    }

    @Override
    @Transactional
    public Playlist create(String name) {
        val playlistId = identifierRepository.nextValue(Playlist.TABLE_NAME);
        val playlist = new Playlist(playlistId, name);
        return playlistRepository.save(playlist);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        try {
            playlistRepository.deleteById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("Playlist not found");
        }

    }

    @Override
    @Transactional
    public void update(Integer id, String name) {
        val playlist = playlistRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Playlist not found"));

        playlist.update(name);

        playlistRepository.save(playlist);
    }

    @Override
    public void addTrack(Integer playlistId, Integer trackId) {
        val playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new IllegalArgumentException("Playlist not found"));
        val track = trackService.findById(trackId)
                .orElseThrow(() -> new IllegalArgumentException("Track not found"));
        playlist.addTrack(track);
        playlistRepository.save(playlist);
    }

    @Override
    public void deleteTrack(Integer playlistId, Integer trackId) {
        val playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new IllegalArgumentException("Playlist not found"));
        val track = trackService.findById(trackId)
                .orElseThrow(() -> new IllegalArgumentException("Track not found"));
        System.out.println(playlist.getName());
        System.out.println(track.getName());
        playlist.deleteTrack(track);
        playlistRepository.save(playlist);
    }

}
