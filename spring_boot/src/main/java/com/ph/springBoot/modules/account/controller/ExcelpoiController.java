package com.ph.springBoot.modules.account.controller;

import com.ph.springBoot.modules.account.entity.Excelpoi;
import com.ph.springBoot.modules.account.service.ExcelpoiService;
import com.ph.springBoot.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/test/excel")
public class ExcelpoiController {

    @Autowired
    private ExcelpoiService service;

    @RequestMapping("export")
    public void export(HttpServletResponse response) throws IOException {
        //从数据库获取需要导出的数据
        List<Excelpoi> userModelList =service.getExportDatas();
        //导出操作
        FileUtil.exportExcel(userModelList,"用户信息","用户制作scy",Excelpoi.class,"用户.xls",response);
    }

    @RequestMapping("importExcel")
    public String importExcel() throws IOException {
        String filePath = "F:\\用户.xls.xlsx";
        //解析excel，
        List<Excelpoi> userModelList= FileUtil.importExcel(filePath,1,1,Excelpoi.class);
        //也可以使用MultipartFile,使用 FileUtil.importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass)导入
        System.err.println("导入数据一共【"+userModelList.size()+"】行");

        //TODO 保存数据库.是个集合需要遍历
        for(Excelpoi userModelLists : userModelList) {
            String userName = userModelLists.getUserName();
            String password = userModelLists.getPassword();
            //数据库查重（写个查询的方法），名字和密码重复就不插入，否则插入
            Excelpoi byNameAndPassword = service.findByNameAndPassword(userName, password);
            if (byNameAndPassword==null){
                service.saveImport(userModelLists);
            }
        }
        return "redirect:/test/index";
    }

}
