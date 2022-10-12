package com.example.demo.link.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShortLinkRepository extends JpaRepository<ShortLink, Long> {
    Optional<ShortLink> findByShortId(String shortId);

    Optional<ShortLink> findByUrl(String url);
}
