package ar.edu.utn.frc.backend.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;



@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IdentifierRepositoryImpl implements IdentifierRepository{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public int nextValue(String tableName) {

        Integer result = (Integer) entityManager
                .createNativeQuery(String.format("SELECT count(*) FROM %s", tableName)).getSingleResult();
        return result + 1;
    }
}
