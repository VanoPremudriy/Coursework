package ru.mirea.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "genreband")
public class GenreBand {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "genre")
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "band")
    private Band band;
}
