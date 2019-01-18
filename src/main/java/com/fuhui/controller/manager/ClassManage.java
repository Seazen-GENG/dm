package com.fuhui.controller.manager;

import com.fuhui.dao.txu.ClassMapper;
import com.fuhui.dao.txu.GradeMapper;
import com.fuhui.dao.txu.YearMapper;
import com.fuhui.entity.txu.Class;
import com.fuhui.entity.txu.Grade;
import com.fuhui.entity.txu.Year;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClassManage {
    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private GradeMapper gradeMapper;

    @Autowired
    private YearMapper yearMapper;

    public List getList(String schoolIds, String name, String code, String status, String gradeId,
                        String xGradeId, String yearId, String headId, String sCTime,
                        String eCTime, Integer isPage, Integer pNum, Integer pLine) {
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        List<Class> list = classMapper.selectClassList(schoolIds, name, code, status, gradeId, xGradeId, yearId, headId, sCTime, eCTime, isPage, pNum, pLine);
        if (list != null) {
            for (Class c : list) {
                Map<String, Object> map = new HashMap<String, Object>();
                if (c.getiSchoolid() != null) {
                    map.put("schoolId", String.valueOf(c.getiSchoolid()));
                }
                if (c.getiClassid() != null) {
                    map.put("classId", String.valueOf(c.getiClassid()));
                }
                if (c.getcName() != null) {
                    map.put("name", c.getcName());
                }
                if (c.getcCode() != null) {
                    map.put("code", c.getcCode());
                }
                if (c.getiGradeid() != null) {
                    map.put("gradeId", String.valueOf(c.getiGradeid()));
                }
                if (c.getiXgradeid() != null) {
                    map.put("xGradeId", String.valueOf(c.getiXgradeid()));
                }
                if (c.getGrade() != null) {
                    Grade grade = gradeMapper.selectByPrimaryKey(c.getiXgradeid());
                    map.put("xGradeName", grade.getcName());
                }
                if (c.getiYearid() != null) {
                    map.put("yearId", String.valueOf(c.getiYearid()));
                }
                if (c.getYear() != null) {
                    Year year = yearMapper.selectByPrimaryKey(c.getiYearid());
                    map.put("yearName", year.getcName());
                }
                if (c.getiStatus() != null) {
                    map.put("status", c.getiStatus());
                }
                mapList.add(map);
            }
        }
        return mapList;
    }

    public int add(Class record){
        int k = 0;
        k = classMapper.add(record);
        if (k>0){
            return 1;
        }
        return 0;
    }

    public int update(Class record){
        int k = 0;
        k = classMapper.update(record);
        if (k>0){
            return 1;
        }
        return 0;
    }

}
