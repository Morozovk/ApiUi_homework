package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AddBookRequestModel {
   String token;
   String userId;
   String expires;
   String username;
}
