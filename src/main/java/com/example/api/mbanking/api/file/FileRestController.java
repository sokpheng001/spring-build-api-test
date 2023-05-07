package com.example.api.mbanking.api.file;

import com.example.api.mbanking.base.BaseRest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/files")
@Slf4j
@RequiredArgsConstructor
public class FileRestController {
    private final FileService fileService;
    @Value("${file.server-path}")
    private String fileServerPath;

    @GetMapping
    public BaseRest<?> findAllFile() {
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(fileService.findAllFile())
                .message("File found successfully.")
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
                .message("Files selected successfully.")
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
                .data("All files has been deleted.")
                .message("File has been deleted successfully.")
                .build();
    }

    @DeleteMapping("/{fileName}")
    public BaseRest<?> removeFile(@PathVariable String fileName, HttpRequest httpRequest) {
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(fileService.removeFileByName(fileName))
                .message("File has been deleted successfully.")
                .build();
    }

//    @GetMapping("/download/{fileName}")
//    public BaseRest<?> downloadFile(@PathVariable("fileName") String fileName) {
//        return BaseRest
//                .builder()
//                .status(true)
//                .code(HttpStatus.OK.value())
//                .timestamp(LocalDateTime.now())
//                .data(fileService.downloadFile(fileName))
//                .message("File for download")
//                .build();
//    }
@Value("${file.base-url-download}")
private String fileBaseUrl;
    @GetMapping("/download/{filename}")
    public ResponseEntity<?> downloadFile_(@PathVariable String filename) throws MalformedURLException {
        File file = new File(fileServerPath);
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
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource)
                ;
        return responseEntity;
//        return FileDownloadDto.builder()
//                .downloadUrl(URI.create(fileBaseUrl + "api/v1/files/download/" + filename).toURL())
//                .build();
    }
}
