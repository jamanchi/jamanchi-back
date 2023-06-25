package com.jamanchi.question;

import com.jamanchi.question.dto.QuestionRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    @Transactional
    public void create(QuestionRequestDto.Create request){
        Question question = request.toEntity();
        questionRepository.save(question);
    }

    @Transactional(readOnly = true)
    public List<String> getAll(){
        return questionRepository.getAll();
    }

    @Transactional
    public void deleteAll(){
        questionRepository.deleteAll();
    }
}
