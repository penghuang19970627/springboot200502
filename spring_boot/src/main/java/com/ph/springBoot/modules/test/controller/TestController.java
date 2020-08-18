package com.ph.springBoot.modules.test.controller;
import com.ph.springBoot.modules.test.entity.City;
import com.ph.springBoot.modules.test.entity.Country;
import com.ph.springBoot.modules.test.service.CityService;
import com.ph.springBoot.modules.test.service.CountryService;
import com.ph.springBoot.modules.vo.ApplicationTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description TestController
 * @Author HymanHu
 * @Date 2020/8/10 10:39
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private CountryService countryService;
    @Autowired
    private CityService cityService;

    //单个文件上传
    /*127.0.0.1/test/file   post*/
    @PostMapping(value = "/file" ,consumes = "multipart/form-data")
    public String uploadFile(@RequestParam MultipartFile file, RedirectAttributes redirectAttributes){
        //判断有没有选择文件
        if (!file.isEmpty()){
            try {
                //保存文件的路径
                String destFilePath = "D:\\upload\\"+file.getOriginalFilename();
                File uploadfile = new File(destFilePath);
                //转存文件
                file.transferTo(uploadfile);
                redirectAttributes.addFlashAttribute("message","上传成功");
            }catch (IOException e){
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("message","上传失败");
            }
            return "redirect:/test/index";
        }
        redirectAttributes.addFlashAttribute("message","请选择上传文件");
        return "redirect:/test/index";
    }

    //多个文件上传
    /*127.0.0.1/test/files   post*/
    @PostMapping(value = "/files" , consumes = "multipart/form-data")
    public String uploadFiles(@RequestParam MultipartFile[] files,RedirectAttributes redirectAttributes){
        boolean empty = true;
        try {
            for (MultipartFile file : files){
                if (file.isEmpty()){
                    continue;
                }
                String destFilePath="D:\\upload\\"+file.getOriginalFilename();
                File uploadfile = new File(destFilePath);
                file.transferTo(uploadfile);
                empty=false;
            }
            if (empty){
                redirectAttributes.addFlashAttribute("message","请选择上传文件");
            }else {
                redirectAttributes.addFlashAttribute("message","上传成功");
            }
        }catch (IOException e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message","上传失败");
        }
        return "redirect:/test/index";
    }

    //文件下载
    /*127.0.0.1/test/file    get*/
    @GetMapping("/file")
    public ResponseEntity<Resource> downloadfile(@RequestParam String fileName){
        Resource resource = null;
        try {
            //UrlResource(Uri uri) 所以要将获取到的文件转为Uri的属性
            resource = new UrlResource(Paths.get("D:\\upload\\"+fileName).toUri());
            //判断文件是否存在以及是否可读
            if (resource.exists() && resource.isReadable()){
                return ResponseEntity.ok()
                        //传输格式
                        .header(HttpHeaders.CONTENT_TYPE,"application/octet-stream")
                        //文件说明
                        .header(HttpHeaders.CONTENT_DISPOSITION,String.format("attachment;filename=\"%s\"",resource.getFilename()))
                        .body(resource);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    /*将文件以BufferedInputStream的方式读取到byte[]里面，然后用OutputStream.write输出文件*/
    /*报Request method 'GET' not supported错误*/
    /*127.0.0.1/test/downone   request*/
    /*@GetMapping("/downone")
    public void downloadfileone(HttpServletRequest request, HttpServletResponse response,@RequestParam String fileName){
        String filePath = "D:\\upload" +File.separator+ fileName;
        File downloadFile = new File(filePath);
        if (downloadFile.exists()){
            response.setContentType("application/octet-stream");
            response.setContentLength((int)downloadFile.length());
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION,String.format("attachment;\"%s\"",fileName));

            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(downloadFile);
                bis = new BufferedInputStream(fis);
                OutputStream ok = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1){
                    ok.write(buffer,0,i);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                try {
                    if (fis != null){
                        fis.close();
                    }
                    if (bis != null){
                        bis.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }*/

    /*以包装类 IOUtils 输出文件*/
    /*127.0.0.1/test/downrwo*/
    /*报Request method 'GET' not supported错误*/
    /*@RequestMapping("/downtwo")
    public void downloadtwo(HttpServletResponse response ,@RequestParam String fileName){
        String filePath = "D:\\upload" +File.separator+ fileName;
        File downfile = new File(filePath);
        try {
            if (downfile.exists()){
                response.setContentType("application/octet-stream");
                response.setContentLength((int)downfile.length());
                response.setHeader(HttpHeaders.CONTENT_DISPOSITION,String.format("attachment;\"%s\"",fileName));
                InputStream is = new FileInputStream(downfile);
                IOUtils.copy(is,response.getOutputStream());
                response.flushBuffer();
            }
        } catch (Exception e) {
            LOGGER.debug(e.getMessage());
            e.printStackTrace();
        }
    }*/

    private final static Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @Value("${server.port}")
    private int port;
    @Value("${com.name}")
    private String name;
    @Value("${com.age}")
    private int age;
    @Value("${com.desc}")
    private String desc;
    @Value("${com.random}")
    private String random;

    @Autowired
    private ApplicationTest applicationTest;

    /**
     * 127.0.0.1:8081/test/log ---- get
     */
    @GetMapping("/log")
    @ResponseBody
    public String logTest() {
        LOGGER.trace("This is trace log");
        LOGGER.debug("This is debug log");
        LOGGER.info("This is info log");
        LOGGER.warn("This is warn log");
        LOGGER.error("This is error log");
        return "这是日志系统测试(热加载实验)开始";
    }

    /**
     * 127.0.0.1:8085/test/config ---- get
     */
    @GetMapping("/config")
    @ResponseBody
    public String configTest() {
        StringBuffer sb = new StringBuffer();
        sb.append(port).append("----")
                .append(name).append("----")
                .append(age).append("----")
                .append(desc).append("----")
                .append(random).append("----").append("<br>");
        sb.append(applicationTest.getPort()).append("----")
                .append(applicationTest.getName()).append("----")
                .append(applicationTest.getAge()).append("----")
                .append(applicationTest.getDesc()).append("----")
                .append(applicationTest.getRandom()).append("----").append("<br>");

        return sb.toString();

    }

    /**
     * 127.0.0.1/test/testDesc?paramKey=fuck ---- get
     */
    @GetMapping("/testDesc")
    @ResponseBody
    public String testDesc(HttpServletRequest request ,@RequestParam(value = "paramKey") String paramValue) {
        String paramValue2 = request.getParameter("paramKey");
        return "这是我的第一个springboot项目"+paramValue+"=="+paramValue2;
    }


    /*127.0.0.1/test/index*/
    @GetMapping("/index")
    public String indexPage(ModelMap map){
        int countryId = 2   ;
        List<City> cities = cityService.getCitiesByCountryId(countryId);
        cities = cities.stream().limit(10).collect(Collectors.toList());
        Country country = countryService.getCountryByCountryId(countryId);

        map.addAttribute("thymeleafTitle", "penghuang");
        map.addAttribute("checked", true);
        map.addAttribute("currentNumber", 99);
        map.addAttribute("changeType", "checkbox");
        map.addAttribute("baiduUrl", "/test/log");
        map.addAttribute("city", cities.get(0));
        map.addAttribute("shopLogo1",
                "http://cdn.duitang.com/uploads/item/201308/13/20130813115619_EJCWm.thumb.700_0.jpeg");
        map.addAttribute("shopLogo", "/upload/11111.jpg");
        map.addAttribute("country", country);
        map.addAttribute("cities", cities);
        map.addAttribute("updateCityUri", "/ph/city");
        //map.addAttribute("template", "test/index");
        // 返回外层的碎片组装器
        return "index";
    }
}
