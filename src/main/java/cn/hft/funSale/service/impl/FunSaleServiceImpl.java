package cn.hft.funSale.service.impl;

import cn.hft.funSale.Dao.IFunSaleDao;
import cn.hft.funSale.domain.FunSale;
import cn.hft.funSale.service.IFunSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service

public class FunSaleServiceImpl implements IFunSaleService {
    @Autowired
    private IFunSaleDao funSaleDao;
    /*@Override
    public List<FunSale> findAll(Integer pageNumber,Integer pageSize) {
      *//*  Sort sort = new Sort(Sort.Direction.DESC,"UPDATE_TIME"); //创建时间降序排序
        Pageable pageable = new PageRequest(pageNumber,pageSize,sort);
        this.depositRecordRepository.findAllByUserIdIn(userIds,pageable);*//*
        return funSaleDao.findAll();
    }*/

    @Override
    public List<FunSale> findAll() {
        return funSaleDao.findAll();
    }

    @Override
    public Page<FunSale> findPage( Integer page, Integer size) {
       /* Pageable pageRequest = new PageRequest(page, size);
        Sort sort = new Sort(Sort.Direction.ASC,"UPDATE_TIME");
        pageRequest.getSortOr(sort);
        Specification<FunSale> funSaleSpecification = new Specification<FunSale>() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        };
        return  funSaleDao.findAll(funSaleSpecification,pageRequest);*/
        Pageable pageable = new PageRequest(page,size);
        return funSaleDao.findByOrderByUpdateTimeDesc(pageable);
     /* Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
      Pageable pageable = new PageRequest(page, size, sort);
      return funSaleDao.findAll(pageable);*/
    }

    @Override
    public FunSale findById(Integer id) {
        Optional<FunSale> byId = funSaleDao.findById(id);
        FunSale funSale = byId.get();
        return funSale;
    }

    @Override
    @Transactional
    public void updateById(FunSale funSale) {
        //先将前端传递过来的funSale取出id到数据库进行查询
        Integer saleID = funSale.getSaleID();
        Optional<FunSale> byId = funSaleDao.findById(saleID);
        FunSale funSale1 = byId.get();//获得数据库中对应的funSale对象

        //将修改的数据设置到查询出来的funSale对象中
        funSale1.setSaleSubject(funSale.getSaleSubject());
        funSale1.setBuildName(funSale.getBuildName());
        funSale1.setTradeAddr(funSale.getTradeAddr());
        funSale1.setSaleRoom(funSale.getSaleRoom());
        funSale1.setSaleInnerarea(funSale.getSaleInnerarea());
        funSale1.setUpdateTime(funSale.getUpdateTime());
        funSale1.setRegionName(funSale.getRegionName());
        funSale1.setSectionName(funSale.getSectionName());
        funSale1.setSaleTotalPrice(funSale.getSaleTotalPrice());
        funSale1.setSaleUnitPrice(funSale.getSaleUnitPrice());

        funSaleDao.save(funSale1);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        funSaleDao.deleteById(id);
    }

    @Override
    @Transactional
    public void insert(FunSale funSale) {

        funSale.setCreationTime(new Date());
        funSale.setUpdateTime(funSale.getCreationTime());
        funSaleDao.save(funSale);
    }
}
