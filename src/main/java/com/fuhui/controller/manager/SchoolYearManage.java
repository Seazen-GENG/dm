package com.fuhui.controller.manager;

import com.fuhui.dao.txu.YearMapper;
import com.fuhui.entity.txu.Year;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SchoolYearManage {

    @Autowired
    private YearMapper yearMapper;

    public int add(Year year) {
        int k = yearMapper.add(year);
        if (k > 0) {
            return 1;
        }
        return 0;
    }

    public int update(Year year) {
        int k = yearMapper.update(year);
        if (k > 0) {
            return 1;
        }
        return 0;
    }

    public List getList(String schoolIds, String name, String code
            , String status, Integer isPage, Integer pNum, Integer pLine) {
        List<Year> list = yearMapper.selectByYear(schoolIds,name,code,status,isPage,pNum,pLine);
        List<Map<String,Object>> mapList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (list != null && !list.isEmpty()){
            for (Year y: list) {
                Map<String,Object> map = new HashMap<>();
                if (y.getiYearid() != null){
                    map.put("yearId",String.valueOf(y.getiYearid()));
                }else {
                    map.put("yearId","");
                }
                if (y.getcName() != null){
                    map.put("name",y.getcName());
                }else {
                    map.put("name","");
                }

                if (y.getiSchoolid() != null){
                    map.put("schoolId",String.valueOf(y.getiSchoolid()));
                }else {
                    map.put("schoolId","");
                }
                if (y.getcCode() != null){
                    map.put("code",y.getcCode());
                }else {
                    map.put("code","");
                }
                if (y.getdSdate() != null){
                    map.put("startDate",sdf.format(y.getdSdate()));
                }else {
                    map.put("startDate","");
                }
                if (y.getdEdate() != null){
                    map.put("endDate",sdf.format(y.getdEdate()));
                }else {
                    map.put("endDate","");
                }
                if (y.getiYearid() != null){
                    map.put("status",y.getiYearid());
                }else {
                    map.put("status","");
                }
                mapList.add(map);
            }
        }

        return mapList;
    }

}
