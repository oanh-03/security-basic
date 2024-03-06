package com.example.demo.service;


import com.example.demo.dto.TransactionDTO;
/**
 * @author Phuong Oanh
 * TransactionService Định nghĩa các phương thức để thực hiện các hoạt động liên quan đến giao dịch.
 */
public interface TransactionService {
    /**
     * Mã hóa một đối tượng TransactionDTO.
     *
     * @param transactionDTO Đối tượng TransactionDTO cần được mã hóa.
     * @return Đối tượng TransactionDTO đã được mã hóa.
     * @throws Exception
     */
    TransactionDTO encrypt(TransactionDTO transactionDTO) throws Exception;

    /**
     * Thêm một giao dịch từ một đối tượng TransactionDTO vào cơ sở dữ liệu.
     *
     * @param transactionDTO Đối tượng TransactionDTO chứa thông tin giao dịch.
     * @throws
     */
    void insert(TransactionDTO transactionDTO) throws Exception;
}
