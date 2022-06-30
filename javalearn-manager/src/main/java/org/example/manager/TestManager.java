package org.example.manager;

import cn.hutool.core.date.DateTime;
import org.apache.tomcat.jni.Directory;
import org.example.AdminOperationLogServiceImpl;
import org.example.WebException;
import org.example.dataentity.AdminOperationLog;
import org.example.enums.WebResultCode;
import org.example.mapper.AdminOperationLogMapper;
import org.example.model.request.TestTwoRequest;
import org.example.model.response.TestExceptionResponse;
import org.example.model.response.TestTwoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class TestManager {
    @Resource
    private AdminOperationLogServiceImpl _adminOperationLogServiceImpl;

    @Autowired
    private JedisUtil _jedisUtil;

    public TestExceptionResponse TestOneException() throws WebException{
        throw new WebException(WebResultCode.EXEC_DAPP_SDK_API_BUS_ERROR, "测试接口异常");
    }

    public TestTwoResponse TestTwo(TestTwoRequest request)
    {
        TestTwoResponse response = new TestTwoResponse();
        response.setTestColOne(request.getTestColOne());
        response.setTestColTwo(request.getTestColTwo());
        response.setTestColThree(request.getTestColThree());
        List<TestTwoResponse.TestList> lists = new ArrayList<>();
        if (request.getTestColFour().size() > 0) {
            request.getTestColFour().forEach((e) -> {
                TestTwoResponse.TestList list = new TestTwoResponse().new TestList();
                list.setTestListColOne(e.getTestListColOne());
                list.setTestListColTwo(e.getTestListColTwo());
                list.setTestListColThree(e.getTestListColThree());
                lists.add(list);
            });
        }
        response.setTestColFour(lists);
        return response;
    }

    public void TestFour()
    {
        int result = _adminOperationLogServiceImpl.count();
        AdminOperationLog adminOperationLog = new AdminOperationLog();
        adminOperationLog.setIdUser(0);
        adminOperationLog.setModule("user");
        adminOperationLog.setModuleName("用户");
        adminOperationLog.setUserName("admin");
        adminOperationLog.setName("admintest");
        adminOperationLog.setAction("list");
        adminOperationLog.setActionName("dsfddfd");
        adminOperationLog.setDescription("用户admin在用户管理界面做了获取用户列表操作");
        adminOperationLog.setResult("{}");
        adminOperationLog.setTimeBegin(DateTime.now());
        adminOperationLog.setTimeEnd(DateTime.now());
        adminOperationLog.setTimeCreate(DateTime.now());
        adminOperationLog.setRoute("api/user/list");
        _adminOperationLogServiceImpl.save(adminOperationLog);
        // 测试执行sql语句
        _adminOperationLogServiceImpl.UpdateActionName("newActionName", (long)2011411);
        // 测试事务
        _adminOperationLogServiceImpl.TestTransational("SecondActionName",  (long)2011412, "NewUserName");
    }

    // Redis
    public void TestFive()
    {
        Boolean isConnect = _jedisUtil.IsConnect();
        Set<String> keys = _jedisUtil.GetAallKeys("*");
        long count = _jedisUtil.GetKeyNums();
        Boolean addOne = _jedisUtil.Add("hello", "welcome to redis");
        Boolean addOneWithExpirTime = _jedisUtil.Add("hello2", "welcome to redis", 5000);
        String getOne = _jedisUtil.GetValue("hello");
    }

    // io
    public void TestSix() {
        String strPath = "F:\\JavaBak";
        File dir = new File(strPath);
        File[] files = dir.listFiles();
        if (files != null)
        {
            for (int i = 0; i < files.length; i++)
            {
                String fileName = files[i].getName();
                String path = files[i].getPath();
                boolean isExist = files[i].exists();
            }

            File fileDel = new File("F:\\JavaBak\\安慕希草莓燕麦酸奶200g");
            if (fileDel.isDirectory()){
                deleteDir(fileDel);
            }
            fileDel.delete();
        }
    }

    public static void deleteDir(File directory){
        File files[] = directory.listFiles();
        for (File file : files)
        {
            if (file.isDirectory()){
                deleteDir(file);
            }else {
                file.delete();
                System.out.println(file.getName() + ": : 文件已删除");
            }
        }
    }
}
