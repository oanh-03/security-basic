package com.example.demo.service.impl;

import com.example.demo.dto.TransactionDTO;
import com.example.demo.entity.Transaction;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.service.TransactionService;
import com.example.demo.utils.AESUtils;
import com.example.demo.utils.RSAUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Date;
/**
 * @author Phuong Oanh
 *
 * Lớp TransactionServiceImpl Cung cấp các dịch vụ liên quan đến giao dịch.
 */

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    /**
     * Mã hóa một đối tượng TransactionDTO.
     *
     * @param transactionDTO Đối tượng TransactionDTO cần được mã hóa.
     * @return Đối tượng TransactionDTO đã được mã hóa.
     * @throws Exception
     */
    public TransactionDTO encrypt(TransactionDTO transactionDTO) throws Exception {
            TransactionDTO encryptedDTO = new TransactionDTO();
            encryptedDTO.setTransactionId(RSAUtils.encrypt(transactionDTO.getTransactionId()));
            encryptedDTO.setDestinationAccount(RSAUtils.encrypt(transactionDTO.getDestinationAccount()));
            encryptedDTO.setSourceAccount(RSAUtils.encrypt(transactionDTO.getSourceAccount()));
            encryptedDTO.setInDebt(transactionDTO.getInDebt());
            encryptedDTO.setHave(transactionDTO.getHave());
            return encryptedDTO;
    }
    /**
     * Thêm 1 giao dịch được giải mã và mã hóa từ một đối tượng TransactionDTO vào cơ sở dữ liệu.
     *
     * @param transactionDTO Đối tượng TransactionDTO chứa thông tin giao dịch đã giải mã và mã hóa.
     * @throws Exception
     */
    public void insert(TransactionDTO transactionDTO) throws Exception {
            String decryptDestination = RSAUtils.decrypt(transactionDTO.getDestinationAccount());
            String decryptSourceAccount = RSAUtils.decrypt(transactionDTO.getSourceAccount());
            String decryptTransactionId = RSAUtils.decrypt(transactionDTO.getTransactionId());
            float decryptedInDebt = Float.parseFloat(String.valueOf(transactionDTO.getInDebt()));
            float decryptedHave = Float.parseFloat(String.valueOf(transactionDTO.getHave()));

            // Mã hóa AES account
            String encryptedSourceAccount = AESUtils.encrypt(decryptSourceAccount);
            String encryptedDestinationAccount = AESUtils.encrypt(decryptDestination);

            // Lưu giao dịch nợ (tài khoản nguồn)
            Transaction debitTransaction = new Transaction();
            debitTransaction.setTransactionId(decryptTransactionId);
            debitTransaction.setAccount(encryptedSourceAccount);
            debitTransaction.setInDebt(decryptedInDebt);
            debitTransaction.setHave(0.0f);
            debitTransaction.setTime(new Date());
            transactionRepository.save(debitTransaction);

            // Lưu giao dịch có (tài khoản đích)
            Transaction creditTransaction = new Transaction();
            creditTransaction.setTransactionId(decryptTransactionId);
            creditTransaction.setAccount(encryptedDestinationAccount);
            creditTransaction.setHave(decryptedHave);
            creditTransaction.setInDebt(0.0f);
            creditTransaction.setTime(new Date());
            transactionRepository.save(creditTransaction);
    }

}