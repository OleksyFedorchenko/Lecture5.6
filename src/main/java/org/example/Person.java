package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.Instant;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person {
    private String stringProperty;
    @Property(name = "numberProperty")
    private int myNumber;
}
