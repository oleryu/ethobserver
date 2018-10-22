package xyz.oleryu.wallet.eth.observer.controller;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
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
@Controller
@RequestMapping("/txInOrPuts")
public class ListController {

    @Autowired
    private TxInputRecordService txInputRecordService;
    @Autowired
    private TxOutputRecordService txOutputRecordService;

//
////    @RequestMapping("/txInputRecord/{txHash}")
////    public TxInputRecord findTxInputRecord(@PathVariable String txHash) {
////        TxInputRecord txInputRecords = txInputRecordService.findTxInputRecordByUserName(txHash);
////        return txInputRecords;
////    }
//

    @RequestMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> list(ModelAndView mav, HttpServletRequest request, String txHash) throws JSONException {
        List<TxInputRecord> txIn = txInputRecordService.findTxInputRecordListByUserName(1,20);
//        TxInputRecord txInputRecord  = new TxInputRecord("12345678","345","667","111", BigInteger.ONE,"777",BigInteger.ONE,BigInteger.ONE,BigInteger.ONE,BigInteger.ONE);
//        TxInputRecord txInputRecord2  = new TxInputRecord("123456789","345","667","111", BigInteger.ONE,"777",BigInteger.ONE,BigInteger.ONE,BigInteger.ONE,BigInteger.ONE);
//        List<TxInputRecord> ls=new ArrayList<TxInputRecord>();
//        ls.add(txInputRecord);
//        ls.add(txInputRecord2);
//        TxOutputRecord txOutputRecord  = new TxOutputRecord("1234","345","667","111", BigInteger.ONE,"777",BigInteger.ONE,BigInteger.ONE,BigInteger.ONE,BigInteger.ONE);
//        TxOutputRecord txOutputRecord2  = new TxOutputRecord("123456789121212","345","667","111", BigInteger.ONE,"777",BigInteger.ONE,BigInteger.ONE,BigInteger.ONE,BigInteger.ONE);
//
//        List<TxOutputRecord>out=new ArrayList<TxOutputRecord>();
//        out.add(txOutputRecord);
//        out.add(txOutputRecord2);
        List<TxInputRecord> txOut = txOutputRecordService.findTxOutputRecordListByUserName(1,20);

        Map<String, Object> map = new HashMap<>();
        map.put("txIn", txIn);
        map.put("txOut", txOut);

        return map;
    }

    @RequestMapping(value = "/txIninfo")
    public String txIninfo( String txHash,Model model) {
        TxInputRecord txInputRecord  = txInputRecordService.findTxInputRecordByUserName(txHash);
//        System.out.print("1111");
//        TxInputRecord txInputRecord  = new TxInputRecord("12345678","345","667","111", BigInteger.ONE,"777",BigInteger.ONE,BigInteger.ONE,BigInteger.ONE,BigInteger.ONE);
        model.addAttribute("txInfo", txInputRecord);
        return "/info";
    }
    @RequestMapping(value = "/txOutinfo")
    public String txOutinfo( String txHash,Model model) {
        TxOutputRecord txOutputRecord  = txOutputRecordService.findTxOutputRecordByUserName(txHash);
//        System.out.print("2222");
//        TxInputRecord txInputRecord  = new TxInputRecord("12345678","345","667","111", BigInteger.ONE,"777",BigInteger.ONE,BigInteger.ONE,BigInteger.ONE,BigInteger.ONE);
        model.addAttribute("txInfo", txOutputRecord);
        return "/info";
    }

}
