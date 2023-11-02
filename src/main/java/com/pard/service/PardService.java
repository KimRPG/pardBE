package com.pard.service;

import com.pard.dto.JoinDto;
import com.pard.dto.ResponseDto;
import com.pard.entity.PardEntity;
import com.pard.repository.PardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.transform.Result;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PardService {
    private final PardRepository pardRepository;
    private final S3UploadService s3UploadService;

    @Autowired
    public PardService(PardRepository pardRepository, S3UploadService s3UploadService) {
        this.pardRepository= pardRepository;
        this.s3UploadService = s3UploadService;
    }
//, MultipartFile multipartFile
    public ResponseDto<PardEntity> joinPard(JoinDto joinDto) {
        //String imageUrl = s3UploadService.saveFile(multipartFile);
        String name = joinDto.getName();
        PardEntity pard = new PardEntity(joinDto);
        if (!pardRepository.existsById(name)) {
            pardRepository.save(pard);
            return ResponseDto.setSuccess("축하한다.", pard);
        }else{
            return ResponseDto.setFailed("이미 가입한 이름입니다.");
        }


    }

    public ResponseDto<PardEntity> searchPard(String name) {
        if (pardRepository.existsById(name)) {
            PardEntity pard = pardRepository.findByName(name);
            return ResponseDto.setSuccess(name + " 찾음", pard);
        }else{
            return ResponseDto.setFailed(name + "이란 사람이 Pard에 없어요!");
        }

    }
    @Transactional
    public ResponseDto<PardEntity> updatePard(JoinDto joinDto, String name) {
        if (pardRepository.existsById(name)) {
            PardEntity pard = pardRepository.findByName(name);
            if (joinDto.getName() != null && !joinDto.getName().isEmpty())pard.setName(joinDto.getName());
            if(joinDto.getAge() !=0) pard.setAge(joinDto.getAge());
            if (joinDto.getPart() != null && !joinDto.getPart().isEmpty())pard.setPart(joinDto.getPart());
            if (joinDto.getImgURL() != null && !joinDto.getImgURL().isEmpty())pard.setImgURL(joinDto.getImgURL());
            return ResponseDto.setSuccess("업데이트됨", pard);
        }
        else{
            return ResponseDto.setFailed(name + " 이런 사람은 Pard에 없어요!");
        }
    }
    public ResponseDto<List<PardEntity>> findAll(){
        List<PardEntity> result = pardRepository.findAll();
        return ResponseDto.setSuccess("PARD타는 사람들", result);
    }
    public ResponseDto<?> deletePard(String name){
        if (pardRepository.existsById(name)) {
            pardRepository.deleteById(name);
            return ResponseDto.setSuccess(name + " PARD 탈퇴!!", null);
        }
        return ResponseDto.setFailed("그딴이름 없음");
    }
}
