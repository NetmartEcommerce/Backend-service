package rw.netmart.ecommerce.v1.servicesImpl;

import org.springframework.stereotype.Service;
import rw.netmart.ecommerce.v1.dtos.CreateCategoryDto;
import rw.netmart.ecommerce.v1.dtos.CreateSubCategoryDto;
import rw.netmart.ecommerce.v1.exceptions.BadRequestException;
import rw.netmart.ecommerce.v1.exceptions.ResourceNotFoundException;
import rw.netmart.ecommerce.v1.models.Category;
import rw.netmart.ecommerce.v1.models.SubCategory;
import rw.netmart.ecommerce.v1.repositories.ICategoriesRepository;
import rw.netmart.ecommerce.v1.repositories.ISubCategoriesRepository;
import rw.netmart.ecommerce.v1.services.ICategoryService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements ICategoryService {

    private final ICategoriesRepository categoryRepository;
    private final ISubCategoriesRepository subCategoriesRepository;

    public CategoryServiceImpl(ICategoriesRepository categoryRepository, ISubCategoriesRepository subCategoriesRepository) {
        this.categoryRepository = categoryRepository;
        this.subCategoriesRepository = subCategoriesRepository;
    }

    @Override
    public Category createCategory(CreateCategoryDto category) {
        if(categoryRepository.existsByName(category.getName())){
            throw new BadRequestException("Category with name " + category.getName() + " already exists");
        }
        Set<SubCategory> subCategoriesSet =  new HashSet<>();
        Category newcat = new Category();
        newcat.setDescription(category.getDescription());
        newcat.setName(category.getName());

        for(CreateSubCategoryDto sub : category.getSubCategories()){
              SubCategory subCategory = new SubCategory();
                subCategory.setName(sub.getName());
                subCategory.setDescription(sub.getDescription());
                subCategoriesRepository.save(subCategory);
                subCategoriesSet.add(subCategory);
        }
        newcat.setSubCategories(subCategoriesSet);
        categoryRepository.save(newcat);
        return newcat;
    }

    @Override
    public Category removeCategory(UUID id) {
         categoryRepository.deleteById(id);
         return null;
    }

    @Override
    public Category updateCategory(UUID id, CreateCategoryDto category) {
        Category category1 = categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("category", "name", category.getName()));
        category1.setName(category.getName());
        category1.setDescription(category.getDescription());
        categoryRepository.save(category1);
        return category1;
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public SubCategory removeSubCategory(UUID id) {
        subCategoriesRepository.deleteById(id);
        return null;
    }

    @Override
    public SubCategory updateSubCategory(UUID id, CreateSubCategoryDto dto) {
        SubCategory category1 = subCategoriesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("category", "name", dto.getName()));
        category1.setName(dto.getName());
        category1.setDescription(dto.getDescription());
        subCategoriesRepository.save(category1);
        return category1;
    }
}
