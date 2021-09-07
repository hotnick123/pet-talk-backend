package com.pettalk.pettalkbackend.controller;

import com.pettalk.pettalkbackend.dto.PetTalkResponse;
import com.pettalk.pettalkbackend.dto.gallery.GalleryRequest;
import com.pettalk.pettalkbackend.entity.Gallery;
import com.pettalk.pettalkbackend.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gallery")
public class GalleryController {
    @Autowired
    private GalleryService galleryService;

    /**
     * 게시글 등록
     * @param galleryRequest
     * @return
     */
    @PostMapping("/create")
    public PetTalkResponse<Gallery> create(@RequestBody GalleryRequest galleryRequest) {
        PetTalkResponse<Gallery> response = new PetTalkResponse<>();
        Gallery gallery = galleryService.create(galleryRequest);
        response.setData(gallery);
        return response;
    }
}
