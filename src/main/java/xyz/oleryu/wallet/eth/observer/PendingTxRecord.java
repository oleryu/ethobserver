package xyz.oleryu.wallet.eth.observer;

import lombok.Data;

import java.math.BigInteger;

/**
 * 交易转入 记录
 * @ oleryu.xyz
 */
@Data
public class PendingTxRecord {

    private static final long serialVersionUID = 1L;

//    // @id这个注解来对应mongo的_id这个字段
//    @Id
//    private String _id;

    private String txHash;
    private String bolckHash;
    private BigInteger blockNumber;
    private String toAddress;
    private String fromAddress;
    private String creates;
    private BigInteger gas;
    private BigInteger gasPrice;
    private BigInteger nonce;
    private BigInteger value;

    public PendingTxRecord(String txHash,
                           String bolckHash,
                           String toAddress,
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
        this.toAddress = toAddress;
        this.blockNumber = blockNumber;
        this.creates = creates;
        this.gas = gas;
        this.gasPrice = gasPrice;
        this.nonce = nonce;
        this.value = value;
    }

    @Override
    public String toString() {
        return "TxRecord{" +
                "txHash='" + txHash + '\'' +
                ", bolckHash='" + bolckHash + '\'' +
                ", blockNumber=" + blockNumber +
                ", address='" + toAddress + '\'' +
                ", fromAddress='" + fromAddress + '\'' +
                ", creates='" + creates + '\'' +
                ", gas=" + gas +
                ", gasPrice=" + gasPrice +
                ", nonce=" + nonce +
                ", value=" + value +
                '}';
    }
}
