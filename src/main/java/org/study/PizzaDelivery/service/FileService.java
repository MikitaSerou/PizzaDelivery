package org.study.PizzaDelivery.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.study.PizzaDelivery.utils.FileChecker;

import javax.servlet.ServletContext;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FileService {

    private static final Logger logger = LogManager.getLogger(FileService.class);

    @Autowired
    private FileChecker fileChecker;

    @Autowired
    private ServletContext context;


    public ResponseEntity productPhotoUploading(MultipartFile file, String productName) throws IOException {
        logger.info("Call method: productPhotoUploading(file: " + file + ", productName: " + productName + ")");

        if (productName.equals("")) {
            return new ResponseEntity<>("Choose product name before upload image.", HttpStatus.BAD_REQUEST);
        }

        if (fileChecker.pngExtensionCheck(file) || fileChecker.jpgExtensionCheck(file)) {
            BufferedOutputStream outputStream = new BufferedOutputStream(
                    new FileOutputStream(context.getRealPath("") + File.separator
                            + "resources/images/products" + File.separator +
                            productName.toLowerCase() + ".png"));

            outputStream.write(file.getBytes());
            logger.info("Create new file: " + "resources/images/products" + File.separator +
                    productName.toLowerCase() + ".png" + ")");

            outputStream.flush();
            outputStream.close();
        } else {
            logger.error("Invalid file " + file.getOriginalFilename() + " (only png).");
            return new ResponseEntity<>("Invalid file (.png only).", HttpStatus.BAD_REQUEST);
        }

        logger.info("Uploaded file: " + "resources/images/products" + File.separator +
                productName + ".png" + ")");

        return new ResponseEntity<>("File Uploaded Successfully.", HttpStatus.OK);
    }
}

