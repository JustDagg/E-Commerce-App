package com.dagg.repository;

import com.dagg.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Dagg
 * @created 27/10/2023
 */

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
