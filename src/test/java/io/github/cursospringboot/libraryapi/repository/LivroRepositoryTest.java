package io.github.cursospringboot.libraryapi.repository;

import io.github.cursospringboot.libraryapi.model.Autor;
import io.github.cursospringboot.libraryapi.model.GeneroLivro;
import io.github.cursospringboot.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    AutorRepository autorRepository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    void salvarTeste() {
        Livro livro = new Livro();
        livro.setIsbn("98198-9519");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.of(1981, 1, 24));
        Autor autor = autorRepository.findById(UUID.fromString("6f75d147-fb77-46e7-8d31-4b19590c779f")).orElse(null);
        livro.setAutor(autor);

        livroRepository.save(livro);
    }

    @Test
    void salvarCascadeTeste() {
        Livro livro = new Livro();
        livro.setIsbn("98198-9519");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.of(1981, 1, 24));

        Autor autor = new Autor();
        autor.setNome("wila");
        autor.setNacionalidade("alemao");
        autor.setDataNascimento(LocalDate.of(1973, 10, 10));

        livro.setAutor(autor);

        livroRepository.save(livro);
    }

    @Test
    void salvarLivroEAutorTeste() {
        Livro livro = new Livro();
        livro.setIsbn("98198-9519");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FANTASIA);
        livro.setTitulo("ANIME");
        livro.setDataPublicacao(LocalDate.of(1981, 1, 24));

        Autor autor = new Autor();
        autor.setNome("blasbario");
        autor.setNacionalidade("ingres");
        autor.setDataNascimento(LocalDate.of(1973, 10, 10));

        autorRepository.save(autor);

        livro.setAutor(autor);

        livroRepository.save(livro);
    }

    @Test
    void atualizarAutorLivro(){
        var livroParaAtualizar = livroRepository.findById(UUID.fromString("0c4eeb30-8417-4114-86b2-968247dbc290")).orElse(null);
        var autor = autorRepository.findById(UUID.fromString("9bf994e1-9887-4bff-9956-11aad178ce0e")).orElse(null);

        livroParaAtualizar.setAutor(autor);
        livroRepository.save(livroParaAtualizar);
    }

    @Test
    void deletar(){
        var id = UUID.fromString("0c4eeb30-8417-4114-86b2-968247dbc290");
        livroRepository.deleteById(id);
    }

}