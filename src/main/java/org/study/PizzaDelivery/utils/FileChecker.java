package org.study.PizzaDelivery.utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Component
public class FileChecker {

    private static final Logger logger = LogManager.getLogger(FileChecker.class);


    public boolean pngExtensionCheck(MultipartFile file) {
        logger.info("Call method: pngExtensionCheck(file:" + file + ")");

        boolean isPng = !Objects.requireNonNull(file.getOriginalFilename()).isEmpty() &&
                Objects.requireNonNull(file.getContentType()).equals("image/png");

        logger.info("Return: " + isPng);

        return isPng;
    }

    public boolean jpgExtensionCheck(MultipartFile file) {
        logger.info("Call method: jpgExtensionCheck(file:" + file + ")");

        boolean isJpg = !Objects.requireNonNull(file.getOriginalFilename()).isEmpty() &&
                (Objects.requireNonNull(file.getContentType()).equals("image/jpeg") ||
                        file.getContentType().equals("image/jpg"));

        logger.info("Return: " + isJpg);

        return isJpg;
    }
}
