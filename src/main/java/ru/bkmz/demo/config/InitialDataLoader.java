package ru.bkmz.demo.config;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import ru.bkmz.demo.entity.Answer;
import ru.bkmz.demo.entity.Question;
import ru.bkmz.demo.repo.AnswerDetailsRepo;
import ru.bkmz.demo.repo.QuestionsDetailsRepo;
import ru.bkmz.demo.repo.UserDetailsRepo;

import java.util.*;

@Component
@CrossOrigin(origins = "http://localhost:4200")
public class InitialDataLoader implements ApplicationRunner {

    private final UserDetailsRepo userDetailsRepo;
    private final AnswerDetailsRepo answerDetailsRepo;
    private final ModelMapper modelMapper;
    private final QuestionsDetailsRepo questionsDetailsRepo;

    @Autowired
    public InitialDataLoader(UserDetailsRepo userDetailsRepo, AnswerDetailsRepo answerDetailsRepo, ModelMapper modelMapper, QuestionsDetailsRepo questionsDetailsRepo) {
        this.userDetailsRepo = userDetailsRepo;
        this.answerDetailsRepo = answerDetailsRepo;
        this.modelMapper = modelMapper;
        this.questionsDetailsRepo = questionsDetailsRepo;
    }

    private int i = 0;

    public void run(ApplicationArguments args) {

        questionConstructor(("Вопрос " + (this.i)),
                "Что из перечисленного относится к цели информационного обеспечения процесса управления",
                new Answer("удовлетворение информационных потребностей управляющих органов;", true),
                new Answer("определение и отбор источников информации;", true),
                new Answer("документационное оформление планов и доведение их до исполнителей:", false),
                new Answer("исключение дублирования информации:", true),
                new Answer("правильная интерпретация и систематизация полученных данных:", true)
        );

        questionConstructor(("Вопрос " + (this.i)),
                "Информационное обеспечение функции оперативного управления включает в себя:",
                new Answer("сбор и обработку информации о возникшей при работе проблемной ситуации и формулировку соответствующих задач;", true),
                new Answer("сбор и обработку информации, необходимой для решения поставленных задач;", true),
                new Answer("доведение информации о принятом решении до конкретных исполнителей;", true),
                new Answer("сбор и анализ информации об имеющихся ресурсах (материальных, кадровых, временных, информационных и прочих);", true)

        );
        questionConstructor(("Вопрос " + (this.i)),
                "Информационное обеспечение функции контроля состоит в выполнении следующих процедур:",
                new Answer("доведение данных решений до исполнителей;", true),
                new Answer("сбор и обработку информации, необходимой для решения поставленных задач;", false),
                new Answer("доведение информации о принятом решении до конкретных исполнителей;", false),
                new Answer("предоставление необходимой информации подразделениям и руководителям, осуществляющим планирование и календарно-плановое руководство.", true)

        );
        questionConstructor(("Вопрос " + (this.i)),
                "Внемашинное ИО включает систему:",
                new Answer("потоки информации", true),
                new Answer("систему классификации и кодирования", true),
                new Answer("подлежащих автоматизированной обработке", true),
                new Answer("документацию", true)


        );
        questionConstructor(("Вопрос " + (this.i)),
                "Выберите верные утверждения ",
                new Answer("Back-Office объединяет вспомогательный персонал, и я говорю не только об ИТ.", true),
                new Answer("Back-Office могут работать администраторы, рекрутеры, помощники, информационные технологи, складские магазины, контролеры инвентаря, планировщики и контролеры (PCP), а также множество профессионалов.", true),
                new Answer("Информационное обеспечение (ИО) – совокупность единой системы классификации и кодирования информации, унифицированных систем документации, схем информационных потоков, циркулирующих в организации, а также методологии построения баз данных.", true),
                new Answer("Информация– информационные единицы низшего уровня, отражающие отдельные свойства объекта.", true)


        );
    }

    private void questionConstructor(String name, String questionStr, Answer... answers) {
        if (this.questionsDetailsRepo.findOneByName(name) == null) {
            Set<Answer> answer = new HashSet<>();
            answer.addAll(Arrays.asList(answers));
            answerDetailsRepo.saveAll(answer);
            Question question = new Question(name, questionStr, answer);
            questionsDetailsRepo.save(question);
        }
        i++;
    }
}