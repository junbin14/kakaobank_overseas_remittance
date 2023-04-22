package com.project.kakaobank.service;

import com.project.kakaobank.controller.FileUploadYNController;
import com.project.kakaobank.domain.Files;
import com.project.kakaobank.domain.User;
import com.project.kakaobank.repository.FileUploadYNRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FileUploadYNService {
    private final FileUploadYNRepository fileUploadYNRepository;
    private final UserService userService;
    @Autowired
    public FileUploadYNService(FileUploadYNRepository fileUploadYNRepository, UserService userService) {
        this.fileUploadYNRepository = fileUploadYNRepository;
        this.userService = userService;
    }

    public void save(List<MultipartFile> multipartFiles, String email) throws Exception{
        User user = userService.findByEmail(email);
        File folder = new File("C:\\Users\\User\\Desktop\\황준빈-학원\\file_upload\\"+email);
        if (!folder.exists()){
            folder.mkdirs();
        }
        String uploadFolder = "C:\\Users\\User\\Desktop\\황준빈-학원\\file_upload\\"+email;
        List<Files> lst = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            String uploadFileName = multipartFile.getOriginalFilename();
            File saveFile = new File(uploadFolder, uploadFileName);
            multipartFile.transferTo(saveFile);
            String filePath = uploadFolder + "\\" + uploadFileName;
            Files files = Files.builder()
                    .filePath(filePath)
                    .user(user)
                    .build();
            lst.add(files);
        }
        try{
            fileUploadYNRepository.saveAll(lst);
            user.setFileUploadYN("Y");
            userService.save(user);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
