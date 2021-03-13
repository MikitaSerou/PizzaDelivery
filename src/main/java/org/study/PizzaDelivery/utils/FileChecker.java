package org.study.PizzaDelivery.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;

public class FileChecker {


    public static String getFileExtension(MultipartFile file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }

    public static boolean pngExtensionCheck(MultipartFile file) {

        return !Objects.requireNonNull(file.getOriginalFilename()).isEmpty() && getFileExtension(file).equals("png");
    }

    public static boolean jpgExtensionCheck(MultipartFile file) {

        return !Objects.requireNonNull(file.getOriginalFilename()).isEmpty() &&
                getFileExtension(file).equals("jpg") && getFileExtension(file).equals("jpeg");
    }
}
