package com.example.demo.link.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Builder
@Setter
public class ShortLink {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(unique = true)
    private String shortId;

    @Column(unique = true)
    private String url;
    private LocalDateTime createdAt;


}
