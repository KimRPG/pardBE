package com.pard.controller;

import com.pard.dto.JoinDto;
import com.pard.dto.ResponseDto;
import com.pard.entity.PardEntity;
import com.pard.service.PardService;
import com.pard.service.S3UploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/pard")
@CrossOrigin(origins = "http://localhost:3000/*")
public class PardController {
    private final PardService pardService;


    @Autowired
    public PardController(PardService pardService) {
        this.pardService = pardService;

    }


    @PostMapping("/join")
    @Operation(summary = "파드에 가입(Create)하기 위한 메소드", description = "\"URL/pard/join\"으로 json파일을 보내면 생성됨")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(description = "똑같은 이름으로 가입할 수 없다. 가입이 된다면 \"PARD 합격을 축하해요\"와 정보가 뜬다., 만약 파트이름을 한글로 적을 경우 fail임")
    public ResponseDto<PardEntity> join(@RequestBody JoinDto dto) {
        ResponseDto<PardEntity> result = pardService.joinPard(dto);
        return result;
    }
    @GetMapping("/all")
    @Operation(summary = "파드에 가입한 사람들 전체를 읽어(Read)오기 위한 메소드", description = "\"URL/pard/all\"으로 요청을 보내면 정보를 줌")
    @ApiResponse(description = "요청이 정상적이라면, \"PARD타는 사람들\"과 함께 정보가 뜬다.")
    public ResponseDto<List<PardEntity>> findAll(){
        ResponseDto<List<PardEntity>> result = pardService.findAll();
        return result;
    }
    @GetMapping("/search/{name}")
    @Operation(summary = "파드에 가입한 사람들 중 한사람을 읽어(Read)오기 위한 메소드", description = "\"URL/pard/search/파드사람이름\"으로 요청을 보내면 그사람의 대한 데이터를 보내줌")
    @ApiResponse(description = "요청이 정상적이라면, \"name + 찾음\"이란 말과 함께 그사람의 정보가 뜬다. 만약 name이 없을 경우 \"name이란 사람이 Pard에 없어요!\"란 말을 반환함")
    public ResponseDto<PardEntity> search(@PathVariable String name) {
        ResponseDto<PardEntity> result = pardService.searchPard(name);
        return result;
    }
    @GetMapping("/search")
    @Operation(summary = "파드에 가입한 사람들 중 한사람을 읽어(Read)오기 위한 메소드", description = "\"URL/pard/search?name=파드사람이름\"으로 요청을 보내면 그사람의 대한 데이터를 보내줌")
    @ApiResponse(description = "요청이 정상적이라면, \"name + 찾음\"이란 말과 함께 그사람의 정보가 뜬다. 만약 name이 없을 경우 \"name이란 사람이 Pard에 없어요!\"란 말을 반환함")
    public ResponseDto<PardEntity> searchV2(@RequestParam String name) {
        ResponseDto<PardEntity> result = pardService.searchPard(name);
        return result;
    }

    @PatchMapping("/update/{userId}")
    @Operation(summary = "파드에 가입한 사람들 중 한사람을 업데이트(Update)오기 위한 메소드", description = "\"URL/pard/update/파드사람이름\"으로 json과 함께 요청을 보내면 그사람의 대한 데이터를 바꿔줌")
    @ApiResponse(description = "요청이 정상적이라면, \"업데이트됨\"이란 말과 함께 그사람의 정보가 뜬다. 만약 name이 없을 경우 \"name이란 사람이 Pard에 없어요!\"란 말을 반환함")
    public ResponseDto<PardEntity> update(@RequestBody JoinDto dto, @PathVariable String userId) {
        ResponseDto<PardEntity> result = pardService.updatePard(dto, userId);
        return result;
    }
    @DeleteMapping("/delete/{name}")
    @Operation(summary = "파드에 가입한 사람들 중 한사람을 삭제(Delete)하기 위한 메소드", description = "\"URL/pard/delete/파드사람이름\"으로 요청을 보내면 그사람의 대한 데이터를 삭제함")
    @ApiResponse(description = "요청이 정상적이라면,  \"name + PARD 탈퇴!!\"이란 말과 함께 그사람의 정보가 삭제된다. 만약 name이 없을 경우 \"그딴이름 없음!\"란 말을 반환함")
    public ResponseDto<?> delete(@PathVariable String name){
        ResponseDto<?> result = pardService.deletePard(name);
        return result;
    }

    @DeleteMapping("/delete")
    @Operation(summary = "파드에 가입한 사람들 중 한사람을 삭제(Delete)하기 위한 메소드", description = "\"URL/pard/delete?name=파드사람이름\"으로 요청을 보내면 그사람의 대한 데이터를 삭제함")
    @ApiResponse(description = "요청이 정상적이라면,  \"name + PARD 탈퇴!!\"이란 말과 함께 그사람의 정보가 삭제된다. 만약 name이 없을 경우 \"그딴이름 없음!\"란 말을 반환함")
    public ResponseDto<?> deleteV2(@RequestParam String name){
        ResponseDto<?> result = pardService.deletePard(name);
        return result;
    }
    @GetMapping("/search/part/{part}")
    @Operation(summary = "파드에 가입한 사람들 중 특정 파트에 읽어(Read)오기 위한 메소드", description = "\"URL/pard/search/part/part_이름\"으로 요청을 보내면 그파트의 대한 데이터를 보내줌")
    @ApiResponse(description = "요청이 정상적이라면, \"part + 파트 사람들\"이란 말과 함께 그 파트의 정보가 뜬다. 만약 파트가 없을 경우 \"그딴파트없음!\"란 말을 반환함, 한글로 쓸 경우 \"한글로 쓰지마세요!\"라고 반환함")
    public ResponseDto<List<PardEntity>> searchPart(@PathVariable String part){
        ResponseDto<List<PardEntity>> result = pardService.searchPartPard(part);
        return result;
    }
}
