package com.dokobit.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
@RequiredArgsConstructor
public class ZipServiceImpl implements ZipService {

    @Override
    public void createZip(List<MultipartFile> files) throws IOException {
        for (MultipartFile file : files) {
            try (FileOutputStream fileOutputStream = new FileOutputStream("compressedFiles.zip");
                 ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
                 InputStream fileInputStream = new BufferedInputStream(file.getInputStream())) {
                ZipEntry zipEntry = new ZipEntry(file.getName());
                zipOutputStream.putNextEntry(zipEntry);

                byte[] bytes = new byte[1024];
                int length;
                while ((length = fileInputStream.read(bytes)) >= 0) {
                    zipOutputStream.write(bytes, 0, length);
                }
            }
        }
    }
}
