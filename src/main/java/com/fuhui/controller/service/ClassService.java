package com.fuhui.controller.service;

import com.fuhui.controller.manager.ClassManage;
import com.fuhui.entity.txu.Class;
import com.fuhui.util.ReturnJson;
import com.fuhui.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("classService")
public class ClassService {

    @Autowired
    private ClassManage classManage;

    @RequestMapping("getClassListBySchoolId")
    public Object getClassListBySchoolId(String schoolId, String name, String code, String status, String gradeId,
                                         String xGradeId, String yearId, String headId, String sCTime,
                                         String eCTime, String isPage, String pNum, String pLine, String headFlg) {
        if (schoolId == null || schoolId.equals("")) {
            return ReturnJson.returnFail();
        }
        Integer isPage1 = -1;
        if (isPage != null) {
            isPage1 = Integer.parseInt(isPage);
        }
        System.out.println("isPage1==" + isPage1);
        List<Class> list = null;
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> pageMap = new HashMap<>();
        try {
            if (isPage1 > 0) {
                if (pNum == null || pNum.equals("") || pLine == null || pLine.equals("")) {
                    return ReturnJson.returnFail();
                }
                list = classManage.getList(schoolId, name, code, status, gradeId, xGradeId, yearId, headId, sCTime,
                        eCTime, Integer.parseInt(isPage), Integer.parseInt(pNum) - 1, Integer.parseInt(pLine));
                pageMap.put("pageNum", pNum);
                pageMap.put("pageLine ", pLine);
                pageMap.put("countAll", list.size());
                resultMap.put("page", pageMap);
            } else {
                list = classManage.getList(schoolId, name, code, status, gradeId, xGradeId, yearId, headId, sCTime,
                        eCTime, 0, 0, 0);
            }
            resultMap.put("list", list);
            return ReturnJson.returnSuccessMap(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnJson.returnFail();
    }

    @RequestMapping("saveClass")
    public Object saveClass(String classId, String name, String shortName, String gradeId, String schoolId, String xGradeId,
                            String type, String yearId, String studentNum, String orderNo, String roomId, String headId,
                            String remark, String status) {
        //schoolId==null || schoolId.equals("") 从token中获取
        SnowFlake snowFlake = new SnowFlake(2, 3);//使用雪花算法生成全局唯一ID
        long schoolId1 = 1;
        //整理参数集合
        Class classPojo = new Class();
        if (name == null || name.equals("") || gradeId == null || gradeId.equals("") || xGradeId == null || xGradeId.equals("")
                || type == null || type.equals("") || yearId == null || yearId.equals("") || orderNo == null || orderNo.equals("")) {
            return ReturnJson.returnFail();
        } else {
            classPojo.setcName(name);
            classPojo.setiGradeid(Long.valueOf(gradeId));
            classPojo.setiXgradeid(Long.valueOf(xGradeId));
            classPojo.setiType(Integer.parseInt(type));
            classPojo.setiYearid(Long.valueOf(yearId));
            classPojo.setiOrderno(Integer.parseInt(orderNo));
        }
        long newClassId;
        if (shortName != null) {
            classPojo.setcStname(shortName);
        }
        if (schoolId == null || schoolId.equals("")) {
            classPojo.setiSchoolid(schoolId1);
        } else {
            classPojo.setiSchoolid(Long.valueOf(schoolId));
        }
        if (status == null || status.equals("")) {
            classPojo.setiStatus(1);
        } else {
            classPojo.setiStatus(Integer.parseInt(status));
        }
        if (studentNum != null) {
            classPojo.setiStudentnum(Integer.parseInt(studentNum));
        }
        if (roomId != null) {
            classPojo.setiRoomid(Long.valueOf(roomId));
        }
        if (headId != null) {
            classPojo.setiHeadId(Long.valueOf(headId));
        }
        if (remark != null) {
            classPojo.setcRemark(remark);
        }
        int result = -1;
        Map<String,Object> map = new HashMap<>();
        if (classId == null || classId.equals("")) {
            newClassId = snowFlake.nextId();
            classPojo.setiClassid(newClassId);
            {//token
                classPojo.setiCuserid(Long.valueOf("1"));
                classPojo.setiUuserid(Long.valueOf("1"));
                classPojo.settCtime(new Date());
                classPojo.settUtime(new Date());
            }
            //classId为空添加
            result = classManage.add(classPojo);
            map.put("classId",String.valueOf(newClassId));
        } else {
            //classId不为空更新
            classPojo.setiClassid(Long.valueOf(classId));
            {//token
                classPojo.setiUuserid(Long.valueOf("1"));
                classPojo.settUtime(new Date());
            }
            result = classManage.update(classPojo);
            map.put("classId",String.valueOf(classId));
        }
        if (result > 0){
            return  ReturnJson.returnSuccessMap(map);
        }
        return ReturnJson.returnFail();
    }

}
