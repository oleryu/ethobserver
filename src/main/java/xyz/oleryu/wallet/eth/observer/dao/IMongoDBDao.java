package xyz.oleryu.wallet.eth.observer.dao;

import java.util.List;

/**
 * Created by hengpu on 2018/9/28.
 */
public interface IMongoDBDao<T> {
    void insert(T c);
    T getTxRecordByHash(String txHash, Class<T> clazz);
    List<T> lstTxRecord(Class<T> clazz,String address,int flag);
}
