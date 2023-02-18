package com.api.upload.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "files")
public class FileDB {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private String type;

    @Lob
    private byte[] data;

    @Column(name = "files" , columnDefinition ="TEXT")
    private String base64;

    public FileDB(String name , String type , String base64){
        this.base64 = base64;
        this.type = type;
        this.name = name;
    }
}


