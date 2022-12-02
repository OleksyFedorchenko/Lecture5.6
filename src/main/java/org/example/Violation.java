package org.example;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;

import java.time.LocalDateTime;
@EqualsAndHashCode
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Violation {
    @JsonSetter("date_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime dateTime;
    @JsonSetter("first_name")
    String firstName;
    @JsonSetter("last_name")
    String lastName;
    String type;
    @JsonSetter("fine_amount")
    Double fineAmount;
}