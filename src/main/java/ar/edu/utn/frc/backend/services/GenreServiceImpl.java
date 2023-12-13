package ar.edu.utn.frc.backend.services;

import ar.edu.utn.frc.backend.models.Album;
import ar.edu.utn.frc.backend.models.Genre;
import ar.edu.utn.frc.backend.repositories.GenreRepository;
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
public class GenreServiceImpl implements GenreService{

    GenreRepository genreRepository;
    IdentifierRepository identifierRepository;

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public Optional<Genre> findById(Integer id) {
        return genreRepository.findById(id);
    }

    @Override
    @Transactional
    public Genre create(String name) {
        val genreId = identifierRepository.nextValue(Genre.TABLE_NAME);
        val genre = new Genre(genreId, name);
        return genreRepository.save(genre);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        try {
            genreRepository.deleteById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("Genre not found");
        }
    }

    @Override
    @Transactional
    public void update(Integer id, String name) {
        val genre = genreRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Genre not found"));

        genre.update(name);
        genreRepository.save(genre);
    }
}
