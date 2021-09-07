package com.pettalk.pettalkbackend.dto.gallery;

import lombok.Data;

@Data
public class GalleryRequest {
    String title;
    String content;
    String base64Img;
    Long writer;
}
