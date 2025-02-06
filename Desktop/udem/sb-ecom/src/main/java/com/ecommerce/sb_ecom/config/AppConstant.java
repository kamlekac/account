package com.ecommerce.sb_ecom.config;

public class AppConstant {
    // these values will be constant and when we enter it in postman no need to add params column and can hit end point, if
    // we want the values also we can enter in postman like pagenumber 1, pagesize 20, but default will be these only.
public static final String PAGE_NUMBER= "0";
public static final String PAGE_SIZE= "50";
// adding sortBy and sortOrder as default values
public static final String SORT_CATEGORIES_BY="categoryId";
public static final String SORT_DIR="asc";

}
