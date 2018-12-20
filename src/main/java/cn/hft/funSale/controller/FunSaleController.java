package cn.hft.funSale.controller;

import cn.hft.funSale.domain.FunSale;
import cn.hft.funSale.domain.Result;
import cn.hft.funSale.service.IFunSaleService;
import org.apache.commons.io.FileUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/funSale")
public class FunSaleController {
    @Autowired
    private IFunSaleService funSaleService;

    private static boolean flag = true;

    @RequestMapping("/findAll")
    @ResponseBody
    public Page<FunSale> findAll(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        File file = new File("F:\\IdeaProjects\\fun_sale_ssm3\\fun_sale_web\\src\\main\\webapp\\funSale.xml");
        if (!file.exists()) {
            if (flag) {
                List<FunSale> all = funSaleService.findAll();
                boolean f = createXml(all);
                if (f) {
                    flag = false;
                }
            }
        }

        Page<FunSale> all = funSaleService.findPage( pageNum, pageSize);
        List<FunSale> content = all.getContent();
        System.out.println(content.get(0));
        return all;
    }

    @RequestMapping("/findBySaleId")
    @ResponseBody
    public FunSale findOne(@RequestParam(value = "saleId") Integer saleId) {
        return funSaleService.findById(saleId);

    }

    @RequestMapping("/updateByFunSale")
    @ResponseBody
    public Result UpdateById(@RequestBody FunSale funSale) {
        try {

            funSale.setUpdateTime(new Date());
            funSaleService.updateById(funSale);
            return new Result(1, "修改成功");

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(0, "修改失败");

        }
    }

    @RequestMapping("/deleteById")
    @ResponseBody
    public Result deleteById(@RequestParam(value = "saleId") Integer saleId) {

        try {
            funSaleService.deleteById(saleId);
            return new Result(1, "删除成功");
        } catch (Exception e) {

            e.printStackTrace();
            return new Result(0, "删除失败");

        }
    }

    @RequestMapping("/insert")
    @ResponseBody
    public Result insert(@RequestBody FunSale funSale) {
        System.out.println(funSale);
        try {


            funSaleService.insert(funSale);
            return new Result(1, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(0, "添加失败");

        }
    }

    @RequestMapping("/createXml")
    @ResponseBody
    public ResponseEntity createXml() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        File file = new File("F:\\IdeaProjects\\fun_sale_ssm3\\fun_sale_web\\src\\main\\webapp\\funSale.xml");

        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "funSale.xml");

        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);

    }

    public boolean createXml(List<FunSale> list) {
        boolean b = false;
        Set hset = new HashSet<>();
        Document doc = DocumentHelper.createDocument();//创建document
        Element schoolEle = doc.addElement("funSale");//添加根元素
        schoolEle.addComment("文档的根funSale已经创建。");//添加注释
        List<FunSale> al = list;

        for (int i = 0; i < al.size(); i++) {
            FunSale funSale = al.get(i);
            hset.add(funSale.getSaleID());
        }
        for (Iterator<Integer> it = hset.iterator(); it.hasNext(); ) {
            Integer saleId = it.next();
            Element classEle = schoolEle.addElement("saleId");
            classEle.addAttribute("saleId", saleId + "");
            for (int i = 0; i < al.size(); i++) {
                FunSale funSale = al.get(i);
                if (saleId == funSale.getSaleID()) {
                    Element UserEle = classEle.addElement("funSale");
                    UserEle.addElement("saleID").addText(funSale.getSaleID() + "");
                    UserEle.addElement("saleSubject").addText(funSale.getSaleSubject() == null ? "无" : funSale.getSaleSubject() + "");
                    UserEle.addElement("buildName").addText(funSale.getBuildName() == null ? "无" : funSale.getBuildName() + "");
                    UserEle.addElement("tradeAddr").addText(funSale.getTradeAddr() == null ? "无" : funSale.getTradeAddr() + "");
                    UserEle.addElement("saleRoom").addText(funSale.getSaleRoom() + "");
                    UserEle.addElement("saleInnerarea").addText(funSale.getSaleInnerarea() + "");
                    UserEle.addElement("regionName").addText(funSale.getRegionName() + "");
                    UserEle.addElement("sectionName").addText(funSale.getSectionName() + "");
                    UserEle.addElement("updateTime").addText(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(funSale.getUpdateTime()));
                    if (funSale.getSaleTotalPrice() == null) {
                        funSale.setSaleTotalPrice(new BigDecimal(0));
                    }
                    UserEle.addElement("saleTotalPrice").addText(funSale.getSaleTotalPrice().toString());
                    if (funSale.getSaleUnitPrice() == null) {
                        funSale.setSaleUnitPrice(new BigDecimal(0));
                    }
                    UserEle.addElement("saleUnitPrice").addText(funSale.getSaleUnitPrice().toString());
                }
            }
        }

        try {
            /*自己查帮助文档
             * OutputFormat format=new OutputFormat("  ",true,"gb2312");
             */
            /*
             * 创建一个漂亮的打印格式的OutputFormat
             * 可以通过setEncoding来设置其传输字符串，默认为utf-8
             */
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("utf-8");
   /*创建缩进格式的OutputFormat
   format = OutputFormat.createCompactFormat();
   */
            File file = new File("F:\\IdeaProjects\\fun_sale_spb\\src\\main\\resources\\static\\funSale.xml");
            if (file.exists()) {
                file.createNewFile();
            }
            XMLWriter writer = new XMLWriter(new FileWriter(file), format);
            writer.write(doc);
            writer.close();
            b = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }


}
