package ru.mirea.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "band")
public class Band {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "band_name")
    private String bandName;

    @Column(name = "band_text")
    @Type(type = "org.hibernate.type.TextType")
    private String bandText;

    @Column(name = "main_image")
    private String mainImage;

}
