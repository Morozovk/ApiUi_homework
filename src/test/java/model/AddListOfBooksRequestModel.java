package model;

import lombok.Data;

import java.util.List;

@Data
public class AddListOfBooksRequestModel {
    private String userId;
    private List<ListCollectionOfIsbns> ListCollectionOfIsbns;
}
