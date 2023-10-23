package com.pard.controller;

import com.pard.dto.JoinDto;
import com.pard.dto.ResponseDto;
import com.pard.entity.PardEntity;
import com.pard.service.PardService;
import com.pard.service.S3UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class PardController {
    private final PardService pardService;
    private final S3UploadService s3UploadService;

    @Autowired
    public PardController(PardService pardService, S3UploadService s3UploadService) {
        this.pardService = pardService;
        this.s3UploadService = s3UploadService;
    }


    @PostMapping("/join")
    public ResponseDto<PardEntity> join(@RequestBody JoinDto dto) {
        ResponseDto<PardEntity> result = pardService.joinPard(dto);
        return result;
    }

    @PostMapping("/image")
    public String image(@RequestParam(value = "image") MultipartFile image) throws IOException {
        String result = s3UploadService.saveFile(image);
        return result;
    }

}
