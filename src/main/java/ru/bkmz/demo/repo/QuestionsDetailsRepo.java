package ru.bkmz.demo.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.bkmz.demo.entity.Answer;
import ru.bkmz.demo.entity.Question;

public interface QuestionsDetailsRepo extends JpaRepository<Question, String> {
    Question findOneByName(String name);
}
