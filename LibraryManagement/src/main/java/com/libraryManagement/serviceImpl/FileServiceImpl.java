package com.libraryManagement.serviceImpl;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.libraryManagement.customExceptionHandling.UploadDownloadException;
import com.libraryManagement.entity.Storage;
import com.libraryManagement.repository.FileRepository;
import com.libraryManagement.service.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	private FileRepository fileRepository;

	@Override
	public Storage uploadImg(MultipartFile file) {

		if (file.isEmpty()) {
			throw new UploadDownloadException(404, " Failed to upload file");
		}

		try {
			Storage f = new Storage();
			f.setFile(file.getBytes());
			f.setFileName(file.getOriginalFilename());
			f.setFileType(file.getContentType());
			return fileRepository.save(f);
		} catch (IOException e) {
			throw new UploadDownloadException(500, "Please try again later" + "" + e.getMessage());
		}
	}

	@Override
	public Storage downloadImg(int id) {
		Optional<Storage> file = fileRepository.findById(id);
		if (file.isEmpty()) {
			throw new UploadDownloadException(404, "File not found with ID: " + id);
		}
		return file.get();
	}

}
