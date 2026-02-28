package model.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookTM {
    private int id;
    private String title;
    private String author;
    private String category;
    private int quantity;
}
