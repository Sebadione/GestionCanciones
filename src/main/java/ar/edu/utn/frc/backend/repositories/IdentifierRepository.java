package ar.edu.utn.frc.backend.repositories;



public interface IdentifierRepository {
    int nextValue(String tableName);
}