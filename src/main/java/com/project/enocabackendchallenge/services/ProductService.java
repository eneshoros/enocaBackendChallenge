package com.project.enocabackendchallenge.services;

import com.project.enocabackendchallenge.entities.Product;
import com.project.enocabackendchallenge.repos.ProductRepository;
import com.project.enocabackendchallenge.requests.ProductCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Ürün bulunamadı"));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(ProductCreateRequest productCreateRequest) {
        Product product = new Product();

        product.setName(productCreateRequest.getName());
        product.setPrice(productCreateRequest.getPrice());
        product.setStockQuantity(productCreateRequest.getStockQuantity());

        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        Product existingProduct = getProductById(id);

        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setStockQuantity(product.getStockQuantity());

        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
