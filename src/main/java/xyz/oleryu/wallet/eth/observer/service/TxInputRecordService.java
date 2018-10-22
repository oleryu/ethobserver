package xyz.oleryu.wallet.eth.observer.service;

import xyz.oleryu.wallet.eth.observer.TxInputRecord;

import java.util.List;

/**
 * Created by hengpu on 2018/9/27.
 */
public interface TxInputRecordService {
    void insert(TxInputRecord txInputRecord);
    TxInputRecord findTxInputRecordByUserName(String txHash);
    List findTxInputRecordListByUserName(Integer page, Integer pageSize);
}
