package es.com.priceproduct.between.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ErrorEnum {

    NOT_FOUND_PRICE("error.not.found.price"),
    PARAMETER_TYPE_MISMATCH("error.parameter.type.mismatch"),
    MISSING_PARAMETER("error.missing.parameter"),
    GENERIC_ERROR("error.generic");

    private String message;

}
