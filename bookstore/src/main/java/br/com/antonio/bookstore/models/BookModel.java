package br.com.antonio.bookstore.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Entity
@Table(name = "TB_BOOKS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class BookModel implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String title;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private PublisherModel publisher;

    @ManyToMany
    @JoinTable(
            name = "tb_books_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<AuthorModel> authors = new HashSet<>();

    @OneToOne(mappedBy = "book",cascade = CascadeType.ALL)
    private ReviewModel review;
}

















