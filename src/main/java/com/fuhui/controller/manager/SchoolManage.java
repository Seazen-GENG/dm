package com.fuhui.controller.manager;

import com.fuhui.dao.txu.AppMapper;
import com.fuhui.dao.txu.SchoolMapper;
import com.fuhui.entity.trj.AppVersion;
import com.fuhui.entity.txu.App;
import com.fuhui.entity.txu.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;;import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SchoolManage {

    @Autowired
    private SchoolMapper schoolMapper;

    @Autowired
    private AppMapper appMapper;

    public int add(School school) {
        int k = schoolMapper.insertSelective(school);
        return k;
    }

    public int update(School school) {
        int k = schoolMapper.updateByPrimaryKeySelective(school);
        return k;
    }

    public List<Map<String, Object>> getSchoolList() {
        List<School> list = null;
        list = schoolMapper.seletSchoolList();
        List<Map<String, Object>> mapList = new ArrayList();
        for (School shl : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("schoolId", String.valueOf(shl.getiSchoolid()));
            map.put("name", shl.getcName());
            map.put("address", shl.getcAddress());
            mapList.add(map);
        }
        return mapList;
    }

    public List<Map<String, Object>> getSchoolListByAppId(Long appId){
        List<School> list = new ArrayList<>();
        list = schoolMapper.seletSchoolListByAppId(appId);
        List<Map<String, Object>> resultList = new ArrayList();
        for (School sh : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("schoolId", String.valueOf(sh.getiSchoolid()));
            map.put("name", sh.getcName());
            map.put("address", sh.getcAddress());
            map.put("version", sh.getVersion());
            resultList.add(map);
        }
        return resultList;
    }

    public int delSchool(Long schoolId) {
        int result = schoolMapper.deleteByPrimaryKey(schoolId);
        if (result > 0){
            return 1;
        }
        return 0;
    }

    public int setAppVersionBySchoolId(Long schoolId,Long appId, Long versionId, Long userId){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        App app = new App();
        app.setiAppid(appId);
        app.setiVersionid(versionId);
        app.setiSchoolid(schoolId);
        app.setiUuserid(userId);
        app.settUtime(sdf.format(new Date()));
        int k = appMapper.setAppVersionBySchoolId(app);
        if (k > 0){
            return 1;
        }
        return 0;
    }

    /////查询设备固件未升级的学校
    public List<Map<String, Object>> getSchoolListByVersionId(Long versionId) {
        List<School> list = null;
        list = schoolMapper.getSchoolListByVersionId(versionId);
        List<Map<String, Object>> mapList = new ArrayList();
        for (School shl : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("schoolId", String.valueOf(shl.getiSchoolid()));
            map.put("name", shl.getcName());
            map.put("address", shl.getcAddress());
            mapList.add(map);
        }
        return mapList;
    }

}
