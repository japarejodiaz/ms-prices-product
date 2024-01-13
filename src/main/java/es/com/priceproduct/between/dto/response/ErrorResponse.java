package es.com.priceproduct.between.dto.response;

import lombok.*;

import java.io.Serializable;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse implements Serializable {

    private String message;
    private int statusCode;

}
