package com.libraryManagement.serviceImpl;

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
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	UserRepository userRepository;
	@Autowired
	TransactionRepositry transactionRepositry;
	@Override
	public void createTransaction(TransactionDto transactionDto) throws TransactionServiceException {
		
		try {
		Transaction transaction = new Transaction();
		transaction.setAmount(transactionDto.getAmount());
		if(transactionDto.getUserId()==0)
		{
			throw new TransactionServiceException(400, "User Id Invalid please Provide Valid User Id");
		}
		UserDetails userDetails = userRepository.findById(transactionDto.getUserId()).get();
		transaction.setUserDetails(userDetails);
		transactionRepositry.save(transaction);
		}
		catch(TransactionServiceException e)
		{
			throw new TransactionServiceException(400,e.getMessage());
		}catch(Exception e){
			throw new TransactionServiceException(400,"Transaction Failed Please Try Again");
			
		}
	}

}
