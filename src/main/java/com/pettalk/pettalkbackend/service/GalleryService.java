package com.pettalk.pettalkbackend.service;

import com.pettalk.pettalkbackend.dto.gallery.GalleryRequest;
import com.pettalk.pettalkbackend.entity.Gallery;
import com.pettalk.pettalkbackend.repository.GalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GalleryService {
    @Autowired
    private GalleryRepository galleryRepository;

    public Gallery create(GalleryRequest galleryRequest) {
        Gallery gallery = Gallery.builder()
                            .title(galleryRequest.getTitle())
                            .content(galleryRequest.getContent())
                            .writer(galleryRequest.getWriter())
                            .build();
        return galleryRepository.save(gallery);
    }
}
