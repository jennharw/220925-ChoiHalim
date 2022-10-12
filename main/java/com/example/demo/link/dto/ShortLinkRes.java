package com.example.demo.link.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ShortLinkRes {
    private String shortId;
    private String url;
    private LocalDateTime createdAt;
}
