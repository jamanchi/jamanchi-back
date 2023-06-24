package com.jamanchi.hobby;

import com.jamanchi.hobby.dto.HobbyRequestDto;
import com.jamanchi.hobby.dto.HobbyResponseDto;
import com.jamanchi.keyword.Keyword;
import com.jamanchi.keyword.KeywordRepository;
import com.jamanchi.keyword.dto.KeywordRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Transactional(readOnly = true)
    public List<HobbyResponseDto.Main> findAll(){

        // 모든 취미 정보들을 조회하고 대분류를 따로 분류
        List<Hobby> hobbies = hobbyRepository.findAll();
        if(hobbies.isEmpty())
            return null;

        List<HobbyResponseDto.Info> mainHobbies = getMainHobbies(hobbies);

        // parentId 기준으로 취미 정보 매핑
        Map<Integer, List<HobbyResponseDto.Info>> groupingByParent = hobbies.stream()
                .map(c -> new HobbyResponseDto.Info(c.getId(), c.getName(), c.getParentId()))
                .collect(Collectors.groupingBy(p -> p.getParentId()));

        List<HobbyResponseDto.Main> mainList = new ArrayList<>();
        for(HobbyResponseDto.Info i : mainHobbies){
            HobbyResponseDto.Main main = new HobbyResponseDto.Main(i, groupingByParent.get(i.getId()));
            mainList.add(main);
        }

        return mainList;
    }

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

    // 모둔 취미 데이터 중 대분류만 반환
    private List<HobbyResponseDto.Info> getMainHobbies(List<Hobby> hobbies){
        List<HobbyResponseDto.Info> infos = new ArrayList<>();

        for(Hobby h : hobbies){
            if(h.getParentId() == 0){
                infos.add(toInfo(h));
            }
        }

        return infos;
    }

    private HobbyResponseDto.Info toInfo(Hobby hobby){
        return new HobbyResponseDto.Info(hobby.getId(), hobby.getName(), hobby.getParentId());
    }
}
