package com.turntabl.testsystem.dao;

import com.turntabl.testsystem.message.GeneralAddResponse;
import com.turntabl.testsystem.model.Course;
import com.turntabl.testsystem.model.Question;
import com.turntabl.testsystem.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class QuestionDAO {
    @Autowired
    private QuestionRepository questionRepository;
    public QuestionDAO(){}
    public QuestionDAO(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }
    public Question get(Long id){
        return questionRepository.findById(id).get();
    }
    public List<Question> getQuestionsByTestId(Long test_id){
        return questionRepository.findQuestionByTestId(test_id);
    }
    public Optional<Question> getQuestionsByQuestionTestId(String question, Long test_id){
        return questionRepository.findByQuestionAndTestID(question, test_id);
    }
    public List<Question> addAll(List<Question> t){
        return questionRepository.saveAll(t);
    }
    public List<Question> getAll() {
        return questionRepository.findAll();
    }
    public Question add(Question question) {
        return questionRepository.save(question);
    }
    public Question update(Question questionFromUser){
        Question questionFromDatabase;
        questionFromDatabase = this.get(questionFromUser.getQuestion_id());
        questionFromDatabase.setQuestion(questionFromUser.getQuestion());
        return this.questionRepository.save(questionFromDatabase);
    }

    public GeneralAddResponse delete(Question question) {
        GeneralAddResponse generalAddResponse = new GeneralAddResponse();
        Optional<Question> optionalQuestion = questionRepository.findById(question.getQuestion_id());
        if (optionalQuestion.isPresent()) {
            questionRepository.delete(optionalQuestion.get());
            generalAddResponse.setMessage("success");
        }else{
            generalAddResponse.setMessage("failed");
        }
        return generalAddResponse;
    }
    public Optional<Question> getByName(String question_name){
        return questionRepository.findByQuestion(question_name);
    }
}
