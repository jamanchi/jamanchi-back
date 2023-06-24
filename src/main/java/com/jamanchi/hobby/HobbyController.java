package com.jamanchi.hobby;

import com.jamanchi.hobby.dto.HobbyRequestDto;
import com.jamanchi.hobby.dto.HobbyResponseDto;
import com.jamanchi.keyword.dto.KeywordResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="취미", description = "취미에 관한 API입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/hobbies")
public class HobbyController {

    private final HobbyService hobbyService;

    @Operation(summary = "Create Hobby", description = "취미 생성")
    @PostMapping
    public void create(@RequestBody HobbyRequestDto.Create requestDto){
        hobbyService.create(requestDto);
    }

    @Operation(summary = "Find All", description = "전체 취미 조회")
    @GetMapping
    public ResponseEntity<List<HobbyResponseDto.Main>> findAll(){
        return ResponseEntity.ok(hobbyService.findAll());
    }

    @Operation(summary = "Find SubHobbies", description = "소분류 취미 조회")
    @GetMapping("/{parentName}")
    public ResponseEntity<List<HobbyResponseDto.Info>> findSubHobbies(@PathVariable String parentName){
        return ResponseEntity.ok(hobbyService.findSubHobbies(parentName));
    }

    @Operation(summary = "Delete By Id", description = "Id로 취미 데이터 삭제")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id){
        hobbyService.deleteById(id);
    }
}
