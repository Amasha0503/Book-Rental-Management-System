package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {
    private int id;
    private String name;
    private String contact;
    private String gender;
    private String address;
    private LocalDate dob;
}
