package com.dokobit.homework.controller;

import com.dokobit.homework.service.ArchiveService;
import com.dokobit.homework.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StatisticsController {
    private final StatisticsService statisticsService;
    private final HttpServletRequest httpServletRequest;

    private final HttpServletResponse httpServletResponse;
    private final ArchiveService archiveService;

    @PostMapping(value = "/zip", produces = "application/zip")
    public ResponseEntity<String> zipAndDownload(@RequestPart("file") List<MultipartFile> files) {
//        statisticsService.saveStatistics(httpServletRequest.getRemoteAddr());
//        httpServletResponse.setContentType("application/zip");
//        httpServletResponse.setHeader("Content-Disposition", "attachment; filename=compressed.zip");
        try {
            archiveService.createFileZip(files);
            return ResponseEntity.ok("Success");
        } catch (Exception e) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Error while importing CSV file", e);
        }
    }
}
