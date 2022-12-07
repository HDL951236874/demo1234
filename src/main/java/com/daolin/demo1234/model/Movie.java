package com.daolin.demo1234.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Movie {
    @Id
    private String id;
    private String name;
}