package com.example.demo.link.service;

import com.example.demo.link.domain.ShortLink;
import com.example.demo.link.domain.ShortLinkRepository;
import com.example.demo.link.dto.ShortLinkRes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LinkServiceTest {
    private LinkService linkService;

    @Autowired
    private ShortLinkRepository shortLinkRepository;

    @BeforeEach
    public void setUp(){
        linkService = new LinkService(shortLinkRepository);
    }

    @Test
    void createShortId() {
    }

    @Test
    void getShortLink() {
        ShortLinkRes shortLinkRes = linkService.getShortLink("abcde");
        assertThat(shortLinkRes.getShortId()).isEqualTo("abcde");
        assertThat(shortLinkRes.getUrl()).isEqualTo("https://airbridge.io");
    }
}