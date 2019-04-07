package com.blockchain.transactionledger.service;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blockchain.transactionledger.entity.Block;
import com.blockchain.transactionledger.repository.TransactionRepository;

@Service
public class Blockchain {

    @Autowired
    private TransactionRepository repository;
    
    public static int difficulty = 4;
    static Logger logger = Logger.getLogger(Blockchain.class.getName());

    
    public ArrayList<Block> getAllTransactions() {
    	return repository.ledger;
	}
    
    public ResponseEntity<String> addTransaction(Block block) {
    	block.previousHash=repository.ledger.get(repository.ledger.size()-1).hash;
    	block.transaction.transactionId=repository.ledger.get(repository.ledger.size()-1).transaction.transactionId++;
    	repository.add(block);
    	repository.ledger.get(repository.ledger.size()-1).mineBlock(difficulty);
    	
    	if(!isChainValid()) {
    		return new ResponseEntity<String>("Chain is invalid",HttpStatus.NOT_ACCEPTABLE);	
    	}
    	return new ResponseEntity<String>(HttpStatus.OK);
    	
    }
    
    public ResponseEntity<String> updateTransaction(Block block) {
    	
    	repository.update(block.transaction.transactionId,block);
    	repository.ledger.get(repository.ledger.size()-1).mineBlock(difficulty);
    	
    	if(!isChainValid()) {
    		return new ResponseEntity<String>("Chain is invalid",HttpStatus.NOT_ACCEPTABLE);	
    	}
    	return new ResponseEntity<String>(HttpStatus.OK);
    	
    	
	}
    
    public ResponseEntity<String> deleteTransaction(Block block) {
		repository.remove(block);	
    	if(!isChainValid()) {
    		return new ResponseEntity<String>("Chain is invalid",HttpStatus.NOT_ACCEPTABLE);	
    	}
    	return new ResponseEntity<String>(HttpStatus.OK);
    	
	}
    

    public Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        //loop through blockchain to check hashes:
        for(int i=1; i < repository.ledger.size(); i++) {
            currentBlock = repository.ledger.get(i);
            previousBlock = repository.ledger.get(i-1);
            //compare registered hash and calculated hash:
            if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
                logger.info("Current Hashes not equal");
                return false;
            }
            //compare previous hash and registered previous hash
            if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
                logger.info("Previous Hashes not equal");
                return false;
            }
            //check if hash is solved
            if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
                logger.info("This block hasn't been mined");
                return false;
            }
        }
        return true;
    }

	public Block getTransactionByTransactionId(Integer transactionId) {
		return repository.getTransactionByTransactionId(transactionId);
	}

}