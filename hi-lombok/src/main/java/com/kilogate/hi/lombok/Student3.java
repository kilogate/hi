package com.kilogate.hi.lombok;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Student3 {
    private int no;
    private String name;
}
