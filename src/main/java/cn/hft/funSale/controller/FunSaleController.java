package cn.hft.funSale.controller;

import cn.hft.funSale.domain.FunSale;
import cn.hft.funSale.service.IFunSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/FunSale")
public class FunSaleController {
    @Autowired
    private IFunSaleService funSaleService;
    @RequestMapping("/findAll")
    public List<FunSale> findAll() {
        return funSaleService.findAll();
    }

    @RequestMapping("/findOne")
    public FunSale findOne(Integer saleId) {
        return funSaleService.findById(saleId);
    }

    @RequestMapping("/updateById")
    public void UpdateById(FunSale funSale) {
        funSaleService.updateById(funSale);
    }
    @RequestMapping("/delectById")
    public void delectById(Integer saleId) {
        funSaleService.deleteById(saleId);
    }
    @RequestMapping("/insert")
    public void insert(FunSale funSale) {
        funSaleService.insert(funSale);
    }

}
