package com.booleanuk.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequest {
    private String title;
    private String genre;
    private Integer authorId;
    private Integer publisherId;
}
