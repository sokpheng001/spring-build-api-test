package com.example.api.mbanking.api.file;

import com.example.api.mbanking.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
                                    ,fileBaseUrlDownload + file1.getName()
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
    public Resource downloadFile(String filename) {
        File file = new File(fileServerPath);
        File [] files = file.listFiles();
        Path path = null;
        Resource resource;
        try {
            assert files != null;
            for(File file1 : files){
//                assert file1.getName().startsWith(filename);// we can used assert instead of (if or throw) statement
//                path  = Paths.get(fileServerPath + file1.getName()).toAbsolutePath().normalize();
                if(file1.getName().startsWith(filename)){
                    path  = Paths.get(fileServerPath + file1.getName()).toAbsolutePath().normalize();
                }
            }
            assert path != null;
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return resource;
    }
}
