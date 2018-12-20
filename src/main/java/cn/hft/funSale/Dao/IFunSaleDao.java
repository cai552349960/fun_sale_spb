package cn.hft.funSale.Dao;

import cn.hft.funSale.domain.FunSale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface IFunSaleDao extends JpaRepository<FunSale, Integer>, JpaSpecificationExecutor<FunSale> {
    Page<FunSale> findByOrderByUpdateTimeDesc(Pageable pageable);
}
