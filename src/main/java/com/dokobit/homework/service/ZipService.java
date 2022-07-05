package com.dokobit.homework.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ZipService {

    default void createZip(List<MultipartFile> files) throws IOException {

    }
}
