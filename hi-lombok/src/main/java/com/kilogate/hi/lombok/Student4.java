package com.kilogate.hi.lombok;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "of")
public class Student4 {
    @NonNull
    private int no;
    @NonNull
    private String name;
}
