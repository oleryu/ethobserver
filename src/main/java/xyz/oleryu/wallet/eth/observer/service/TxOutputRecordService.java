package xyz.oleryu.wallet.eth.observer.service;

import xyz.oleryu.wallet.eth.observer.TxOutputRecord;

import java.util.List;

/**
 * Created by hengpu on 2018/9/27.
 */
public interface TxOutputRecordService {
    void insert(TxOutputRecord txOutputRecord);
    TxOutputRecord findTxOutputRecordByUserName(String txHash);
    List findTxOutputRecordListByUserName(Integer page, Integer pageSize);

}
