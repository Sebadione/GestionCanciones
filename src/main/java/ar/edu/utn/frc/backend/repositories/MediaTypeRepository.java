package ar.edu.utn.frc.backend.repositories;

import ar.edu.utn.frc.backend.models.MediaType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaTypeRepository extends JpaRepository<MediaType, Integer> {
}
