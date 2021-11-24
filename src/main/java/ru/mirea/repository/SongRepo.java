package ru.mirea.repository;

import org.springframework.data.repository.CrudRepository;
import ru.mirea.entity.Song;

import java.util.List;

public interface SongRepo extends CrudRepository<Song, Long> {
    List<Song> findAllByBand_Id(Long id);
    Song getSongById(Long id);
}
