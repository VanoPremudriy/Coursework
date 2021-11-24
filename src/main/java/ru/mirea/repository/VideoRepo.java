package ru.mirea.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.entity.Video;

import java.util.List;

@Repository
public interface VideoRepo extends CrudRepository<Video, Long> {
    List<Video> findAllByBand_Id(Long id);
}
