package com.libraryManagement.serviceImpl;

import java.io.IOException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.libraryManagement.customExceptionHandling.UploadDownloadException;
import com.libraryManagement.entity.Storage;
import com.libraryManagement.repository.FileRepository;
import com.libraryManagement.service.FileService;

@Service
public class FileServiceImpl implements FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class); // Logger initialization

    @Autowired
    private FileRepository fileRepository;

    @Override
    public Storage uploadImg(MultipartFile file) {

        if (file.isEmpty()) {
            logger.error("File upload failed: The file is empty.");
            throw new UploadDownloadException(404, "Failed to upload file");
        }

        try {
            Storage f = new Storage();
            f.setFile(file.getBytes());
            f.setFileName(file.getOriginalFilename());
            f.setFileType(file.getContentType());

            logger.info("Uploading file with name: {}", file.getOriginalFilename());
            Storage savedFile = fileRepository.save(f);
            logger.info("File uploaded successfully with ID: {}", savedFile.getId());
            return savedFile;
        } catch (IOException e) {
            logger.error("Error occurred while uploading file: {}", e.getMessage());
            throw new UploadDownloadException(500, "Please try again later: " + e.getMessage());
        }
    }

    @Override
    public Storage downloadImg(int id) {
        logger.info("Attempting to download file with ID: {}", id);

        Optional<Storage> file = fileRepository.findById(id);
        if (file.isEmpty()) {
            logger.error("File not found with ID: {}", id);
            throw new UploadDownloadException(404, "File not found with ID: " + id);
        }

        logger.info("File with ID: {} found. Preparing for download.", id);
        return file.get();
    }
}
