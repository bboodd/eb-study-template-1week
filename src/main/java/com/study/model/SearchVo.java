package com.study.model;

import lombok.*;

//db용 객체
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
@Value
public class SearchVo {

    private String startDate;

    private String endDate;

    private int categoryId;

    private String keyword;
}
