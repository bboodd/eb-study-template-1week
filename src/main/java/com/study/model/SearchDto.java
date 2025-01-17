package com.study.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Data
@Builder
public class SearchDto {

    String startDate;

    String endDate;

    int categoryId;

    String keyword;
}
