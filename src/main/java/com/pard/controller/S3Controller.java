package com.pard.controller;

import com.pard.service.S3UploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class S3Controller {

    private final S3UploadService s3UploadService;
    public S3Controller(S3UploadService s3UploadService) {
        this.s3UploadService = s3UploadService;
    }
    @PostMapping(value = "/image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "이미지 스냅샷 저장 메소드", description = "이미지 스냅샷을 저장하는 메소드입니다.")
    @ApiResponse(responseCode = "200", description = "이미지 업로드 완료 후, 각각 이미지의 url list를 json 형식으로 반환합니다.")
    public String image(@RequestParam(value = "image") MultipartFile image) throws IOException {
        String result = s3UploadService.saveFile(image);
        return result;
    }
}
