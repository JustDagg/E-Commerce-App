package com.dagg.repository;

import com.dagg.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Dagg
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {

    List<Product> findAllByCategoryId(int id);

    @Modifying
    @Transactional
    @Query("DELETE Product pr WHERE pr.id IN(:Ids)")
    void deleteMultipleProducts(List<Integer> Ids);

}
