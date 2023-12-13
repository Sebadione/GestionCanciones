package ar.edu.utn.frc.backend;


import ar.edu.utn.frc.backend.models.Artist;
import ar.edu.utn.frc.backend.repositories.AlbumRepository;
import ar.edu.utn.frc.backend.repositories.ArtistRepository;
import ar.edu.utn.frc.backend.repositories.IdentifierRepository;
import ar.edu.utn.frc.backend.services.ArtistService;
import ar.edu.utn.frc.backend.services.ArtistServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;

public class ArtistServiceTest {

    ArtistRepository artistRepository;
    IdentifierRepository identifierRepository;
    AlbumRepository albumRepository;


    ArtistService artistService;

    @BeforeEach
    void setUp() {
        artistRepository = mock(ArtistRepository.class);
        identifierRepository = mock(IdentifierRepository.class);
        albumRepository = mock(AlbumRepository.class);
        artistService = new ArtistServiceImpl(artistRepository, identifierRepository, albumRepository);
    }

    @Test
    public void testArtistExistente() {
        Artist artistEsperado = new Artist(1, "AC/DC");
        Mockito.when(artistRepository.findById(1)).thenReturn(Optional.of(artistEsperado));
        Artist x =artistService.findById(1).get();
        Assertions.assertEquals(artistEsperado, x);
    }

    @Test
    public void testArtistNoExistente() {
        Artist artistEsperado = new Artist(999, "Aerosmith");
        Mockito.when(artistRepository.findById(1)).thenReturn(Optional.of(artistEsperado));
        Optional<Artist> x =artistService.findById(1);
        boolean esperado = x.isEmpty();
        Assertions.assertTrue(esperado);
    }
}
