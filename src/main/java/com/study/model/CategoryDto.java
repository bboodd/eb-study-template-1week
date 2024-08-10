package com.study.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Data
@Builder
public class CategoryDto {
    int categoryId;
    String categoryName;
}
