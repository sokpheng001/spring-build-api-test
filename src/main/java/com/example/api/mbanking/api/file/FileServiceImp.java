package com.example.api.mbanking.api.file;

import com.example.api.mbanking.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class FileServiceImp implements FileService{
    private FileUtil fileUtil;
    @Autowired
    private void setFileUtil(FileUtil fileUtil){
        this.fileUtil = fileUtil;
    }
    @Override
    public FileDto uploadSingle(MultipartFile multipartFile) {
        return fileUtil.uploadFile(multipartFile);
    }
    @Override
    public List<FileDto> uploadMultipleFile(List<MultipartFile> multipartFileList) {
        List<FileDto> fileDtoList = new ArrayList<>();
        for(MultipartFile multipartFile: multipartFileList){
            fileDtoList.add(fileUtil.uploadFile(multipartFile));
        }
        return fileDtoList;
    }

    @Override
    public FileDto findFileByName(String fileName) {
        return null;
    }
}
