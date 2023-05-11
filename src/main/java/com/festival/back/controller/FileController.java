package com.festival.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.festival.back.common.constant.ApiPattern;
import com.festival.back.service.FileService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(ApiPattern.FILE)
public class FileController {
    @Autowired private FileService fileService;

    private final String UPLOAD = "/upload";
    private final String GET_FILE = "/{fileName}";
    
    @ApiOperation(value = "파일 업로드"
    ,notes = "Request Body 에 FILE 을 포함하여 요청하면 성공시 다운로드 URl 을 반환,실패시 NULL 반환")
    @PostMapping(UPLOAD)
    public String upload(@ApiParam(value = "파일",example = "파일 이름",required = true)@RequestParam("file") MultipartFile file){
        String response = fileService.upload(file);
        return response;
    }
    
    @ApiOperation(value = "파일 다운로드", notes = "PathVariable 에 fileName 을 포함하여 요청하면"+
    "성공시 해당하는 파일의 Resource 에 해당하는 ")
    @GetMapping(value=GET_FILE, produces={MediaType.IMAGE_PNG_VALUE,MediaType.IMAGE_JPEG_VALUE})
    public Resource getFile(@ApiParam(value = "파일명",example = "example.png",required = true)@PathVariable("fileName")String fileName){
        Resource response = fileService.getFile(fileName);
        return response;
    }

}
