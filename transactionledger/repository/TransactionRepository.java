package com.blockchain.transactionledger.repository;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.blockchain.transactionledger.entity.Block;

@Repository
public class TransactionRepository {

	public ArrayList<Block> ledger = new ArrayList<Block>();

	public void add(Block block) {
		ledger.add(block);		
	}

	public void remove(Block block) {
		ledger.remove(block);
	}

	public void update(Integer index, Block block) {
		ledger.set(index, block);
	}

	public Block getTransactionByTransactionId(Integer transactionId) {
		return ledger.get(transactionId);
	}
	
}
