package com.pard.service;

import com.pard.dto.JoinDto;
import com.pard.dto.ResponseDto;
import com.pard.entity.PardEntity;
import com.pard.repository.PardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PardService {
    private final PardRepository pardRepository;

    @Autowired
    public PardService(PardRepository pardRepository) {
        this.pardRepository= pardRepository;
    }

    public ResponseDto<PardEntity> joinPard(JoinDto joinDto) {
        PardEntity pard = new PardEntity(joinDto);
        pardRepository.save(pard);
        return ResponseDto.setSuccess("축하한다.", pard);
    }
}
