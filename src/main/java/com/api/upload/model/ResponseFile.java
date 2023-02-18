package com.api.upload.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseFile {

    private String id;
    private String name;
    private String url;
    private String type;
    private long size;
    private String base64;

    public ResponseFile(String toString, String name, String fileDownloadUrl, String type, String base64) {

    }
}
