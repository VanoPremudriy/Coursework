package ru.mirea.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.entity.Band;

@Repository
public interface BandRepo extends CrudRepository<Band, Long> {
    Band getBandById(Long id);
}
