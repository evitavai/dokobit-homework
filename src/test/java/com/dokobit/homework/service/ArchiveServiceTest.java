package com.dokobit.homework.service;

import com.dokobit.homework.repository.StatisticsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ArchiveServiceTest {

    @Autowired
    private StatisticsRepository statisticsRepository;

    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private ArchiveService archiveService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void tearDown() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "statistics"); //deletes all data from table before each test
    }

    @Test
    void zipsFilesSuccessfully() throws IOException {
        List<MultipartFile> multipartFiles = new ArrayList<>();
        multipartFiles.add(new MockMultipartFile("file", Files.newInputStream(Paths.get("src/test/java/com/dokobit/homework/testdata/test-data.csv"))));
//        multipartFiles.add(new MockMultipartFile("file", Files.newInputStream(Paths.get("src/test/java/com/dokobit/homework/testdata/test-data2.csv"))));

        multipartFiles.add(new MockMultipartFile("test", "test-data.csv", null, "test".getBytes()));
        multipartFiles.add(new MockMultipartFile("test2", "test-data2.csv", null, "test2".getBytes()));


        archiveService.createFileZip(multipartFiles);

    }
}