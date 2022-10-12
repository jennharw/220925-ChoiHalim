package com.example.demo.link.controller;

import com.example.demo.common.response.CommonResponse;
import com.example.demo.link.dto.ShortLinkReq;
import com.example.demo.link.dto.ShortLinkRes;
import com.example.demo.link.service.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LinkController {
    private final LinkService linkService;

    @PostMapping("/short-links")
    public CommonResponse createShortLink(@RequestBody ShortLinkReq url){
        ShortLinkRes shortLinkRes = linkService.createShortId(url.getUrl());
        //scalability  - network, db scale out
        //System.out.println("=======" + shortLinkRes.getShortId());
        return CommonResponse.success(shortLinkRes);
    }

    @GetMapping("/short-links/{shortId}")
    public CommonResponse getShortLink(@PathVariable String shortId){
        ShortLinkRes shortLinkRes = linkService.getShortLink(shortId);
        return CommonResponse.success(shortLinkRes);

    }

    // redirect
    // RedirectView
    @GetMapping("/r/{shortId}")
    public RedirectView redirectLink(@PathVariable String shortId) {
        ShortLinkRes shortLinkRes = linkService.getShortLink(shortId);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(shortLinkRes.getUrl());
        return redirectView; //302
    }
}
