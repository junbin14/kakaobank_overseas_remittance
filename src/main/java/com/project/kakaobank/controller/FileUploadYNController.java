package com.project.kakaobank.controller;

import com.project.kakaobank.service.FileUploadYNService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class FileUploadYNController {
    private final FileUploadYNService fileUploadYNService;
    @Autowired
    public FileUploadYNController(FileUploadYNService fileUploadYNService) {
        this.fileUploadYNService = fileUploadYNService;
    }

    @PostMapping("/files/upload")
    @ResponseBody
    public String create(@RequestParam("multiPartfiles")List<MultipartFile> multipartfiles,
                         @RequestParam("email")String email) throws Exception {
        fileUploadYNService.save(multipartfiles, email);
        return "ok";
    }
}
