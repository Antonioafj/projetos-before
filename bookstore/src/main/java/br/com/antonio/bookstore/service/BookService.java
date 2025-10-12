package br.com.antonio.bookstore.service;

import br.com.antonio.bookstore.dto.BookDto;
import br.com.antonio.bookstore.models.BookModel;
import br.com.antonio.bookstore.models.ReviewModel;
import br.com.antonio.bookstore.repositories.AuthorRepository;
import br.com.antonio.bookstore.repositories.BookRepository;
import br.com.antonio.bookstore.repositories.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final PublisherRepository publisherRepository;

    public List<BookModel> getAllBooks() {
        return bookRepository.findAll();
    }


    @Transactional
    public BookModel saveBook(BookDto bookDto) {

        BookModel book = new BookModel();
        book.setTitle(bookDto.title());
         book.setPublisher(publisherRepository.findById(bookDto.publisherId()).get());
         book.setAuthors(authorRepository.findAllById(bookDto.authorIds())
                 .stream()
                 .collect(Collectors.toSet()));

        ReviewModel reviewModel = new ReviewModel();
        reviewModel.setComment(bookDto.reviewComment());
        reviewModel.setBook(book);
        book.setReview(reviewModel);

        return  bookRepository.save(book);

    }

    @Transactional
    public void deleteBook(UUID id) {
        bookRepository.deleteById(id);
    }
}

































