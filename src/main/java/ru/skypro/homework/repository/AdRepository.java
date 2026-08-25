package ru.skypro.homework.repository;

import ru.skypro.homework.model.AdModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface AdRepository extends JpaRepository<AdModel, Integer> {

}
