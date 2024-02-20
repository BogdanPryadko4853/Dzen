package com.dzen.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "image")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "originalFileName")
    private String originalFileName;
    @Column(name = "bytes", columnDefinition = "longblob")
    private byte[] bytes;
    @Column(name = "isPreviewImage")
    private boolean isPreviewImage;
    @Column(name = "size")
    private Long size;
    @Column(name = "contentType")
    private String contentType;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private CurrentEvents currentEvents;

}
