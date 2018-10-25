package xyz.oleryu.wallet.eth.observer.controller;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import xyz.oleryu.wallet.eth.observer.TxRecord;
import xyz.oleryu.wallet.eth.observer.service.TxRecordService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hengpu on 2018/9/28.
 */
@Controller
@RequestMapping("/api")
public class TxApiController {

    @Autowired
    private TxRecordService txRecordService;

    @RequestMapping(value = "/txs")
    @ResponseBody
    public Map<String, Object> list(ModelAndView mav, HttpServletRequest request, String txHash) throws JSONException {
        List<TxRecord> txIn = txRecordService.
                lstTxRecord(1,20,null,0);

        Map<String, Object> map = new HashMap<>();
        map.put("txs", txIn);

        return map;
    }

    @RequestMapping(value = "/tx/info")
    public String txIninfo( String txHash,Model model) {
        TxRecord txRecord = txRecordService.getTxRecordByHash(txHash);
        model.addAttribute("txInfo", txRecord);
        return "/info";
    }

    @RequestMapping("/txs/{address}")
    public @ResponseBody Map txRecordAllByAddress(@PathVariable String address,HttpServletRequest httpServletRequest) throws JSONException {
        System.out.println(address);

        String pageNum = httpServletRequest.getParameter("page");
        if(pageNum == null) {
            pageNum = "0";
        }
        Integer page = Integer.parseInt(pageNum);
        String pageSizeNum = httpServletRequest.getParameter("pageSize");
        if(pageSizeNum == null) {
            pageSizeNum = "50";
        }
        Integer pageSize = Integer.parseInt(pageSizeNum);


        List<TxRecord> list = txRecordService.lstTxRecord(page,pageSize,address,0);
        Integer totalPage=list.size()==0?0:Double.valueOf(Math.ceil(Integer.valueOf(list.size()).doubleValue() / pageSize.doubleValue())).intValue();
        //返回体..
        Map jsonObj=new HashMap();
        jsonObj.put("list",list);
        jsonObj.put("totalPage",totalPage);
        return jsonObj;
    }

    @RequestMapping("/txs/{address}/{flag}")
    public @ResponseBody Map txRecordIOByAddress(@PathVariable Integer flag,@PathVariable String address,HttpServletRequest httpServletRequest) throws JSONException {
        String pageNum = httpServletRequest.getParameter("page");
        if(pageNum == null) {
            pageNum = "0";
        }
        Integer page = Integer.parseInt(pageNum);
        String pageSizeNum = httpServletRequest.getParameter("pageSize");
        if(pageSizeNum == null) {
            pageSizeNum = "50";
        }
        Integer pageSize = Integer.parseInt(pageSizeNum);

        List<TxRecord> list = txRecordService.lstTxRecord(page,pageSize,address,flag);
        Integer totalPage=list.size()==0?0:Double.valueOf(Math.ceil(Integer.valueOf(list.size()).doubleValue() / pageSize.doubleValue())).intValue();
        //返回体..
        Map jsonObj=new HashMap();
        jsonObj.put("list",list);
        jsonObj.put("totalPage",totalPage);
        return jsonObj;
    }

}
