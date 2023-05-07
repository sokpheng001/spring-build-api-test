package com.example.api.mbanking.util;

import com.example.api.mbanking.api.file.FileDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileUtil {
    @Value("${file.server-path}")
    private String fileServerPath;
    @Value("${file.base-url}")
    private String fileBaseUrl;
    @Value("${file.base-url-download}")
    private String fileBaseDownloadUrl;
    /**
     *
     * @param multipartFile used for uplaoding file
     * @return
     */
    public FileDto uploadFile(MultipartFile multipartFile){
        int lastDotIndex = multipartFile.getOriginalFilename().lastIndexOf(".");
        String extension = multipartFile.getOriginalFilename().substring(lastDotIndex+1);
        long size = multipartFile.getSize();
        String fileName = String.format("%s.%s", UUID.randomUUID(),extension);
        String url = String.format("%s%s",fileBaseUrl,fileName);
        Path path = Paths.get(fileServerPath + fileName);
        try {
            Files.copy(multipartFile.getInputStream(), path);
            return FileDto.builder()
                    .name(fileName)
                    .url(url)
                    .downloadUrl(fileBaseDownloadUrl + fileName)
                    .extension(extension)
                    .size(size)
                    .build();
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Uploading failed...!");
        }
    }
    /**
     *
     * @param fileName used for finding file
     * @return FileDto
     */
    public FileDto findFileByName(String fileName){
        File file = new File(fileServerPath);
        File[] files = file.listFiles();
        for(File file1: files){
//            String name = file1
//                    .getName()
//                    .substring(0,file1.getName().length()-4);
            if(file1.getName().startsWith(fileName)){
                return FileDto
                        .builder()
                        .name(file1.getName())
                        .url(fileBaseUrl + file1.getName())
                        .downloadUrl(fileBaseDownloadUrl + file1.getName())
                        .extension(file1.getName().substring(file1.getName().length()-3))
                        .size(file1.length())
                        .build();
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"File is not found, please contact the developer..🤣🤣🤣");
    }
    /**
     *
     * @param fileName used for deleting file by a specific name
     * @return String
     */
    public String removeFileByName(String fileName){
        File file = new File(fileServerPath);
        File[] files = file.listFiles();
        for(File file1: files){
//            String name = file1
//                    .getName()
//                    .substring(0,file1.getName().length()-4);
            if(file1.getName().startsWith(fileName)){
                file1.delete();
                return "File " +  fileName + " is removed successfully.";
            }
//            assert file1.getName().startsWith(fileName);
//            file1.delete();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"File is not found, please contact the developer..🤣🤣🤣");
    }
    /**
     * used for remove all file in folder server
     */
    public void removeAllFiles(){
        File file = new File(fileServerPath);
        File[] files = file.listFiles();
        List<FileDto> fileDtoList = new ArrayList<>();
        try{
            for(File file1: files){
                file1.delete();
            }
        }catch (Exception exception){
            return;
        }
    }
}

