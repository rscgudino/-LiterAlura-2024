package com.example.literalura;

import com.example.literalura.client.BookApiClient;
import com.example.literalura.model.Book;
import com.example.literalura.repository.BookRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookApiClient bookApiClient;

    public List<Book> fetchAndSaveBooks() throws IOException {
        String jsonResponse = bookApiClient.fetchBooks();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode booksNode = mapper.readTree(jsonResponse);

        for (JsonNode bookNode : booksNode) {
            Book book = new Book();
            book.setTitle(bookNode.get("title").asText());
            book.setAuthor(bookNode.get("author").asText());
            book.setDescription(bookNode.get("description").asText());
            bookRepository.save(book);
        }

        return bookRepository.findAll();
    }
}
