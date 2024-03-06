package com.example.demo.repository;


import com.example.demo.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Phuong Oanh
 * quản lý các giao dịch trong cơ sở dữ liệu.
 * Mở rộng JpaRepository để kế thừa các hoạt động CRUD cơ bản.
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}

