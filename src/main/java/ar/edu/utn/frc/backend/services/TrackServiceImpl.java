package ar.edu.utn.frc.backend.services;

import ar.edu.utn.frc.backend.models.MediaType;
import ar.edu.utn.frc.backend.models.Track;
import ar.edu.utn.frc.backend.repositories.AlbumRepository;
import ar.edu.utn.frc.backend.repositories.IdentifierRepository;
import ar.edu.utn.frc.backend.repositories.TrackRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class TrackServiceImpl implements TrackService {

    TrackRepository trackRepository;
    IdentifierRepository identifierRepository;
    AlbumService albumService;
    MediaTypeService mediaTypeService;
    GenreService genreService;

    @Override
    public List<Track> findAll() {
        return trackRepository.findAll();
    }

    @Override
    public Optional<Track> findById(Integer id) {
        return trackRepository.findById(id);
    }

    @Override
    @Transactional
    public Track create(String name, int albumId, int mediaTypeId, int genreId,
                        String composer, int milliseconds, int bytes, double unitPrice) {
        val album = albumService.findById(albumId)
                .orElseThrow(() -> new IllegalArgumentException("Album not found"));
        val mediaType = mediaTypeService.findById(mediaTypeId)
                .orElseThrow(() -> new IllegalArgumentException("Media Type not found"));
        val genre = genreService.findById(genreId)
                .orElseThrow(() -> new IllegalArgumentException("Genre not found"));
        val trackId = identifierRepository.nextValue(Track.TABLE_NAME);
        val track = new Track(trackId, name, album, mediaType, genre, composer,
                milliseconds, bytes, unitPrice);
        return trackRepository.save(track);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        try {
            trackRepository.deleteById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("Track not found");
        }
    }

    @Override
    @Transactional
    public void update(Integer id, String name, int albumId, int mediaTypeId, int genreId,
                       String composer, int milliseconds, int bytes, double unitPrice) {
        val track = trackRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Track not found"));
        val album = albumService.findById(albumId)
                .orElseThrow(() -> new IllegalArgumentException("Album not found"));
        val mediaType = mediaTypeService.findById(mediaTypeId)
                .orElseThrow(() -> new IllegalArgumentException("Media Type not found"));
        val genre = genreService.findById(genreId)
                .orElseThrow(() -> new IllegalArgumentException("Genre not found"));

        track.update(name, album, mediaType, genre, composer, milliseconds,
                bytes, unitPrice);
        trackRepository.save(track);
    }
}
