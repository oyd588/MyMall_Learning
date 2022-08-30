package com.oyd.mall.tiny.service;

import com.oyd.mall.tiny.mbg.model.PmsBrand;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PmsBrandService {

    List<PmsBrand> listAllBrand();

    int createBrand(PmsBrand pmsBrand);

    int updateBrand(Long id,PmsBrand pmsBrand);

    int deleteBrand(Long id);

    List<PmsBrand> listBrand(int pageNum,int pageSize);

    PmsBrand getBrand(Long id);
}
