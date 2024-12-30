package io.github.cursospringboot.libraryapi.repository;

import io.github.cursospringboot.libraryapi.model.Autor;
import io.github.cursospringboot.libraryapi.model.GeneroLivro;
import io.github.cursospringboot.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository autorRepository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTest() {
        Autor autor = new Autor();
        autor.setNome("maria");
        autor.setNacionalidade("japonesa");
        autor.setDataNascimento(LocalDate.of(1950, 1, 1));

        Autor autorSalvo = autorRepository.save(autor);
        System.out.println(autorSalvo);
    }

    @Test
    public void atualizarTest() {
        var id = UUID.fromString("b6e14123-e01e-46fe-96f5-cd6208ba9da6");

        Optional<Autor> autor = autorRepository.findById(id);

        if (autor.isPresent()) {

            Autor autorEncontrado = autor.get();
            System.out.println(autor.get());

            autorEncontrado.setDataNascimento(LocalDate.of(2022, 1, 3));
            autorRepository.save(autorEncontrado);
        }
    }

    @Test
    public void listarTest() {
        List<Autor> lista = autorRepository.findAll();
        lista.forEach(System.out::println);

    }

    @Test
    public void countTest(){
        System.out.println(autorRepository.count());
    }

    @Test
    public void apagarTest(){
        List<Autor> lista = autorRepository.findAll();
        Autor oautor = lista.getFirst();  // Alterei de 'getFirst()' para 'get(0)', pois 'getFirst()' não é válido para Listas.

        Optional<Autor> retornado = autorRepository.findById(oautor.getId());

        try {
            // Acessando a propriedade 'nome' do autor retornado
            if (retornado.isPresent()) {
                Autor autor = retornado.get();
                System.out.println("Excluindo o autor: " + autor.getNome());  // Acessa o nome do autor
                autorRepository.delete(autor);  // Exclui o autor
            } else {
                System.out.println("Autor não encontrado.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao excluir autor: " + e.getMessage());
        }
    }


    @Test
    void salvarAutorComLivros(){

        Autor autor = new Autor();
        autor.setNome("WIRAS");
        autor.setNacionalidade("vietnamita");
        autor.setDataNascimento(LocalDate.of(1974, 1, 1));


        Livro livro = new Livro();
        livro.setIsbn("98198-9519s");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setTitulo("ANIMAIS TURISTICOS");
        livro.setDataPublicacao(LocalDate.of(1968, 1, 24));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("98198-9519s");
        livro2.setPreco(BigDecimal.valueOf(100));
        livro2.setGenero(GeneroLivro.BIOGRAFIA);
        livro2.setTitulo("SOY BOIOLA");
        livro2.setDataPublicacao(LocalDate.of(2022, 1, 24));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        autorRepository.save(autor);
        livroRepository.saveAll(autor.getLivros());

    }

    @Test


}
