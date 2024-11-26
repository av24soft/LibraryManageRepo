package com.libraryManagement.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryManagement.customExceptionHandling.TransactionServiceException;
import com.libraryManagement.dto.TransactionDto;
import com.libraryManagement.entity.Transaction;
import com.libraryManagement.entity.UserDetails;
import com.libraryManagement.repository.TransactionRepositry;
import com.libraryManagement.repository.UserRepository;
import com.libraryManagement.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	TransactionRepositry transactionRepositry;

	private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

	@Override
	public void createTransaction(TransactionDto transactionDto) throws TransactionServiceException {

		try {
			logger.info("Creating transaction for user ID: {}", transactionDto.getUserId());

			Transaction transaction = new Transaction();
			transaction.setAmount(transactionDto.getAmount());
			if (transactionDto.getUserId() == 0) {
				logger.error("Invalid user ID: {}", transactionDto.getUserId());
				throw new TransactionServiceException(400, "User Id Invalid please Provide Valid User Id");
			}
			UserDetails userDetails = userRepository.findById(transactionDto.getUserId()).get();
			transaction.setUserDetails(userDetails);
			transactionRepositry.save(transaction);
		} catch (TransactionServiceException e) {
			logger.error("Transaction creation failed: {}", e.getMessage());
			throw new TransactionServiceException(400, e.getMessage());
		} catch (Exception e) {
			logger.error("Transaction failed due to unexpected error: {}", e.getMessage());
			throw new TransactionServiceException(400, "Transaction Failed Please Try Again");

		}
	}

}
