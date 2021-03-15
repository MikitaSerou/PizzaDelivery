package org.study.PizzaDelivery.utils;

import com.google.common.io.Files;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.study.PizzaDelivery.service.BaseService;

import java.io.File;
import java.util.Objects;

@Component
public class FileChecker {

    private static final Logger logger = LogManager.getLogger(FileChecker.class);


    public String getFileExtension(MultipartFile file) {
        logger.info("Call method: getFileExtension(file:" + file + ")");
        logger.info("FileExtension: " + Files.getFileExtension(Objects.requireNonNull(file.getOriginalFilename())));

        return Files.getFileExtension(Objects.requireNonNull(file.getOriginalFilename()));
    }

    public boolean pngExtensionCheck(MultipartFile file) {
        logger.info("Call method: pngExtensionCheck(file:" + file + ")");

        boolean isPng = false;
        if (!Objects.requireNonNull(file.getOriginalFilename()).isEmpty() &&
                file.getContentType().equals("image/png")){
            isPng = true;
        }

        logger.info("Return: " + isPng);

        return isPng;
    }

    public boolean jpgExtensionCheck(MultipartFile file) {
        logger.info("Call method: jpgExtensionCheck(file:" + file + ")");

        boolean isJpg = false;
        if (!Objects.requireNonNull(file.getOriginalFilename()).isEmpty() &&
                (file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/jpg"))){
            isJpg = true;
        }

        logger.info("Return: " + isJpg);

        return isJpg;
    }
}
