package com.dokobit.homework.controller;

import com.dokobit.homework.service.ZipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ZipController {
    private final ZipService zipService;

    @PostMapping(value = "/zip", produces = "application/zip")
    public ResponseEntity<String> zipAndDownload(@RequestPart("file") List<MultipartFile> files) {
        try {
            zipService.createZip(files);
            return ResponseEntity.ok("Success");
        } catch (Exception e) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Error while importing files", e);
        }
    }
}
