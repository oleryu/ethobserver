package xyz.oleryu.wallet.eth.observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import xyz.oleryu.wallet.eth.observer.service.TxInputRecordService;
import xyz.oleryu.wallet.eth.observer.service.TxOutputRecordService;

import java.math.BigInteger;

/**
 * 观察 链上交易
 * @ oleryu.xyz
 */
@Component
//@ConfigurationProperties(prefix = "eth")
@Order(value = 1)
public class TransactionListener implements ApplicationRunner {

    @Value("${eth.rpcUrl}")
    private String  rpcUrl;
    @Autowired
    private TxInputRecordService txInputRecordService;
    @Autowired
    private TxOutputRecordService txOutputRecordService;

    @Override
    public void run(ApplicationArguments var1) throws Exception{
        System.out.println("TransactionListener!");
//        System.out.println("|ETH_RPC_URL|: " + rpcUrl);
//        Web3j web3j = Web3j.build(new HttpService("http://192.168.10.102:8545"));
        Web3j web3j = Web3j.build(new HttpService(rpcUrl));
        System.out.println(web3j);

        //获得到transactionHash后就可以到以太坊的网站上查询这笔交易的状态了
        web3j.transactionObservable().subscribe(tx -> {
            if(null == tx) {
                return;
            }
            //
            String txHash = tx.getHash();
            String bolckHash = tx.getBlockHash();
            String fromAddress = tx.getFrom();
            String toAddress = tx.getTo();
            BigInteger blockNumber = tx.getBlockNumber();
            String creates = tx.getCreates();

            BigInteger gas = tx.getGas();
            BigInteger gasPrice = tx.getGasPrice();

            BigInteger nonce = tx.getNonce();

            BigInteger value = tx.getValue();
            //

            TxInputRecord txInputRecord  = new TxInputRecord(txHash,
                    bolckHash,toAddress,fromAddress,blockNumber,creates,gas,gasPrice,nonce,value);
            TxOutputRecord txOuutRecord  = new TxOutputRecord(txHash,
                    bolckHash,fromAddress,toAddress,blockNumber,creates,gas,gasPrice,nonce,value);

            //存入数据库
            txInputRecordService.insert(txInputRecord);
            txOutputRecordService.insert(txOuutRecord);

        });
    }
}
