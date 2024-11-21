package com.libraryManagement.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.libraryManagement.entity.Storage;

@Service
public interface FileService {

	public Storage uploadImg(MultipartFile file);

	public Storage downloadImg(int id);

}
