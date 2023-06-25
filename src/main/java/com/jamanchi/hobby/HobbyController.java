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

    @Operation(summary = "Create Hobby", description = "취미 생성")
    @PostMapping
    public void create(@RequestBody HobbyRequestDto.Create requestDto){
        hobbyService.create(requestDto);
    }


    @Operation(summary = "Find All MainHobbies", description = "전체 대분류 취미 조회")
    @GetMapping("/main")
    public ResponseEntity<List<HobbyResponseDto.Info>> findAllMainHobbies() {
        return ResponseEntity.ok(hobbyService.findAllMainHobbies());
    }

    @Operation(summary = "Find All SubHobbies", description = "전체 소분류 취미 조회")
    @GetMapping("/sub")
    public ResponseEntity<PageResponseDto> findAllSubHobbies(PageRequestDto requestDto){
        return ResponseEntity.ok(hobbyService.findAllSubHobbies(requestDto.of()));
    }

    @Operation(summary = "Find SubHobbies", description = "특정 대분류에 속한 취미 조회")
    @GetMapping("/{parentName}")
    public ResponseEntity<List<HobbyResponseDto.Info>> findSubHobbies(@PathVariable String parentName){
        return ResponseEntity.ok(hobbyService.findSubHobbies(parentName));
    }

    @Operation(summary = "Update Image", description = "이미지 수정")
    @PutMapping
    public ResponseEntity<Void> updateImage(@RequestPart HobbyRequestDto.UpdateImage requestDto
            , @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {
        hobbyService.updateImage(requestDto, image);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(summary = "Delete By Id", description = "Id로 취미 데이터 삭제")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id){
        hobbyService.deleteById(id);
    }
}
