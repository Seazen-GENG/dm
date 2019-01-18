package com.fuhui.controller.service;

import com.fuhui.controller.manager.SchoolYearManage;
import com.fuhui.entity.txu.Year;
import com.fuhui.util.DateFromat;
import com.fuhui.util.ReturnJson;
import com.fuhui.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("yearService")
public class SchoolYearService {

    @Autowired
    private SchoolYearManage yearManage;

    @RequestMapping("saveYear")
    public Object saveYear(String yearId, String name, String code, String shortName, String schoolId,
                           String startDate, String endDate, String remark, String status) {
        //schoolId == null || schoolId.equals("") ||
        SnowFlake snowFlake = new SnowFlake(2, 3);//使用雪花算法生成全局唯一ID
        long schooId1 = 1;
        Year year = new Year();
        if (name == null || name.equals("") || startDate == null || startDate.equals("") || endDate == null || endDate.equals("")) {
            return ReturnJson.returnFail();
        } else {
            year.setcName(name);
            Date staDate = DateFromat.parse(startDate, "yyyy-MM-dd HH:mm:ss", Locale.CHINA);
            Date enDate = DateFromat.parse(endDate, "yyyy-MM-dd HH:mm:ss", Locale.CHINA);
            year.setdSdate(staDate);
            year.setdEdate(enDate);
        }

        if (schoolId == null || schoolId.equals("")) {
            year.setiSchoolid(schooId1);
        } else {
            year.setiSchoolid(Long.valueOf(schoolId));
        }
        if (code != null) {
            year.setcCode(code);
        }
        if (shortName != null) {
            year.setcStname(shortName);
        }
        if (remark != null) {
            year.setcRemark(remark);
        }
        if (status == null || status.equals("")) {
            year.setiStatus(1);
        } else {
            year.setiStatus(Integer.parseInt(status));
        }
        Map<String, Object> map = new HashMap<String, Object>();
        if (yearId == null || yearId.equals("")) {
            //添加
            long ID = snowFlake.nextId();
            year.setiYearid(ID);
            //根据token获取用户信息
            {
                year.setiCuserid(Long.valueOf("1"));
                year.setiUuserid(Long.valueOf("1"));
                year.settCtime(new Date());
                year.settUtime(new Date());
            }
            int k = yearManage.add(year);
            if (k > 0) {
                map.put("yearId", String.valueOf(ID));
                return ReturnJson.returnSuccessMap(map);
            }
        } else {
            //更新
            year.setiYearid(Long.valueOf(yearId));
            {
                year.setiUuserid(Long.valueOf("2"));
                year.settUtime(new Date());
            }
            int k = yearManage.update(year);
            if (k > 0) {
                map.put("yearId", String.valueOf(yearId));
                return ReturnJson.returnSuccessMap(map);
            }
        }
        return ReturnJson.returnFail();
    }

    @RequestMapping("getYearListBySchoolId")
    public Object getYearListBySchoolId(String schoolIds, String name, String code
            , String status, String isPage, String pNum, String pLine) {
        if (schoolIds == null || schoolIds.equals("")) {
            return ReturnJson.returnFail();
        }
        List list = new ArrayList();
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> pageMap = new HashMap<>();
        try {
            if (isPage == null || isPage.equals("")) {
                list = yearManage.getList(schoolIds, name, code, status, 0, 0, 0);
            } else {
                if (pNum == null || pNum.equals("") || pLine == null || pLine.equals("")) {
                    return ReturnJson.returnFail();
                }
                list = yearManage.getList(schoolIds, name, code, status, Integer.parseInt(isPage), Integer.parseInt(pNum) - 1, Integer.parseInt(pLine));
                pageMap.put("pageNum", pNum);
                pageMap.put("pageLine ", pLine);
                pageMap.put("countAll", list.size());
                resultMap.put("page", pageMap);
            }
            resultMap.put("list", list);
            return ReturnJson.returnSuccessMap(resultMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ReturnJson.returnFail();
    }
}
