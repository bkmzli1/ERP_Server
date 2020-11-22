package ru.bkmz.demo.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.bkmz.demo.entity.Answer;
import ru.bkmz.demo.entity.Tests;

public interface TestDetailsRepo extends JpaRepository<Tests, String> {

}
