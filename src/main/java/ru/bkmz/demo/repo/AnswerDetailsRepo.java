package ru.bkmz.demo.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.bkmz.demo.entity.Answer;

public interface AnswerDetailsRepo extends JpaRepository<Answer, String> {

}
