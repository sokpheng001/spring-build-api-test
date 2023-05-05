package com.example.api.mbanking.api.file;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface FileService {
    /**
     * used for uploading single file
     * @param multipartFile request form data from client
     * @return FileDto
     */
      FileDto uploadSingle(MultipartFile multipartFile);

    /**
     * used for uploading multiple files
     * @param multipartFileList request form data from client
     * @return Lis<FileDto>
     */
      List<FileDto> uploadMultipleFile(List<MultipartFile> multipartFileList);
      FileDto findFileByName(String fileName);
 }
