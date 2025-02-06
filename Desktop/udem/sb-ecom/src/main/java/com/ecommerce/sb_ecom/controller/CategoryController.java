package com.ecommerce.sb_ecom.controller;

import com.ecommerce.sb_ecom.config.AppConstant;
import com.ecommerce.sb_ecom.payload.CategoryDTO;
import com.ecommerce.sb_ecom.payload.CategoryResponse;
import com.ecommerce.sb_ecom.service.CategoryService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;


    // using the requestmapping, we can write this as well, it is similar to get as well.
    @RequestMapping(value="/api/public/categories", method=RequestMethod.GET)
   // @GetMapping("api/public/categories")
    // adding pagination using requestparam
    public ResponseEntity<CategoryResponse> getAllCategories(
            @RequestParam(name="pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required= false) Integer pageNumber,
            @RequestParam(name="pageSize", defaultValue = AppConstant.PAGE_SIZE, required= false) Integer pageSize,
            @RequestParam(name="sortBy", defaultValue = AppConstant.SORT_CATEGORIES_BY, required = false) String sortBy,
            @RequestParam(name="sortOrder", defaultValue = AppConstant.SORT_DIR, required = false) String sortOrder)
    {

            CategoryResponse listOfCategory = categoryService.getAllCategories(pageNumber, pageSize, sortBy, sortOrder);
            return new ResponseEntity<>(listOfCategory, HttpStatus.OK);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
      }

    @RequestMapping(value="/api/admin/category", method=RequestMethod.POST)
    //@PostMapping("api/admin/category")
    //@Valid checks if the request is valid as per the model i.e., Category.
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO create= categoryService.createCategories(categoryDTO);
        return new ResponseEntity<>(create, HttpStatus.CREATED);
    }
    @RequestMapping(value="/api/admin/categories/{categoryId}", method= RequestMethod.DELETE)
    //@DeleteMapping("api/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoryId) {
        // handling the exception and making the error clear

            CategoryDTO deletedCategory= categoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(deletedCategory, HttpStatus.OK);

    }

   // @PutMapping("api/admin/categories/update/{categoryId}")
    @RequestMapping(value="/api/admin/categories/update/{categoryId}", method= RequestMethod.PUT)
    public ResponseEntity <CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO,@PathVariable Long categoryId) {
    /*    try {
            categoryService.updateCategory(category, categoryId);
            return new ResponseEntity<>("update", HttpStatus.ACCEPTED);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }*/

        // if we remove try block we can update the code as below
           CategoryDTO updatedDTO= categoryService.updateCategory(categoryDTO, categoryId);
            return new ResponseEntity<>(updatedDTO, HttpStatus.ACCEPTED);

    }
}