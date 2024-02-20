package com.dzen.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "events")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CurrentEvents {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    long id;
    @Column(name = "title")
    String title;
    @Column(name = "description")
    String description;
    @Column(name = "author")
    String author;
    @Column(name = "city")
    String city;
    LocalDateTime dateOfCreated;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "currentEvents")
    private List<Image> images = new ArrayList<>();

    @PrePersist
    void init() {
        dateOfCreated = LocalDateTime.now();
    }
    public void addImageToArticle(Image image){
        image.setCurrentEvents(this);
        images.add(image);
    }

    public void setPreviewImageId(Long id) {
    }
}


