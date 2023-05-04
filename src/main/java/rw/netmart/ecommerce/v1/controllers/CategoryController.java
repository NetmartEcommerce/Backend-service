package rw.netmart.ecommerce.v1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rw.netmart.ecommerce.v1.dtos.CreateCategoryDto;
import rw.netmart.ecommerce.v1.dtos.CreateSubCategoryDto;
import rw.netmart.ecommerce.v1.payloads.ApiResponse;
import rw.netmart.ecommerce.v1.services.ICategoryService;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("api/v1/category")
public class CategoryController {


    private final ICategoryService categoryService;

    @Autowired
    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping(path="/create")
    public ResponseEntity<ApiResponse> createCategory(@Valid @RequestBody CreateCategoryDto dto){
        System.out.println("dto");
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(categoryService.createCategory(dto)));
    }

    @DeleteMapping(path="/delete")
    public ResponseEntity<ApiResponse> deleteCategory(@Valid @RequestParam UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(categoryService.removeCategory(id)));
    }

    @PutMapping(path= "/update")
    public ResponseEntity<ApiResponse> updateCategory(@Valid @RequestBody CreateCategoryDto dto, @RequestParam UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(categoryService.updateCategory(id, dto)));
    }

    @PutMapping(path= "/update/sub-category")
    public ResponseEntity<ApiResponse> updateSubCategory(@Valid @RequestBody CreateSubCategoryDto dto, @RequestParam UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(categoryService.updateSubCategory(id, dto)));
    }

    @DeleteMapping(path="/delete/sub-category")
    public ResponseEntity<ApiResponse> deleteSubCategory(@Valid @RequestParam UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(categoryService.removeSubCategory(id)));
    }
    @GetMapping()
    public ResponseEntity<ApiResponse> getCategories(){
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(categoryService.getCategories()));
    }
}
