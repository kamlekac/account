package com.ecommerce.sb_ecom.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// DTO class will be similar to entity but here it will have data wich will be visible to the client, it can not have password such kind of data.
public class CategoryDTO {
    private Long categoryId;
    private String categoryName;

}
