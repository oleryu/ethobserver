package xyz.oleryu.wallet.eth.observer.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import xyz.oleryu.wallet.eth.observer.TxInputRecord;
import xyz.oleryu.wallet.eth.observer.dao.TxInputRecordDao;

/**
 * Created by hengpu on 2018/9/27.
 */
@Repository("txInputRecordDao")
public class TxInputRecordDaoImpl implements TxInputRecordDao{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void insert(TxInputRecord txInputRecord) {
        mongoTemplate.insert(txInputRecord);
    }

    @Override
    public TxInputRecord findTxInputRecordByUserName(String txHash) {
        Query query=new Query(Criteria.where("txHash").is(txHash));
        TxInputRecord user =  mongoTemplate.findOne(query , TxInputRecord.class);
        return user;
    }
}
