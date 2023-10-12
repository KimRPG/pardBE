package com.pard.controller;

import com.pard.dto.JoinDto;
import com.pard.dto.ResponseDto;
import com.pard.entity.PardEntity;
import com.pard.service.PardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
}
