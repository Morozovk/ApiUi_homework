package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.print.Book;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserResponseBodyModel {
    String userID, username;
    List<Book> books;
}
