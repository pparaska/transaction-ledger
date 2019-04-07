package com.blockchain.transactionledger.entity;

import java.util.Date;
import java.util.logging.Logger;

import com.blockchain.transactionledger.util.StringUtil;

public class Block {

    public Transaction transaction;
    public String previousHash;
    public long timeStamp;
    public	 String hash;
    private int nonce;

    static Logger logger = Logger.getLogger(Block.class.getName());

    public Block(Transaction transaction,String previousHash ) {
        this.transaction = transaction;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash(); //Making sure we do this after we set the other values.
    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0"
        while(!hash.substring( 0, difficulty).equals(target)) {
            nonce ++;
            hash = calculateHash();
        }
        logger.info("Block Mined!!! : " + hash);
    }

    public String calculateHash() {
        String calculatedhash = StringUtil.applySha256(
        				previousHash +
                        Long.toString(timeStamp) +
                        Integer.toString(nonce) +
                        transaction
        );
        return calculatedhash;
    }



}