package com.blockchain.transactionledger;

import java.util.logging.Logger;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.blockchain.transactionledger.entity.Block;
import com.blockchain.transactionledger.entity.Transaction;
import com.blockchain.transactionledger.repository.TransactionRepository;
import com.blockchain.transactionledger.service.Blockchain;

@SpringBootApplication
public class TransactionLedgerApplication {

	
    static Logger logger = Logger.getLogger(TransactionLedgerApplication.class.getName());
	public static void main(String[] args) {
		SpringApplication.run(TransactionLedgerApplication.class, args);
	
	}

	@Bean
	public CommandLineRunner populateData(TransactionRepository repository) {
		
		int difficulty=4;
		
		Block genesisBlock =new Block(new Transaction("Shubham","Poonam",100,"Rs 100 has been sent"), "0");
        repository.ledger.add(genesisBlock);
        logger.info("Trying to Mine block 1... ");        
		repository.ledger.get(0).mineBlock(difficulty);

        Block secondBlock =new Block(new Transaction("Shubham","Poonam",200,"Rs 200 has been sent"),repository.ledger.get(repository.ledger.size()-1).hash);
        repository.add(secondBlock);
        logger.info("Trying to Mine block 2... ");
        repository.ledger.get(1).mineBlock(difficulty);

        repository.add(new Block(new Transaction("Shubham","Poonam",300,"Rs 300 has been sent"),repository.ledger.get(repository.ledger.size()-1).hash));
        logger.info("Trying to Mine block 3... ");
        repository.ledger.get(2).mineBlock(difficulty);

        repository.ledger.add(new Block(new Transaction("Shubham","Poonam",400,"Rs 400 has been sent"),repository.ledger.get(repository.ledger.size()-1).hash));
        logger.info("Trying to Mine block 4... ");
        repository.ledger.get(3).mineBlock(difficulty);

        return (args) -> {

        };

	}
}
