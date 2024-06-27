package com.example.quizz.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum PaginationConstants {
    DEFAULT_PAGE(0),
    DEFAULT_SIZE(7);
    int value;

}
