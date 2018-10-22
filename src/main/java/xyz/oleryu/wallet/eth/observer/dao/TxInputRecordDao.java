package xyz.oleryu.wallet.eth.observer.dao;

import xyz.oleryu.wallet.eth.observer.TxInputRecord;

/**
 * Created by hengpu on 2018/9/27.
 */
public interface TxInputRecordDao {
    void insert(TxInputRecord txInputRecord);
    TxInputRecord findTxInputRecordByUserName(String txHash);

}
