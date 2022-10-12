package com.example.demo.link.service;

import com.example.demo.link.domain.ShortLink;
import com.example.demo.link.domain.ShortLinkRepository;
import com.example.demo.link.dto.ShortLinkRes;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class LinkService {
    private final ShortLinkRepository shortLinkRepository;

    @Transactional
    public ShortLinkRes createShortId(String url){
        Optional<ShortLink> shortLink = shortLinkRepository.findByUrl(url);
        if (shortLink.isPresent()){
            return ShortLinkRes.builder()
                    .shortId(shortLink.get().getShortId())
                    .url(shortLink.get().getUrl())
                    .createdAt(shortLink.get().getCreatedAt())
                    .build();
        }

        else {

            String shortId = RandomStringUtils.randomAlphanumeric(5); //random
            ShortLink shortLinkCreate = ShortLink.builder()
                    .shortId(shortId)
                    .url(url)
                    .createdAt(LocalDateTime.now())
                    .build();
            ShortLink shortLinkEntity = shortLinkRepository.save(shortLinkCreate);

            return ShortLinkRes.builder()
                    .shortId(shortLinkEntity.getShortId())
                    .url(shortLinkEntity.getUrl())
                    .createdAt(shortLinkEntity.getCreatedAt())
                    .build();
        }
    }

    @Transactional(readOnly = true)
    public ShortLinkRes getShortLink(String shortId) {
        Optional<ShortLink> shortLink = shortLinkRepository.findByShortId(shortId);
        //if (shortLink.isPresent()){
        return ShortLinkRes.builder()
                .shortId(shortLink.get().getShortId())
                .url(shortLink.get().getUrl())
                .createdAt(shortLink.get().getCreatedAt())
                .build();
        //}
    }
}