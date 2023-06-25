package com.jamanchi.answer;

import com.jamanchi.answer.dto.AnswerResponseDto;
import com.jamanchi.answer.dto.AnswerResultDto;
import com.jamanchi.hobby.Hobby;
import com.jamanchi.hobby.HobbyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AnswerService {
    @Value("${openai.key}")
    public static String GPT_KEY;
    @Value("${openai.dns}")
    public static String DNS;
    @Value("${openai.model}")
    public static String MODEL;
    private final AnswerRepository answerRepository;
    private final HobbyRepository hobbyRepository;

    @Transactional(readOnly = true)
    public AnswerResponseDto.DbResponse getAnswerDB(Integer hobbyId, Integer keywordId1, Integer keywordId2) {
        // 취미 정보조회
        Hobby hobbyInfo = hobbyRepository.findOne(hobbyId);

        List<AnswerResultDto> keywordRes = new ArrayList<>();
        Integer[] keywordIds = {1, 2, 3, keywordId1, keywordId2};

        for (Integer keywd: keywordIds) {
            if(!existsById(hobbyId, keywd)){
                throw new IllegalArgumentException("존재하지 않는 답변입니다.");
            }
            AnswerResultDto keywordInfo = answerRepository.getAnswer2(hobbyId, keywd);
            keywordRes.add(keywordInfo);
        }

        return new AnswerResponseDto.DbResponse(hobbyInfo.getName(),hobbyInfo.getImage(),keywordRes);
    }

    @Transactional
    public AnswerResponseDto.GptResponse getAnswerGPT(Integer hobbyId, Integer keywordId) {
        AnswerResponseDto.GptResponse prevAnswer = answerRepository.getAnswer(hobbyId, keywordId);
        if (prevAnswer == null) {
            try {
                List<Map<String, String>> gptAnswer = (List<Map<String, String>>) Objects.requireNonNull(excuteGpt().getBody()).get("choices");
                String gptAns = gptAnswer.get(0).get("text").replaceAll("\\n\\n", "");

                Answer newAnswer = new Answer(hobbyId, keywordId, gptAns);
                saveAnswer(newAnswer);

                return new AnswerResponseDto.GptResponse(newAnswer.getId(), newAnswer.getHobby_id(), newAnswer.getKeyword_id(), newAnswer.getContents());
            } catch (Exception e) {
                System.out.println("e = " + e);
            }
        }
        return prevAnswer;
    }

    public ResponseEntity<Map> excuteGpt () {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + GPT_KEY);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", MODEL);
        requestBody.put("prompt", "취미발레에 필요한 준비물 3가지와 그 이유를 각 3줄이내로 알려줘");
        requestBody.put("temperature", 0);
        requestBody.put("max_tokens", 300);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            public boolean hasError(ClientHttpResponse response) throws IOException {
                HttpStatus statusCode = (HttpStatus) response.getStatusCode();
                return statusCode.series() == HttpStatus.Series.SERVER_ERROR;
            }
        });

        ResponseEntity<Map> response = restTemplate.postForEntity(DNS, requestEntity, Map.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "GPT 사용불가");
        }

        return response;
    }

    @Transactional
    public void saveAnswer(Answer answer) {
        answerRepository.save(answer);
    }

    private boolean existsById(Integer hobbyId, Integer keywordId){
        return answerRepository.existsById(hobbyId, keywordId);
    }

}
