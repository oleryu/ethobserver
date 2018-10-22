package xyz.oleryu.wallet.eth.observer.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import xyz.oleryu.wallet.eth.observer.dao.IMongoDBDao;

import java.util.List;

/**
 * Created by hengpu on 2018/9/28.
 */
@Repository("iMongoDBDao")
public class MongoDBDaoImpl<T> implements IMongoDBDao<T>{
    @Autowired
    private MongoTemplate mongoTemplate;

    public void insert(T t){
        mongoTemplate.insert(t);
    }
    public T findRecordByTxHash(String txHash,Class<T> clazz){
        Query query=new Query(Criteria.where("txHash").is(txHash));
        T t =  mongoTemplate.findOne(query , clazz);
        mongoTemplate.find(query , clazz);
        return t;
    }
    public List<T> findRecordListByTxHash(Class<T> clazz){

        Query query = new Query();
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC,"_id")));
        List<T> li = this.mongoTemplate.find(query, clazz);

        return li;


//        List<T> t= mongoTemplate.findAll(clazz);
//        return t;
    }
}
