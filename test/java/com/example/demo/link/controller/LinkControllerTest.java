package com.example.demo.link.controller;

import com.example.demo.link.domain.ShortLink;
import com.example.demo.link.dto.ShortLinkReq;
import com.example.demo.link.dto.ShortLinkRes;
import com.example.demo.link.service.LinkService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.hypermedia.Link;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class LinkControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private LinkService linkService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getShortLink() throws Exception {
        ShortLink shortLink = new ShortLink(1004L, "abcde", "airbridge.io", LocalDateTime.now());
        ShortLinkRes shortLinkRes = ShortLinkRes.builder()
                .shortId(shortLink.getShortId())
                .url(shortLink.getUrl())
                .createdAt(shortLink.getCreatedAt())
                .build();
        given(linkService.getShortLink("abcde")).willReturn(shortLinkRes);


        mvc.perform(MockMvcRequestBuilders.get("/short-links/abcde"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.shortId").value("abcde"));


    }

    @Test
    void createShortLink() throws Exception {
        ShortLink shortLink = new ShortLink(1004L, "abcde", "https://airbridge.io", LocalDateTime.now());
        ShortLinkRes shortLinkRes = ShortLinkRes.builder()
                .shortId(shortLink.getShortId())
                .url(shortLink.getUrl())
                .createdAt(shortLink.getCreatedAt())
                .build();
        given(linkService.createShortId("https://airbridge.io")).willReturn(shortLinkRes);

        String content = objectMapper.writeValueAsString(new ShortLinkReq("https://airbridge.io"));
        mvc.perform(MockMvcRequestBuilders.post("/short-links")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                //.andExpect(jsonPath("$.[0].data.shortId").value("abcde"))
                .andExpect(jsonPath("$.data.url").value("https://airbridge.io"));
    }

    @Test
    void redirectLink() throws Exception {
        ShortLink shortLink = new ShortLink(1004L, "abcde", "airbridge.io", LocalDateTime.now());
        ShortLinkRes shortLinkRes = ShortLinkRes.builder()
                .shortId(shortLink.getShortId())
                .url(shortLink.getUrl())
                .createdAt(shortLink.getCreatedAt())
                .build();
        given(linkService.getShortLink("abcde")).willReturn(shortLinkRes);

        mvc.perform(MockMvcRequestBuilders.get("/r/abcde"))
                .andExpect(status().is3xxRedirection());
    }
}