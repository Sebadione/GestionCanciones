package ar.edu.utn.frc.backend.services;

import ar.edu.utn.frc.backend.models.Album;
import ar.edu.utn.frc.backend.repositories.AlbumRepository;
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
public class AlbumServiceImpl implements AlbumService {

    AlbumRepository albumRepository;
    ArtistService artistService;
    IdentifierRepository identifierRepository;

    @Override
    public List<Album> findAll() {
        return albumRepository.findAll();
    }

    @Override
    public Optional<Album> findById(Integer id) {
        return albumRepository.findById(id);
    }

    @Override
    @Transactional
    public Album create(String title, Integer artistId) {

        val artist = artistService.findById(artistId).orElseThrow(() -> new IllegalArgumentException("Artist not found"));
        val albumId = identifierRepository.nextValue(Album.TABLE_NAME);
        val album =new Album(albumId, title, artist);
        return albumRepository.save(album);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        try {
            albumRepository.deleteById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("Album not found");
        }

    }

    @Override
    @Transactional
    public void update(Integer id, String title, Integer artistId) {
        val album = albumRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Album not found"));
        val artist = artistService.findById(artistId)
                .orElseThrow(() -> new IllegalArgumentException("Artist not found"));
        album.update(title, artist);
        albumRepository.save(album);
    }
}
