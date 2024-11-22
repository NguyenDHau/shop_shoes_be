package com.example.login.model.repository;

import com.example.login.dto.product.FileProductProjection;
import com.example.login.dto.product.ProductColorUpdateProjection;
import com.example.login.model.entity.FileProductImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileUrlProduct extends JpaRepository<FileProductImg, Long> {

    @Query(value = "select \n" +
            "p.id,\n" +
            "p.category_id as 'categoryId',\n" +
            "p.name,\n" +
            "p.description,\n" +
            "p.price,\n" +
            "p.product_code as productCode\n" +
            "from product p\n" +
            "where p.id = :id;", nativeQuery = true)
    List<ProductColorUpdateProjection> getProductUpdateDetails(@Param("id") Long id);

    @Query(value = "select fpi.public_id AS publicId, fpi.signature AS signature\n" +
            "from product_color pc\n" +
            "join file_product_img fpi on pc.id = fpi.product_color_id\n" +
            "where pc.product_id = :id", nativeQuery = true)
    List<FileProductProjection> filePublicOld(@Param("id") Long id);

    List<FileProductImg> findByProductColorIdIn(List<Long> listId);
}
