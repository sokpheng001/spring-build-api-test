package com.example.api.mbanking.api.file;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
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
    /**
     *
     * @param fileName used for searching file name
     * @return FileDto
     */
      FileDto findFileByName(String fileName);
    /**
     * used for find all files
     * @return List<FileDto>
     */
      List<FileDto> findAllFile();
    /**
     * used for remove all files
     */
    void removeAllFiles();
    /**
     * used for remove a file by a specific name
     */
    String removeFileByName(String fileName);
    /**
     * used for requesting the url for download file
     * @return fileDownloadDto
     */
    FileDownloadDto downloadFile(String fileName);
 }
