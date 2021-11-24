package ru.mirea.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.entity.GenreBand;

import java.util.List;

@Repository
public interface GenreBandRepo extends CrudRepository<GenreBand,Long> {

    List<GenreBand> findAllByGenre_Id(Long id);
    List<GenreBand> findAllByBand_Id(Long id);

}
