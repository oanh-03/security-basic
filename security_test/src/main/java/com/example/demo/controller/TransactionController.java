package com.example.demo.controller;


import com.example.demo.dto.TransactionDTO;
import com.example.demo.service.impl.TransactionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Phuong Oanh22
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionServiceImpl transactionService;

    /**
     * Xử lý yêu cầu POST để mã hóa một đối tượng giao dịch.
     *
     * @param transactionDTO Đối tượng giao dịch cần được mã hóa.
     * @return ResponseEntity chứa đối tượng giao dịch đã được mã hóa nếu thành công, hoặc trả về mã lỗi 500 nếu có lỗi xảy ra trong quá trình mã hóa.
     */
    @PostMapping("/encrypt")
    public ResponseEntity<TransactionDTO> encryptTransaction(@RequestBody TransactionDTO transactionDTO) {
        try {
            TransactionDTO encryptedDTO = transactionService.encrypt(transactionDTO);
            return ResponseEntity.ok(encryptedDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Xử lý yêu cầu POST để thêm một đối tượng giao dịch vào cơ sở dữ liệu.
     *
     * @param transactionDTO Đối tượng giao dịch cần được thêm vào cơ sở dữ liệu.
     * @return ResponseEntity
     */
    @PostMapping("/insert")
    public ResponseEntity<?> insertTransaction(@RequestBody TransactionDTO transactionDTO) {
        try {
            transactionService.insert(transactionDTO);
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
