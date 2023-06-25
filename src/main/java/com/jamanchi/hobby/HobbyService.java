package com.jamanchi.hobby;

import com.jamanchi.hobby.dto.HobbyRequestDto;
import com.jamanchi.hobby.dto.HobbyResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HobbyService {

    private final HobbyRepository hobbyRepository;

    @Transactional
    public void create(HobbyRequestDto.Create request){
        if(existsByName(request.getName())){
            throw new IllegalArgumentException("이미 존재하는 취미입니다.");
        }

        Hobby hobby;
        // 대분류 등록
        if(request.getParentName().isBlank()){
            hobby = new Hobby(request.getName(), 0);
        }
        // 소분류 등록
        else{
            Integer parentId = findIdByName(request.getParentName());
            hobby = new Hobby(request.getName(), parentId);
        }

        hobbyRepository.save(hobby);
    }

    // 전체 대분류 취미 조회
    public List<HobbyResponseDto.Info> findAllMainHobbies(){
        return hobbyRepository.findAllMainHobbies();
    }

    // 전체 소분류 취미 조회
    @Transactional(readOnly = true)
    public Page<HobbyResponseDto.Info> findAllSubHobbies(Pageable pageable){
        return hobbyRepository.findAllSubHobbies(pageable);
    }

    // 특정 대분류에 속한 취미 조회
    @Transactional(readOnly = true)
    public List<HobbyResponseDto.Info> findSubHobbies(String parentName){
        Integer parentId = findIdByName(parentName);

        return hobbyRepository.findSubHobbies(parentId);
    }

    @Transactional
    public void deleteById(Integer id){
        hobbyRepository.deleteById(id);
    }

    private Integer findIdByName(String name){
        if(!existsByName(name)){
            throw new IllegalArgumentException("존재하지 않는 취미입니다.");
        }

        return hobbyRepository.findIdByName(name);
    }

    private boolean existsByName(String name){
        return hobbyRepository.existsByName(name);
    }


}
