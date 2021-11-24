package ru.mirea.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "genre_name")
    private String genreName;

    @Column(name = "genre_text")
    @Type(type = "org.hibernate.type.TextType")
    private String genreText;

    @Column(name = "url")
    private String url;

}
