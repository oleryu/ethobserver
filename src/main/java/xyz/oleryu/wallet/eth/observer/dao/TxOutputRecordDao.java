package xyz.oleryu.wallet.eth.observer.dao;

import xyz.oleryu.wallet.eth.observer.TxOutputRecord;

/**
 * Created by hengpu on 2018/9/27.
 */
public interface TxOutputRecordDao {
    void insert(TxOutputRecord txOutputRecord);
    TxOutputRecord findTxOutputRecordByUserName(String txHash);
}
