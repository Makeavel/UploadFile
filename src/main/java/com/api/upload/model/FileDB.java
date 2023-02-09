package com.api.upload.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

//https://www.bezkoder.com/spring-boot-upload-file-database/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "files")
public class FileDB {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    private String name;

    private String type;

    @Lob
    private byte[] data;

    public FileDB(String name , String type , byte[] data){
        this.data = data;
        this.type = type;
        this.name = name;
    }
}


