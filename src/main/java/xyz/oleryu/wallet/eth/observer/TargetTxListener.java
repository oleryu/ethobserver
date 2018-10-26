package xyz.oleryu.wallet.eth.observer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import rx.Subscription;
import xyz.oleryu.wallet.eth.observer.service.TxRecordService;

import java.io.IOException;
import java.math.BigInteger;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 观察 链上交易
 * @ oleryu.xyz
 */
@Component
@Slf4j
@Order(value = 1)
public class TargetTxListener implements ApplicationRunner {

    @Value("${eth.rpcUrl}")
    private String  rpcUrl;
    @Autowired
    private TxRecordService txInputRecordService;

    @Override
    public void run(ApplicationArguments var1) {
        log.info("TargetTxListenerStart!");
        //校验，校验
        //if() {}
        boolean subscribeOn = false;
        try {
            int istartpos = rpcUrl.indexOf("//");
            int iendpos = rpcUrl.lastIndexOf(":");
            String hostname = rpcUrl.substring(istartpos+2,iendpos);
            Integer port = Integer.parseInt(rpcUrl.substring(iendpos+1));
            int timeout = 1000;
            Subscription subscription = null;
            while(true) {
                try {
                    if(testTarget(hostname,port,1000)  == 0) {
                        if(!subscribeOn) {
                            subscription = beginsubscribeTx(hostname,port,timeout);
                            if(subscription!=null) {
                                if(!subscription.isUnsubscribed()) {
                                    subscribeOn = true;
                                } else {
                                    subscription.unsubscribe();
                                }
                            }
                        }
                    } else {
                        if(subscription != null) {
                            log.info("unsubscribe["+hostname+":"+port+"],timeout="+timeout);
                            subscribeOn=false;
                            try{subscription.unsubscribe();subscription=null;}catch(Exception e) {}
                        }
                        log.info("TestTarget["+hostname+":"+port+"],timeout="+timeout);
                    }
                    Thread.sleep(1000);
                } catch(Exception e) {
                    if(subscription != null) {
                        subscription.unsubscribe();
                    }
                    e.printStackTrace();
                }
            }
        } catch(Exception e) {
            log.info("TargetTxListener Exception" + e.getMessage());

        } finally {
            log.info("TargetTxListener Finally!");
        }
    }

    public Subscription beginsubscribeTx(String hostname,Integer port,Integer timeout) {
        log.info("TargetTxListener Beginning!");
            Subscription subscription  = subscribeTx();

            return subscription;


    }

    public static int testTarget(String hostname,Integer port,Integer timeout){

        try {
            Socket server = new Socket();
            InetSocketAddress address = new InetSocketAddress(hostname,port);
            server.connect(address, timeout);
            server.close();
            server.close();
            return 0;
        }catch (UnknownHostException e) {
            return 1;
        } catch (IOException e) {
            return 2;
        }
    }

    private Subscription subscribeTx() {
        try {
            Web3j web3j = Web3j.build(new HttpService(rpcUrl));

            //获得到transactionHash后就可以到以太坊的网站上查询这笔交易的状态了
            //
                Subscription subscription =web3j.transactionObservable().subscribe(tx -> {
                log.info("transactionObservable recv>"+tx);
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

                TxRecord txRecord = new TxRecord(txHash,
                        bolckHash,toAddress,fromAddress,blockNumber,creates,gas,gasPrice,nonce,value);

                log.info("transactionObservable txRecord>"+txRecord.toString());

                txInputRecordService.insert(txRecord);

            });

            return subscription;
        } catch(Exception e) {
            log.error("transactionObservable recv " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
