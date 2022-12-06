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
    
    private Instant timeProperty;
}
