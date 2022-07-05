package com.dokobit.homework.controller;

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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ZipControllerTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "statistics"); //deletes all data from table before each test
    }

    @Test
    void zipsFilesSuccessfully() throws Exception {
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", Files.newInputStream(Paths.get("src/test/java/com/dokobit/homework/testdata/test-data.csv")));
        MockMultipartFile mockMultipartFile1 = new MockMultipartFile("file", Files.newInputStream(Paths.get("src/test/java/com/dokobit/homework/testdata/test-data2.csv")));

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/zip")
                .file(mockMultipartFile)
                .file(mockMultipartFile1))
            .andExpect(status().is(200));
    }
}