package ru.mirea.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.entity.Image;

import java.util.List;

@Repository
public interface ImageRepo extends CrudRepository<Image, Long> {
    List<Image> findAllByBand_Id(Long id);
}
