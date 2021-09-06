package org.multicat.springbootkafkaformyself.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Book {
    private Long id;
    private String name;
}
