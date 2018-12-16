package cn.hft.funSale.Dao;

import cn.hft.funSale.domain.FunSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface IFunSaleDao extends JpaRepository<FunSale, Integer>, JpaSpecificationExecutor<FunSale> {
    @Query(value = "update [FUN_SALE] set  SALE_SUBJECT=#{saleSubject},BUILD_NAME=#{buildName},TRADE_ADDR=#{tradeAddr},SALE_ROOM=#{saleRoom},SALE_INNERAREA=#{saleInnerarea},REGION_NAME=#{regionName},SECTION_NAME=#{sectionName},UPDATE_TIME=#{updateTime},SALE_TOTAL_PRICE=#{saleTotalPrice},SALE_UNIT_PRICE=#{saleUnitPrice} wherer SALE_ID=#{saleID}",nativeQuery = true)
    public void updateById(FunSale funSale);
}
