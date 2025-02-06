package com.ecommerce.sb_ecom.service;

import com.ecommerce.sb_ecom.exception_handler.APIException;
import com.ecommerce.sb_ecom.exception_handler.ResourceNotFoundException;
import com.ecommerce.sb_ecom.model.Category;
import com.ecommerce.sb_ecom.payload.CategoryDTO;
import com.ecommerce.sb_ecom.payload.CategoryResponse;
import com.ecommerce.sb_ecom.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    //1. creating a array list for storing all the values, if there is no db and experimenting without db, nosql
    //private List<Category> categories = new ArrayList<>();
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    // using the page number and page size as input for this method for the pagination
    public CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        // this is used if we using no db and storing in list//   return categories;


        // implementing sorting here, we are using sort from springframe.data.domain
        Sort sortByAndOrder= sortOrder.equalsIgnoreCase("asc")
                ?Sort.by(sortBy).ascending()
                :Sort.by(sortBy).descending();

        // implementing pagination, using pageable interface and page interface for it.
        Pageable pageDetails= PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        Page <Category> categoryPage= categoryRepository.findAll(pageDetails);

       List<Category> ListOfCategories= categoryPage.getContent();// get content will give list of categories
       if(ListOfCategories.isEmpty())
           throw new APIException("There is no category");

       // mapping using modelmapper from list to responseCategory object.
      List<CategoryDTO> categoryDTOS= ListOfCategories.stream()
               .map(category -> modelMapper.map(category, CategoryDTO.class))
               .toList();
       CategoryResponse categoryResponse= new CategoryResponse();
       categoryResponse.setContent(categoryDTOS);
       // this is for purpose of frontend, the metadata for the pagination given
       categoryResponse.setPageNumber(categoryPage.getNumber());
       categoryResponse.setPageSize(categoryPage.getSize());
       categoryResponse.setTotalElements(categoryPage.getTotalElements());
       categoryResponse.setTotalPages(categoryPage.getTotalPages());
       categoryResponse.setLastPage(categoryPage.isLast());
       return categoryResponse;
//        try {
//            return categoryRepository.findAll();
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error fetching categories", e);
//        }
    }

    @Override
    public CategoryDTO createCategories(CategoryDTO categoryDTO) {
        // used when no db and using list// categories.add(category);
        // using model mapper and mapping categoryDTo to Category
        Category category= modelMapper.map(categoryDTO, Category.class);
         Category savedCategory=categoryRepository.findByCategoryName(category.getCategoryName());
        if(savedCategory!=null)
            throw new APIException("category with the name "+ category.getCategoryName()+ "exsist");
        Category categorysaved= categoryRepository.save(category);
        // converting category to category DTO as return type is categoryDTO
       CategoryDTO categoryDTO1= modelMapper.map(categorysaved, CategoryDTO.class);
         return categoryDTO1;
        }


    @Override
    public CategoryDTO deleteCategory(Long categoryId) {
      /*  List<Category> categories = categoryRepository.findAll();
        Category category = categories.stream()
                .filter(c -> c.getCategoryId()
                        .equals(categoryId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found"));
        categoryRepository.delete(category);
        //  categories.remove(category);
        return "deleted successfully";*/
        // here is the other method using findById()
        Category deleted= categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));
        // here the resourceNotFoundException is custom exception instead of ResponseEntity we are using this and writing custom exception
        // otherwise it would be like in the updateCategory method.
        //usimg model to convert category to categoryDTo
              categoryRepository.delete(deleted);
              CategoryDTO deleteDTO= modelMapper.map(deleted, CategoryDTO.class);
              return deleteDTO;
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId) {
        // we can also use optional category as variable, which means if the stream is finding category
        // it will give category otherwise it gives null.
     /*  List<Category> categories= categoryRepository.findAll();
        Optional<Category> updatedCategory = categories.stream()
                .filter(c -> c.getCategoryId()
                        .equals(categoryId))
                .findFirst();
                // if category is present, we are getting it
                if (updatedCategory.isPresent()) {
            Category categoryPresent = updatedCategory.get();
            categoryPresent.setCategoryName(category.getCategoryName());
            Category savedCategory= categoryRepository.save(categoryPresent);
            return savedCategory;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Not updated");
        }*/
        // we can also find the category using findById(), and since we are using findById, there is no need of stream, direct apply the exception if not found.
        Optional<Category> savedCategories = categoryRepository.findById(categoryId);
        Category updatedCategory = savedCategories
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource Not Found"));
        // mapping the category dto to category
        Category category1= modelMapper.map(categoryDTO, Category.class);
        // if category is present, we are getting it
        category1.setCategoryId(categoryId);// using this, if we even dont give the right categoryId in postman request still changes would be done, removing this line will throw server error is we do not give the categoryId which we give in the end point.
        categoryRepository.save(category1);
        //map using model mapper from category to categorydto
       CategoryDTO categoryDTO1= modelMapper.map(category1, CategoryDTO.class);
        return categoryDTO1;
    }

}
// Here is the second other way for the update method
     /*   public String updateCategory(Category category, Long categoryId){
       Category category1= categories.stream().filter(c-> c.getCategoryId().equals(categoryId))
                .findFirst().orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
        category1.setCategoryName(category.getCategoryName());
        return "update";
        }

} */


