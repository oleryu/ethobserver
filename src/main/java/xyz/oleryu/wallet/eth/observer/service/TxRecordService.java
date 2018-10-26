package xyz.oleryu.wallet.eth.observer.service;

import xyz.oleryu.wallet.eth.observer.PendingTxRecord;
import xyz.oleryu.wallet.eth.observer.TxRecord;

import java.util.List;

/**
 * Created by hengpu on 2018/9/27.
 */
public interface TxRecordService {
    void insert(TxRecord txRecord);
    void insertPending(PendingTxRecord txRecord);
    TxRecord getTxRecordByHash(String txHash);
    List<TxRecord> lstTxRecord(Integer page, Integer pageSize,String address,int flag);

    }
