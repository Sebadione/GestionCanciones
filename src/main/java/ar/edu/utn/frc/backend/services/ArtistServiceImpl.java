package ar.edu.utn.frc.backend.services;

import ar.edu.utn.frc.backend.models.Artist;
import ar.edu.utn.frc.backend.repositories.AlbumRepository;
import ar.edu.utn.frc.backend.repositories.ArtistRepository;
import ar.edu.utn.frc.backend.repositories.IdentifierRepository;
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
public class ArtistServiceImpl implements ArtistService{

    ArtistRepository artistRepository;
    IdentifierRepository identifierRepository;
    AlbumRepository albumRepository;

    @Override
    public List<Artist> findAll() {
        return artistRepository.findAll();
    }

    @Override
    public Optional<Artist> findById(Integer id) {
        return artistRepository.findById(id);
    }

    @Override
    @Transactional
    public Artist create(String name) {
        val artistId = identifierRepository.nextValue(Artist.TABLE_NAME);
        return artistRepository.save(new Artist(artistId, name));
    }

    @Override
    @Transactional
    public void delete(Integer id) {
//        try {
//            albumRepository.deleteAllByArtistId(id);
//            artistRepository.deleteById(id);
//        } catch (Exception e) {
//            throw new IllegalArgumentException("Artist not found");
//        }
        val artist = artistRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Artist not found"));
        albumRepository.deleteAllByArtist(artist);
        artistRepository.delete(artist);

    }

    @Override
    @Transactional
    public void update(Integer id, String name) {
        val artist = artistRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Artist not found"));
        artist.update(name);
        artistRepository.save(artist);
    }
}
