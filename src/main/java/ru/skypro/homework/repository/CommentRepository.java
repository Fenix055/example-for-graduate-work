package ru.skypro.homework.repository;

import ru.skypro.homework.model.CommentModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CommentRepository extends JpaRepository<CommentModel, Integer> {

}
