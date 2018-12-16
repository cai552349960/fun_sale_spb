package cn.hft.funSale.service.impl;

import cn.hft.funSale.Dao.IFunSaleDao;
import cn.hft.funSale.domain.FunSale;
import cn.hft.funSale.service.IFunSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class FunSaleServiceImpl implements IFunSaleService {
    @Autowired
    private IFunSaleDao funSaleDao;
    @Override
    public List<FunSale> findAll() {
        return funSaleDao.findAll();
    }

    @Override
    public FunSale findById(Integer id) {
        Optional<FunSale> byId = funSaleDao.findById(id);
        FunSale funSale = byId.get();
        return funSale;
    }

    @Override
    public void updateById(FunSale funSale) {
        funSaleDao.updateById(funSale);
    }

    @Override
    public void deleteById(Integer id) {
        funSaleDao.deleteById(id);
    }

    @Override
    public void insert(FunSale funSale) {
        funSaleDao.save(funSale);
    }
}
