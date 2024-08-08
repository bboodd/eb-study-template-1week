package com.study.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchDto {

    String startDate;

    String endDate;

    int categoryId;

    String keyword;
}
