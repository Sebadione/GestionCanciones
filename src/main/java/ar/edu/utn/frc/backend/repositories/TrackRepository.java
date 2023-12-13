package ar.edu.utn.frc.backend.repositories;

import ar.edu.utn.frc.backend.models.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TrackRepository extends JpaRepository<Track, Integer> {
}
