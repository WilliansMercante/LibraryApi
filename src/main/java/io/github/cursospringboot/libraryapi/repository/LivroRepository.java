package io.github.cursospringboot.libraryapi.repository;

import io.github.cursospringboot.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {

}
