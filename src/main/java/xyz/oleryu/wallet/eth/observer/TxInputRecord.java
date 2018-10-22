package xyz.oleryu.wallet.eth.observer;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.math.BigInteger;

/**
 * 交易转入 记录
 * @ oleryu.xyz
 */
@Data
public class TxInputRecord {

    private static final long serialVersionUID = 1L;

//    // @id这个注解来对应mongo的_id这个字段
//    @Id
//    private String _id;

    private String txHash;
    private String bolckHash;
    private BigInteger blockNumber;
    private String address;
    private String fromAddress;
    private String creates;
    private BigInteger gas;
    private BigInteger gasPrice;
    private BigInteger nonce;
    private BigInteger value;

    public TxInputRecord(String txHash,
                         String bolckHash,
                         String address,
                         String fromAddress,
                         BigInteger blockNumber,
                         String creates,
                         BigInteger gas,
                         BigInteger gasPrice,
                         BigInteger nonce,
                         BigInteger value) {
        this.txHash = txHash;
        this.bolckHash = bolckHash;
        this.fromAddress = fromAddress;
        this.address = address;
        this.blockNumber = blockNumber;
        this.creates = creates;
        this.gas = gas;
        this.gasPrice = gasPrice;
        this.nonce = nonce;
        this.value = value;
    }
}
