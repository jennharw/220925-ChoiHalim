package com.example.demo.link.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ShortLinkTest {
    @Test
    public void creation(){
        ShortLink restaurant = new ShortLink(1004L, "abcde", "https://airbridge.io", LocalDateTime.now());
        assertThat(restaurant.getId()).isEqualTo(1004);

        assertThat(restaurant.getShortId()).isEqualTo("abcde");
        assertThat(restaurant.getUrl()).isEqualTo("https://airbridge.io");

    }


}