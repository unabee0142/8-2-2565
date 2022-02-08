package nvc.it.spring_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nvc.it.spring_api.model.Product;
import nvc.it.spring_api.model.Review;
import nvc.it.spring_api.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(String id) {
        return productRepository.findById(id);
    }

    public List<Product> findProductByName(String name) {
        return productRepository.findByNameContaining(name);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> updateProduct(String id, Product product) {
        // ดึงข้อมูล product เดิมจาก id
        Product currrenProduct = productRepository.findById(id).get();
        currrenProduct.setName(product.getName());
        currrenProduct.setPrice(product.getPrice());
        currrenProduct.setUnit_in_stock(product.getUnit_in_stock());
        return Optional.of(productRepository.save(currrenProduct));
    }

    public Optional<Product> addReview(String id, Review review) {
        Product currentProduct = productRepository.findById(id).get();
        List<Review> reviews = currentProduct.getReviews();
        reviews.add(review);
        currentProduct.setReviews(reviews);
        return Optional.of(productRepository.save(currentProduct));
    }

    public boolean deleteProduct(String id) {
        try {
            productRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
