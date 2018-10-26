package xyz.oleryu.wallet.eth.observer.service.impl;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.oleryu.wallet.eth.observer.PendingTxRecord;
import xyz.oleryu.wallet.eth.observer.TxRecord;
import xyz.oleryu.wallet.eth.observer.dao.IMongoDBDao;
import xyz.oleryu.wallet.eth.observer.service.TxRecordService;

import java.util.List;

/**
 * Created by hengpu on 2018/9/27.
 */
@Service("txInputRecordService")
public class TxRecordServiceimpl implements TxRecordService {
    @Autowired
    private IMongoDBDao iMongoDBDao;

    public void insert(TxRecord txRecord){

//        txInputRecordDao.insert(txRecord);
        iMongoDBDao.insert(txRecord);
    }

    public void insertPending(PendingTxRecord txRecord){

//        txInputRecordDao.insert(txRecord);
        iMongoDBDao.insert(txRecord);
    }


    public TxRecord getTxRecordByHash(String txHash){
        return (TxRecord)iMongoDBDao.getTxRecordByHash(txHash,TxRecord.class);
    }

    public List<TxRecord> lstTxRecord(Integer page, Integer pageSize,String address,int flag){

        List<TxRecord> list = iMongoDBDao.lstTxRecord(TxRecord.class,address,flag);
        List<TxRecord> pageList = getPageList(list,page,pageSize);
        return pageList;
    }
    private List<TxRecord> getPageList(List sourceList, int page, int pageSize) {
        int beginIndex = (page - 1) * pageSize;
        int endIndex = beginIndex + pageSize - 1;

        List<TxRecord> result = Lists.newArrayList();
        if (sourceList.size() > 0) {
            //全部记录集合 小于等于分页大小
            if (sourceList.size() <= pageSize) {
                result = sourceList;
            } else {
                //取出对应元素添加至
                for (int i = beginIndex; i <= endIndex && i < sourceList.size(); i++) {
                    result.add((TxRecord)sourceList.get(i));
                }
            }
        }
        return result;
    }



}
