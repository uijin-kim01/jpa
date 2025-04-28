package com.jjang051.jpa.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ModalDto {
    private String title;
    private String content;
    private boolean isShow;
}
