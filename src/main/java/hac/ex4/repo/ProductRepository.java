package hac.ex4.repo;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {


    Product findByName(String productName);

    List<Product> findTop5ByOrderByDiscountDesc();

    @Query("select p from Product p where p.name LIKE %?1%")
    public List<Product>getProductByNameLikeJPA(String name);






}
