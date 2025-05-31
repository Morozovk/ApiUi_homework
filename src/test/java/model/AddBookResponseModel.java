package model;

import lombok.Data;

import java.util.List;

@Data
public class AddBookResponseModel {
    String userId;
    String username;
    List<BookModel> books;
}
