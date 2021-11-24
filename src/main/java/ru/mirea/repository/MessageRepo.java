package ru.mirea.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.entity.Message;
import ru.mirea.entity.User;

import java.util.List;

@Repository
public interface MessageRepo extends CrudRepository<Message, Long> {
    List<Message> findAllBySenderAndRecipient(User sender, User recipient);
    List<Message> findAllByRecipient(User recipient);
    List<Message> findAllBySender(User sender);

}
