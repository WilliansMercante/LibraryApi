package io.github.cursospringboot.libraryapi.repository;

import io.github.cursospringboot.libraryapi.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AutorRepository extends JpaRepository<Autor,UUID> {

}