package org.example;

import lombok.*;

import java.time.Instant;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person {
    private String stringProperty;
    @Property(name = "numberProperty")
    private int myNumber;
    @Property(format = "dd.MM.yyyy HH:mm")
    private Instant timeProperty;
}
