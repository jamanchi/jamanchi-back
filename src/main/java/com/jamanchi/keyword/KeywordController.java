package com.jamanchi.keyword;

import com.jamanchi.keyword.dto.KeywordRequestDto;
import com.jamanchi.keyword.dto.KeywordResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="키워드", description = "키워드에 관한 API입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/keywords")
public class KeywordController {

    private final KeywordService keywordService;

    @Operation(summary = "Create Keyword", description = "키워드 생성")
    @PostMapping
    public void create(@RequestBody KeywordRequestDto.Create requestDto){
        keywordService.create(requestDto);
    }

    @Operation(summary = "Get Keywords", description = "모든 키워드 조회")
    @GetMapping
    public ResponseEntity<List<KeywordResponseDto.Info>> getAll(){
        return ResponseEntity.ok(keywordService.getAll());
    }
}
