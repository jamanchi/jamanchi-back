package com.jamanchi.hobby;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.jamanchi.commons.dto.PageResponseDto;
import com.jamanchi.hobby.dto.HobbyRequestDto;
import com.jamanchi.hobby.dto.HobbyResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HobbyService {

    private final HobbyRepository hobbyRepository;
    private final Storage storage;

    @Value("${GCS_BUCKET_NAME}") // application.yml에 써둔 bucket 이름
    private String bucketName;
    private String url = "https://storage.googleapis.com/";

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
    public List<HobbyResponseDto.All> findAllMainHobbies(){
        List<HobbyResponseDto.All> results = hobbyRepository.findAllMainHobbies();

        String frontUrl = url + bucketName + "/";
        for(HobbyResponseDto.All h : results){
            if(h.getImage() == null)
                continue;
            h.setImageUrl(frontUrl);
        }
        return results;
    }

    // 전체 소분류 취미 조회
    @Transactional(readOnly = true)
    public PageResponseDto findAllSubHobbies(Pageable pageable){

        Page page = hobbyRepository.findAllSubHobbies(pageable);
        System.out.println(page.getTotalPages() + ", isLast : " + page.isLast() + ", " + page.isFirst());
        return new PageResponseDto(page.getContent(), page.isLast());
    }

    // 특정 대분류에 속한 취미 조회
    @Transactional(readOnly = true)
    public List<HobbyResponseDto.Info> findSubHobbies(String parentName){
        Integer parentId = findIdByName(parentName);

        return hobbyRepository.findSubHobbies(parentId);
    }

    @Transactional
    public void updateImage(HobbyRequestDto.UpdateImage requestDto, MultipartFile image) throws IOException {

        if(!existsByName(requestDto.getName())){
            throw new IllegalArgumentException("존재하지 않는 취미입니다.");
        }

        String uuid = UUID.randomUUID().toString(); // Google Cloud Storage에 저장될 파일 이름
        String ext = image.getContentType(); // 파일의 형식 ex) JPG, PNG

        // Storage에 이미지 업로드
        BlobInfo blobInfo = storage.create(
                BlobInfo.newBuilder(bucketName, uuid)
                        .setContentType(ext)
                        .build(),
                image.getInputStream()
        );

        hobbyRepository.updateImage(requestDto.getName(), uuid);
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
