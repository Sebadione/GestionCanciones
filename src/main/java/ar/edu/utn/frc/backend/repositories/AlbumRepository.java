package ar.edu.utn.frc.backend.repositories;

import ar.edu.utn.frc.backend.models.Album;
import ar.edu.utn.frc.backend.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {


    void deleteAllByArtist(Artist artist);
}
