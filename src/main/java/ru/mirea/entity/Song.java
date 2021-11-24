package ru.mirea.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "song")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "song_name")
    private String songName;

    @Column(name = "url")
    private String url;

    @ManyToOne
    @JoinColumn(name = "band")
    private Band band;
}
