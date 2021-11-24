package ru.mirea.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.entity.Genre;

@Repository
public interface GenreRepo extends CrudRepository<Genre,Long> {
    Genre getGenreById(Long id);

}
