package com.example.api.mbanking.api.file;

import com.example.api.mbanking.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileServiceImp implements FileService{
    private FileUtil fileUtil;
    @Value("${file.server-path}")
    private String fileServerPath;
    @Value("${file.base-url}")
    private String fileBaseUrl;
    @Value("${file.base-url-download}")
    private String fileBaseUrlDownload;
    @Autowired
    private void setFileUtil(FileUtil fileUtil){
        this.fileUtil = fileUtil;
    }
    //find all files
    @Override
    public List<FileDto> findAllFile() {
        File file = new File(fileServerPath);
        File[] files = file.listFiles();
        List<FileDto> fileDtoList = new ArrayList<>();
        assert files != null;
        for(File file1: files){
            int indexForGettingFileExtension = file1.getName().lastIndexOf(".") + 1;
            fileDtoList
                    .add(
                            new FileDto(
                                    file1.getName()
                                    ,fileBaseUrl + file1.getName()
                                    ,file1.getName().substring(indexForGettingFileExtension)
                                    ,file1.length()
                            )
                    );
        }
        return fileDtoList;
    }
    //upload single file
    @Override
    public FileDto uploadSingle(MultipartFile multipartFile) {
        return fileUtil.uploadFile(multipartFile);
    }
    //upload multiple file
    @Override
    public List<FileDto> uploadMultipleFile(List<MultipartFile> multipartFileList) {
        List<FileDto> fileDtoList = new ArrayList<>();
        for(MultipartFile multipartFile: multipartFileList){
            fileDtoList.add(fileUtil.uploadFile(multipartFile));
        }
        return fileDtoList;
    }
    //find any file by specific name
    @Override
    public FileDto findFileByName(String fileName){
        return fileUtil.findFileByName(fileName);
    }
    //remove all files
    @Override
    public void removeAllFiles() {
        fileUtil.removeAllFiles();
    }
    //remove a file by specific name
    @Override
    public String removeFileByName(String fileName) {
        return fileUtil.removeFileByName(fileName);
    }


    @Override
    @GetMapping("/download/{filename}")
    public ResponseEntity<?> downloadFile(String filename) {
        System.out.println(filename);
        File file = new File(filename);
        File[] files = file.listFiles();
        Path path  = Paths.get(fileServerPath + filename + ".png").toAbsolutePath().normalize();
        Resource resource = null;
        System.out.println(path);
        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(resource.getFilename());
        System.out.println(fileBaseUrl + "api/v1/files/download/" + filename );
        ResponseEntity <?> responseEntity = ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(
                        FileDownloadDto
                                .builder()
                                .size(filename.length())
                                .url(filename)
                                .extension("png")
                                .downloadUrl(fileBaseUrl + "api/v1/files/download/" + filename)
                                .build()
                )
                ;
        return responseEntity;
//        return FileDownloadDto.builder()
//                .downloadUrl(URI.create(fileBaseUrl + "api/v1/files/download/" + filename).toURL())
//                .build();
    }
    //
}
