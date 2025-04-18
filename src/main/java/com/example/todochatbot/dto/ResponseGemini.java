package com.example.todochatbot.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ResponseGemini {
    private String title;
    private String description;
}
