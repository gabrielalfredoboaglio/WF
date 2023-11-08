package com.C10G14.WorldFitBackend.service.impl;

import com.C10G14.WorldFitBackend.exception.InputNotValidException;
import com.C10G14.WorldFitBackend.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Objects;

@Service
public class ImageServiceImpl implements ImageService {


    //Sin docker
    //private final String FOLDER_PATH=System.getProperty("user.dir")+"/src/main/resources/static/images/";
    //Con docker
    private  final String FOLDER_PATH= "/var/lib/images/";
    private final String URL_PATH = System.getenv("URL_PATH");

    @Override
    public boolean isImageValid(MultipartFile image) {
        if (Objects.equals(image.getContentType(),null)){
            throw new InputNotValidException("Not a valid image");
        }
        String contentType = image.getContentType();
        if (contentType.equals("image/jpg")||
                contentType.equals("image/jpeg")||
                contentType.equals("image/png")){

            if(image.getSize()<2000000){
                return true;
            }
            throw new InputNotValidException("Image must be 2mb at must");
        }
        throw new InputNotValidException("Image type must be either jpg, jpeg, or png");
    }

    @Override
    public String upload(MultipartFile image, String userEmail) {
        StringBuilder imgPath = new StringBuilder();
        String encodedEmail = Base64.getEncoder().encodeToString(userEmail.getBytes());
        String contentType = image.getContentType().replace("image/",".");
        imgPath.append(FOLDER_PATH).
                append(encodedEmail).
                append(contentType);

        try {
            image.transferTo(new File(imgPath.toString()));
        }catch(IOException e){
            return e.getMessage();
        }
        return URL_PATH+encodedEmail+contentType;
    }
    }

