package com.oyd.mall.tiny.service.impl;

import com.oyd.mall.tiny.dao.EsProductDao;
import com.oyd.mall.tiny.nosql.elasticsearch.document.EsProduct;
import com.oyd.mall.tiny.nosql.elasticsearch.repository.EsProductRepository;
import com.oyd.mall.tiny.service.EsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class EsProductServiceImpl implements EsProductService {

    @Autowired
    private EsProductDao esProductDao;
    @Autowired
    private EsProductRepository esProductRepository;

    @Override
    public int importAll() {

        List<EsProduct> allEsProductList = esProductDao.getAllEsProductList(null);
        Iterator<EsProduct> iterator = esProductRepository.saveAll(allEsProductList).iterator();
        int result = 0;
        while (iterator.hasNext()) {
            result++;
            iterator.next();
        }
        return result;
    }

    @Override
    public void delete(Long id) {
        esProductRepository.deleteById(id);
    }

    @Override
    public EsProduct create(Long id) {
        EsProduct result = null;
        List<EsProduct> esProductList = esProductDao.getAllEsProductList(id);
        if (esProductList.size() > 0) {
            EsProduct esProduct = esProductList.get(0);
            result = esProductRepository.save(esProduct);
        }
        return result;
    }

    @Override
    public void delete(List<Long> ids) {
        if (!ids.isEmpty()) {
            ArrayList<EsProduct> esProducts = new ArrayList<>();
            for (Long id : ids) {
                EsProduct esProduct = new EsProduct();
                esProduct.setId(id);
                esProducts.add(esProduct);
            }
            esProductRepository.deleteAll(esProducts);
        }
    }

    @Override
    public Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize) {
        PageRequest pageable = PageRequest.of(pageNum, pageSize);
        return esProductRepository.findByNameOrSubTitleOrKeywords(keyword, keyword, keyword, pageable);
    }
}
