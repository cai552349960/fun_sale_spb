package cn.hft.funSale.service;

import cn.hft.funSale.domain.FunSale;

import java.util.List;

public interface IFunSaleService {
    public List<FunSale> findAll();

    public FunSale findById(Integer id);

    public void updateById(FunSale funSale);

    public void deleteById(Integer id);

    public void insert(FunSale funSale);
}
