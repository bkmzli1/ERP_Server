package ru.bkmz.demo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.bkmz.demo.entity.*;
import ru.bkmz.demo.repo.QuestionsDetailsRepo;
import ru.bkmz.demo.repo.TestDetailsRepo;
import ru.bkmz.demo.repo.UserDetailsRepo;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/questions")
public class QuestionsController {

    private final QuestionsDetailsRepo questionsDetailsRepo;
    private final TestDetailsRepo testDetailsRepo;
    private final UserDetailsRepo userDetailsRepo;

    @Autowired
    public QuestionsController(QuestionsDetailsRepo questionsDetailsRepo, TestDetailsRepo testDetailsRepo, UserDetailsRepo userDetailsRepo) {
        this.questionsDetailsRepo = questionsDetailsRepo;
        this.testDetailsRepo = testDetailsRepo;
        this.userDetailsRepo = userDetailsRepo;
    }


    @GetMapping
    @JsonView(Views.IdName.class)
    public List<Question> list() {
        return questionsDetailsRepo.findAll();
    }

    @GetMapping("full")
    @JsonView(Views.FullMessage.class)
    public List<Question> listFull() {
        return questionsDetailsRepo.findAll();
    }

    @PutMapping
    public void listPut(@RequestBody Set<Question> questions, @AuthenticationPrincipal User user) {
        userDetailsRepo.save(user);
        if (!user.isTest()) {
            float balls = 0;
            for (Question question : questions) {
                Question questionRight = questionsDetailsRepo.findOneByName(question.getName());
                int ballsQuestion = 0;
                for (Answer answer : question.getAnswer()) {
                    ballsQuestion -= questionRight.getAnswer().stream()
                            .filter(ar -> ar.getId().equals(answer.getId()) & ar.isYN() != answer.isYN()).count();

                }
                if (ballsQuestion == 0) balls++;
            }


            user.setTest(true);
            userDetailsRepo.save(user);
            testDetailsRepo.save(new Tests(balls, user));
            userDetailsRepo.save(user);
        }

    }

    @GetMapping("statistics")
    @JsonView(Views.FullMessage.class)
    public List<ListStatistics> listStatistics() {
        Set<Tests> testsList = new HashSet<>();
        testsList.addAll(testDetailsRepo.findAll());

        Set<ListStatistics> listStatistics = new HashSet<>();


        testsList.stream().forEach(tests -> {
                    listStatistics.add(new ListStatistics(tests.getUser().getName(), tests.getBalls()));
                }
        );
        List<ListStatistics> collect = listStatistics.stream()
                .sorted(Comparator.comparing(ListStatistics::getBalls).reversed())
                .collect(Collectors.toList());
        return collect;
    }
}
