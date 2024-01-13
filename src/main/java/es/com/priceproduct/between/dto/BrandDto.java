package es.com.priceproduct.between.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class BrandDto implements Serializable {
    private Long id;
    private String name;
}
