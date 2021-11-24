package ru.mirea.repository;

import org.springframework.data.repository.CrudRepository;
import ru.mirea.entity.User;

public interface UserRepo extends CrudRepository<User, Long> {
    User getUserByUsername(String username);
    User getUserById(Long id);
}
