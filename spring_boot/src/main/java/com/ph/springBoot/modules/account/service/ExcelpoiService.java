package com.ph.springBoot.modules.account.service;

import com.ph.springBoot.modules.account.entity.Excelpoi;

import java.util.List;

public interface ExcelpoiService {
    List<Excelpoi> getExportDatas();

    void saveImport(Excelpoi userModel);

    Excelpoi findByNameAndPassword(String userName, String password);
}
