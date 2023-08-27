package rw.netmart.ecommerce.v1.servicesImpl;

import org.springframework.stereotype.Service;
import rw.netmart.ecommerce.v1.dtos.CreateProductDto;
import rw.netmart.ecommerce.v1.exceptions.ResourceNotFoundException;
import rw.netmart.ecommerce.v1.models.Manufacturer;
import rw.netmart.ecommerce.v1.models.Product;
import rw.netmart.ecommerce.v1.models.SubCategory;
import rw.netmart.ecommerce.v1.repositories.IProductRepository;
import rw.netmart.ecommerce.v1.repositories.ISubCategoriesRepository;
import rw.netmart.ecommerce.v1.repositories.ManufacturerRepository;
import rw.netmart.ecommerce.v1.services.IProductService;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements IProductService {

    private final IProductRepository productRepository;
    private final ISubCategoriesRepository subCategoriesRepository;
    private final ManufacturerRepository manufacturerRepository;

    public ProductServiceImpl(IProductRepository productRepository, ISubCategoriesRepository subCategoriesRepository, ManufacturerRepository manufacturerRepository) {
        this.productRepository = productRepository;
        this.subCategoriesRepository = subCategoriesRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public Product createProduct(CreateProductDto dto) {
        SubCategory category = subCategoriesRepository.findById(dto.getCategoryid()).orElseThrow(()-> new ResourceNotFoundException("Illustration"));
        Manufacturer manufacturer = manufacturerRepository.findById(dto.getManufacturerId()).orElseThrow(()-> new ResourceNotFoundException(("Manufacturer")));
        Product product = new Product(dto.getName(),category, dto.getModel(), dto.getDescription(), manufacturer, dto.getPrice(), dto.getDiscountRate(), dto.getInStock(), dto.getSold() );
        productRepository.save(product);
        return product;
    }

    @Override
    public String removeProduct(UUID id) {
        productRepository.deleteById(id);
        return "Successfully removed data!";
    }

    @Override
    public Product updateProduct(UUID id, CreateProductDto dto) {
        Product product =  productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product"));
        SubCategory category = subCategoriesRepository.findById(dto.getCategoryid()).orElseThrow(()-> new ResourceNotFoundException("Illustration"));
        Manufacturer manufacturer = manufacturerRepository.findById(dto.getManufacturerId()).orElseThrow(()-> new ResourceNotFoundException(("Manufacturer")));
        product.setCategory(category);
//        product.setName(dto.getName());
        product.setModel(dto.getModel());
        product.setDescription(dto.getDescription());
        product.setPrice(Float.valueOf(dto.getPrice()));
        product.setInStock(dto.getInStock());
        product.setManufacturer(manufacturer);
        productRepository.save(product);
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(UUID id) {
        Product product = productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product"));
        return product;
    }

    @Override
    public Product soldout(UUID id) {
        Product product = getProductById(id);
        product.setSold(1);
        productRepository.save(product);
        return product;
    }

}
