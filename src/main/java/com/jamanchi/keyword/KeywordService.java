package com.jamanchi.keyword;

import com.jamanchi.keyword.dto.KeywordRequestDto;
import com.jamanchi.keyword.dto.KeywordResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KeywordService {

    private final KeywordRepository keywordRepository;

    @Transactional
    public void create(KeywordRequestDto.Create request){
        Keyword keyword = request.toEntity();

        keywordRepository.save(keyword);
    }

    @Transactional(readOnly = true)
    public List<KeywordResponseDto.Info> getAll(){
        return keywordRepository.getKeywords();
    }
}
