package com.example.crudeprofile;
 
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
 
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE "
			+ "CONCAT(p.id, p.name, p.brand, p.madein, p.price)"
			+ "LIKE %?1%")
    public List<Product> search(String keyword);
 
}
