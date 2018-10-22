package xyz.oleryu.wallet.eth.observer.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import xyz.oleryu.wallet.eth.observer.TxOutputRecord;
import xyz.oleryu.wallet.eth.observer.dao.TxOutputRecordDao;

/**
 * Created by hengpu on 2018/9/27.
 */
@Repository("txOutputRecordDao")
public class TxOutputRecordDaoImpl implements TxOutputRecordDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void insert(TxOutputRecord txInputRecord) {
        mongoTemplate.insert(txInputRecord);
    }

    @Override
    public TxOutputRecord findTxOutputRecordByUserName(String txHash) {
        Query query=new Query(Criteria.where("txHash").is(txHash));
        TxOutputRecord user =  mongoTemplate.findOne(query , TxOutputRecord.class);
        return user;
    }
}
