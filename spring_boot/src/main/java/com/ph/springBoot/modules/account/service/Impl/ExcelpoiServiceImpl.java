package com.ph.springBoot.modules.account.service.Impl;

import com.ph.springBoot.modules.account.dao.ExcelpoiDao;
import com.ph.springBoot.modules.account.entity.Excelpoi;
import com.ph.springBoot.modules.account.service.ExcelpoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExcelpoiServiceImpl implements ExcelpoiService {

    @Autowired
    private ExcelpoiDao excelpoiDao;

    @Override
    public List<Excelpoi> getExportDatas() {
        List<Excelpoi> userModels = excelpoiDao.getExportDatas();
        return userModels;
    }

    @Override
    public void saveImport(Excelpoi userModel) {
        excelpoiDao.saveImport(userModel);
    }

    @Override
    public Excelpoi findByNameAndPassword(String userName, String password) {
        Excelpoi excelTemp = excelpoiDao.getExcelpoiByUserName(userName);
        return excelTemp;
    }
}
