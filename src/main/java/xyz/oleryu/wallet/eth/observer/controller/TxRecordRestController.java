package xyz.oleryu.wallet.eth.observer.controller;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.oleryu.wallet.eth.observer.TxInputRecord;
import xyz.oleryu.wallet.eth.observer.TxOutputRecord;
import xyz.oleryu.wallet.eth.observer.service.TxInputRecordService;
import xyz.oleryu.wallet.eth.observer.service.TxOutputRecordService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hengpu on 2018/9/28.
 */
@RestController
@RequestMapping("/txInOrPut")
public class TxRecordRestController {

    @Autowired
    private TxInputRecordService txInputRecordService;
    @Autowired
    private TxOutputRecordService txOutputRecordService;


    @RequestMapping("/txInputRecord/{txHash}")
    public TxInputRecord findTxInputRecord(@PathVariable String txHash) {
        TxInputRecord txInputRecord = txInputRecordService.findTxInputRecordByUserName(txHash);
        return txInputRecord;
    }

    @RequestMapping("/txtext")
    public List txtext() {
        List list = txInputRecordService.findTxInputRecordListByUserName(1,3);
        return list;
    }

    @RequestMapping("/txOutputRecord/{txHash}")
    public TxOutputRecord findTxOutputRecord(@PathVariable String txHash) {
        TxOutputRecord txOutputRecord = txOutputRecordService.findTxOutputRecordByUserName(txHash);
        return txOutputRecord;
    }

    @RequestMapping("/txInputRecordList")
    public Map txInputRecordList(HttpServletRequest httpServletRequest) throws JSONException {
        Integer page = Integer.parseInt(httpServletRequest.getParameter("page"));
        Integer pageSize = Integer.parseInt(httpServletRequest.getParameter("pageSize"));
        List list = txInputRecordService.findTxInputRecordListByUserName(page,pageSize);
        Integer totalPage=list.size()==0?0:Double.valueOf(Math.ceil(Integer.valueOf(list.size()).doubleValue() / pageSize.doubleValue())).intValue();
        //返回体..
        Map jsonObj=new HashMap<>();
        jsonObj.put("list",list);
        jsonObj.put("totalPage",totalPage);
        return jsonObj;
    }
    @RequestMapping("/txOutputRecordList")
    public Map txOutputRecordList(HttpServletRequest httpServletRequest) throws JSONException {
        Integer page = Integer.parseInt(httpServletRequest.getParameter("page"));
        Integer pageSize = Integer.parseInt(httpServletRequest.getParameter("pageSize"));
        List list = txOutputRecordService.findTxOutputRecordListByUserName(page,pageSize);
        Integer totalPage=list.size()==0?0:Double.valueOf(Math.ceil(Integer.valueOf(list.size()).doubleValue() / pageSize.doubleValue())).intValue();

        //返回体
        Map jsonObj=new HashMap<>();
        jsonObj.put("list",list);
        jsonObj.put("totalPage",totalPage);
        return jsonObj;    }
}
