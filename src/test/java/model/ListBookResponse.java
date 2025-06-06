package model;

import lombok.Data;

import java.util.List;

@Data
public class ListBookResponse {
    List<BookModel> books;
}
