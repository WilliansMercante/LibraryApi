package io.github.cursospringboot.libraryapi.repository;

import io.github.cursospringboot.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository autorRepository;

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
}
