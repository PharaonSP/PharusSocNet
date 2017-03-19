package ru.pharus.socnetwork.dao;

import ru.pharus.socnetwork.dao.exception.DAOException;
import ru.pharus.socnetwork.entity.Message;
import ru.pharus.socnetwork.entity.Model;

import java.util.List;
import java.util.Optional;

public interface MessageDao {
    int create(Message message) throws DAOException;

    default void update(Model model) throws DAOException{
      throw new UnsupportedOperationException();
    }

    default void delete(int id) throws DAOException{
        throw new UnsupportedOperationException();
    }

    List<Message> getAllById(int userId) throws DAOException;
}