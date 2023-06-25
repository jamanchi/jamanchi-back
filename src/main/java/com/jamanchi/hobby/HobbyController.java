package com.jamanchi.hobby;

import com.jamanchi.commons.dto.PageRequestDto;
import com.jamanchi.commons.dto.PageResponseDto;
import com.jamanchi.hobby.dto.HobbyRequestDto;
import com.jamanchi.hobby.dto.HobbyResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Tag(name="취미", description = "취미에 관한 API입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/hobbies")
public class HobbyController {

    private final HobbyService hobbyService;

    @Operation(summary = "취미 생성", description = "새로운 취미를 생성합니다.")
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody HobbyRequestDto.Create requestDto){
        hobbyService.create(requestDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(summary = "모든 대분류 취미 조회", description = "대분류에 해당하는 취미들을 모두 조회합니다.")
    @GetMapping("/main")
    public ResponseEntity<List<HobbyResponseDto.All>> findAllMainHobbies() {
        return ResponseEntity.ok(hobbyService.findAllMainHobbies());
    }

    @Operation(summary = "모든 소분류 취미 조회", description = "소분류에 해당하는 취미들을 모두 조회합니다.")
    @GetMapping("/sub")
    public ResponseEntity<PageResponseDto> findAllSubHobbies(PageRequestDto requestDto){
        return ResponseEntity.ok(hobbyService.findAllSubHobbies(requestDto.of()));
    }

    @Operation(summary = "특정 대분류에 속한 소분류 취미 조회", description = "특정 대분류에 속한 소분류 취미들을 조회합니다.")
    @GetMapping("/{parentName}")
    public ResponseEntity<List<HobbyResponseDto.Info>> findSubHobbies(@PathVariable String parentName){
        return ResponseEntity.ok(hobbyService.findSubHobbies(parentName));
    }

    @Operation(summary = "고른 선택지에 따른 추천 취미 조회", description = "고른 선택지에 따른 5개의 추천 취미들을 조회합니다.")
    @GetMapping("/recommend/{recommendId}")
    public ResponseEntity<List<HobbyResponseDto.All>> findRecommendHobbies(@PathVariable String recommendId){
        return ResponseEntity.ok(hobbyService.findRecommendHobbies(recommendId));
    }

    @Operation(summary = "취미의 이미지 수정", description = "취미에 해당하는 이미지 데이터를 수정합니다.")
    @PutMapping
    public ResponseEntity<Void> updateImage(@RequestPart HobbyRequestDto.UpdateImage requestDto
            , @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {
        hobbyService.updateImage(requestDto, image);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(summary = "취미 삭제", description = "Id에 해당하는 취미를 삭제합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id){
        hobbyService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
