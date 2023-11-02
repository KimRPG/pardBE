package com.pard.controller;

import com.pard.dto.JoinDto;
import com.pard.dto.ResponseDto;
import com.pard.entity.PardEntity;
import com.pard.service.PardService;
import com.pard.service.S3UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/pard")
public class PardController {
    private final PardService pardService;


    @Autowired
    public PardController(PardService pardService) {
        this.pardService = pardService;

    }


    @PostMapping("/join")
    public ResponseDto<PardEntity> join(@RequestBody JoinDto dto) {
        ResponseDto<PardEntity> result = pardService.joinPard(dto);
        return result;
    }

    @PatchMapping("/update/{userId}")
    public ResponseDto<PardEntity> update(@RequestBody JoinDto dto, @RequestParam String userId) {
        ResponseDto<PardEntity> result = pardService.updatePard(dto, userId);
        return result;
    }


    @GetMapping("/search/{name}")
    public ResponseDto<PardEntity> search(@PathVariable String name) {
        ResponseDto<PardEntity> result = pardService.searchPard(name);
        return result;
    }
    @GetMapping("/search")
    public ResponseDto<PardEntity> searchV2(@RequestParam String name) {
        ResponseDto<PardEntity> result = pardService.searchPard(name);
        return result;
    }
    @GetMapping("/all")
    public ResponseDto<List<PardEntity>> findAll(){
        ResponseDto<List<PardEntity>> result = pardService.findAll();
        return result;
    }
    @DeleteMapping("/delete/{name}")
    public ResponseDto<?> delete(@PathVariable String name){
        ResponseDto<?> result = pardService.deletePard(name);
        return result;
    }

    @DeleteMapping("/delete")
    public ResponseDto<?> deleteV2(@RequestParam String name){
        ResponseDto<?> result = pardService.deletePard(name);
        return result;
    }
}
