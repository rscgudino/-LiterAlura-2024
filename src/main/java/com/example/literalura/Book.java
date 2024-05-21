package com.example.literalura.model;

import com.example.literalura.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String publisher;
    private String isbn;

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Component
    public static class LiterAluraCLI implements CommandLineRunner {

        @Autowired
        private BookService bookService;

        @Override
        public void run(String... args) throws Exception {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Bienvenido a LiterAlura. Selecciona una opción:");
                System.out.println("1. Buscar y guardar libros desde la API");
                System.out.println("2. Mostrar todos los libros");
                System.out.println("3. Mostrar libro por ID");
                System.out.println("4. Eliminar libro por ID");
                System.out.println("5. Salir");
                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        fetchAndSaveBooks();
                        break;
                    case 2:
                        showAllBooks();
                        break;
                    case 3:
                        showBookById(scanner);
                        break;
                    case 4:
                        deleteBookById(scanner);
                        break;
                    case 5:
                        System.exit(0);
                    default:
                        System.out.println("Opción no válida. Inténtalo de nuevo.");
                }
            }
        }

        private void fetchAndSaveBooks() throws IOException {
            List<Book> books = bookService.fetchAndSaveBooks();
            System.out.println("Libros guardados:");
            books.forEach(book -> System.out.println(book.getTitle()));
        }

        private void showAllBooks() {
            List<Book> books = bookService.getAllBooks();
            books.forEach(book -> System.out.println(book.getTitle()));
        }

        private void showBookById(Scanner scanner) {
            System.out.println("Introduce el ID del libro:");
            Long id = scanner.nextLong();
            Book book = bookService.getBookById(id);
            System.out.println("Título: " + book.getTitle());
            System.out.println("Autor: " + book.getAuthor());
            System.out.println("Descripción: " + book.getDescription());
        }

        private void deleteBookById(Scanner scanner) {
            System.out.println("Introduce el ID del libro a eliminar:");
            Long id = scanner.nextLong();
            bookService.deleteBook(id);
            System.out.println("Libro eliminado.");
        }
    }
}
