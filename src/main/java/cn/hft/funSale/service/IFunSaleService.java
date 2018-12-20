package cn.hft.funSale.service;

import cn.hft.funSale.domain.FunSale;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IFunSaleService {
    public List<FunSale> findAll();

    public Page<FunSale> findPage( Integer page, Integer size);

    public FunSale findById(Integer id);

    public void updateById(FunSale funSale);

    public void deleteById(Integer id);

    public void insert(FunSale funSale);
}
