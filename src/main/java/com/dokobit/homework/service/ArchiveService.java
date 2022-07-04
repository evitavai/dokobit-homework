package com.dokobit.homework.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ArchiveService {

    default void createFileZip(List<MultipartFile> files) throws IOException {

    }
}
