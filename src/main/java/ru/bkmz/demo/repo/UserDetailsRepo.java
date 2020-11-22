package ru.bkmz.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bkmz.demo.entity.Question;
import ru.bkmz.demo.entity.User;

public interface UserDetailsRepo extends JpaRepository<User, String> {
    User findOneById(String id);
}
