package com.kilogate.hi.lombok;

import lombok.*;

/**
 * Student
 *
 * @author kilogate
 * @create 2021/6/24 19:57
 **/
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor
@Builder
public class Student {
    @NonNull
    private Integer sno;
    @NonNull
    private String name;
    private String sex;
}
