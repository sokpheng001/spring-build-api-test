package com.example.api.mbanking.api.file;

import com.example.api.mbanking.base.BaseRest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/files")
@Slf4j
@RequiredArgsConstructor
public class FileRestController {
    private final FileService fileService;
    @Value("${file.base-url-download}")
    private String fileBaseDownloadUrl;
    @Value("${file.server-path}")
    private String fileServerPath;
    private String downloadLink;
    @GetMapping
    public BaseRest<?> findAllFile() {
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(fileService.findAllFile())
                .message("Files found successfully.")
                .build();
    }
    @PostMapping("/uploaded-single")
    public BaseRest<?> uploadSingle(@RequestPart("file") MultipartFile multipartFile) {
        FileDto fileDto = fileService.uploadSingle(multipartFile);
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(fileDto)
                .message("File uploaded successfully.")
                .build();
    }
    @PostMapping("/uploaded-multiple")
    public BaseRest<?> uploadMultiple(@RequestPart("files") List<MultipartFile> multipartFileList) {
        List<FileDto> fileDtoList = fileService.uploadMultipleFile(multipartFileList);
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(fileDtoList)
                .message("Files uploaded successfully.")
                .build();
    }
    @GetMapping("/{fileName}")
    public BaseRest<?> findFileByName(@PathVariable("fileName") String fileName) {
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(fileService.findFileByName(fileName))
                .message("Files found successfully.")
                .build();
    }
    @DeleteMapping
    public BaseRest<?> removeAllFiles() {
        fileService.removeAllFiles();
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data("All files have been removed.")
                .message("Files have been removed successfully.")
                .build();
    }
    @DeleteMapping("/{fileName}")
    public BaseRest<?> removeFile(@PathVariable String fileName) {
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(fileService.removeFileByName(fileName))
                .message("File has been removed successfully.")
                .build();
    }
    @GetMapping("/download/{filename}")
    public ResponseEntity<?> downloadFile_(@PathVariable("filename") String filename){
        Resource resource = fileService.downloadFile(filename);
        this.downloadLink = "Download link: " + fileBaseDownloadUrl + filename;
        System.out.println(downloadLink);
        try {
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        }catch (ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File is not found.");
        }
    }
}
