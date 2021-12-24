package com.ddq;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class UtilsTest {
    ObjectMapper objectMapper = new ObjectMapper();


    @Test // 字符串操作 org.apache.commons.lang3
    public void langsStringTest() throws Exception{

    }


    @Test //JSON字符串操作 com.fasterxml.jackson :  -JsonNode -ObjectNode -ArrayNode
    public void jsonTest() throws Exception{


        JsonNode info = objectMapper.readTree("{\n" +
                "\t\t\t\"code\": \"AC2001150000\",\n" +
                "\t\t\t\"auditCode\": \"C101\",\n" +
                "\t\t\t\"responsibleParty\": \"03\",\n" +
                "\t\t\t\"responsiblePartyName\": \"销售客服\",\n" +
                "\t\t\t\"auditItem\": \"61\",\n" +
                "\t\t\t\"auditItemName\": \"资料信息核查-C1类\",\n" +
                "\t\t\t\"auditCodeType\": \"01\",\n" +
                "\t\t\t\"auditCodeTypeName\": \"标准码\",\n" +
                "\t\t\t\"sketch\": \"未上传车身铭牌照片及上传不完整或不清晰\",\n" +
                "\t\t\t\"grade\": 0.0\n" +
                "\t\t}");
        String caseCo = "CC2112210005";
        ObjectNode auditCompleteParams = objectMapper.createObjectNode();
        ObjectNode caseLibraryInfo = objectMapper.createObjectNode();
        caseLibraryInfo.putPOJO("caseCode",caseCo);
        caseLibraryInfo.putPOJO("caseLibraryType","02");
        caseLibraryInfo.putPOJO("reason","测试--申请案例库");
        caseLibraryInfo.putNull("id");
        auditCompleteParams.putPOJO("caseLibraryInfo",caseLibraryInfo);

        String x1 = auditCompleteParams.toString();
        JsonNode x2 = auditCompleteParams;
        String x3 = objectMapper.writeValueAsString(auditCompleteParams);

        System.out.println("auditCompleteParams.toString() = " + auditCompleteParams.toString());
        System.out.println("auditCompleteParams = " + auditCompleteParams);
        System.out.println("objectMapper.writeValueAsString(auditCompleteParams) = " + objectMapper.writeValueAsString(auditCompleteParams));






        String s = "{\"status\":\"1\",\"data\":{\"id\":null,\"auditStaffName\":null,\"transportId\":null,\"firstTrialStaffName\":null,\"firstTrialDepartmentName\":null,\"finalTrialStaffName\":null,\"finalTrialDepartmentName\":null,\"borrowerCode\":null,\"borrowerName\":null,\"processNode\":null,\"auditDate\":null,\"auditStartTime\":null,\"auditEndTime\":null,\"expendTime\":null,\"score\":null,\"productCode\":null,\"caseCode\":null,\"finalAmount\":null,\"channel\":null}}";
        JsonNode data = objectMapper.readTree(s).get("data");
        String caseCode = data.get("caseCode").textValue();
        String caseCode1 = data.get("caseCode").asText();
        JsonNode caseCode2 = data.get("caseCode");
        System.out.println("caseCode为 null  的结果" + (caseCode == null));
        System.out.println("caseCode1为 null  的结果" + (caseCode1 == null));
        System.out.println("caseCode2为 null  的结果" + (caseCode2 == null));
        System.out.println("caseCode2为 NullNode  的结果" + (caseCode2 instanceof NullNode));


        String deliverySearchRes = "{\"status\":\"1\",\"data\":{\"resultList\":[{\"caseCode\":null,\"transportId\":\"100390853\",\"custName\":\"经营复议\",\"processNode\":\"等待签订合同\",\"productName\":null,\"revolveFlag\":\"个贷\",\"submitDeptName\":\"个贷-北京朝阳区铜牛大厦营业部\",\"firstTrialStaffName\":\"王卉\",\"firstTrialTime\":\"2020-09-04 10:14:32.0\",\"firstTrialDepartment\":\"政策组\",\"finalTrialStaffName\":null,\"finalTrialTime\":null,\"finalTrialDepartment\":null,\"auditFirstTrialName\":null,\"auditFirstTrialTime\":null,\"auditFirstTrialDept\":null,\"auditStaffName\":null,\"auditName\":null,\"auditStatus\":\"未稽核\"},{\"caseCode\":null,\"transportId\":\"104254753\",\"custName\":\"复议回归\",\"processNode\":\"等待签订合同\",\"productName\":null,\"revolveFlag\":\"个贷\",\"submitDeptName\":\"个贷-北京朝阳区铜牛大厦营业部\",\"firstTrialStaffName\":\"王卉\",\"firstTrialTime\":\"2020-10-13 10:28:50.0\",\"firstTrialDepartment\":\"政策组\",\"finalTrialStaffName\":null,\"finalTrialTime\":null,\"finalTrialDepartment\":null,\"auditFirstTrialName\":null,\"auditFirstTrialTime\":null,\"auditFirstTrialDept\":null,\"auditStaffName\":null,\"auditName\":null,\"auditStatus\":\"未稽核\"},{\"caseCode\":null,\"transportId\":\"104307155\",\"custName\":\"经营复议\",\"processNode\":\"等待签订合同\",\"productName\":null,\"revolveFlag\":\"个贷\",\"submitDeptName\":\"个贷-北京朝阳区铜牛大厦营业部\",\"firstTrialStaffName\":\"王卉\",\"firstTrialTime\":\"2020-11-24 10:08:51.0\",\"firstTrialDepartment\":\"政策组\",\"finalTrialStaffName\":null,\"finalTrialTime\":null,\"finalTrialDepartment\":null,\"auditFirstTrialName\":null,\"auditFirstTrialTime\":null,\"auditFirstTrialDept\":null,\"auditStaffName\":null,\"auditName\":null,\"auditStatus\":\"未稽核\"},{\"caseCode\":null,\"transportId\":\"100388985\",\"custName\":\"车贷反欺诈测九\",\"processNode\":\"等待签订合同\",\"productName\":\"车贷授薪\",\"revolveFlag\":\"个贷\",\"submitDeptName\":\"个贷-北京朝阳区铜牛大厦营业部\",\"firstTrialStaffName\":\"贾宇鹏\",\"firstTrialTime\":\"2020-05-11 16:12:18.0\",\"firstTrialDepartment\":\"培训团队\",\"finalTrialStaffName\":null,\"finalTrialTime\":null,\"finalTrialDepartment\":null,\"auditFirstTrialName\":null,\"auditFirstTrialTime\":null,\"auditFirstTrialDept\":null,\"auditStaffName\":null,\"auditName\":null,\"auditStatus\":\"未稽核\"},{\"caseCode\":null,\"transportId\":\"100388231\",\"custName\":\"新网测试十二\",\"processNode\":\"等待签订合同\",\"productName\":\"授薪\",\"revolveFlag\":\"个贷\",\"submitDeptName\":\"上海浦东新区证券大厦营业部\",\"firstTrialStaffName\":\"申请测试\",\"firstTrialTime\":\"2020-03-06 15:36:53.0\",\"firstTrialDepartment\":\"test111\",\"finalTrialStaffName\":null,\"finalTrialTime\":null,\"finalTrialDepartment\":null,\"auditFirstTrialName\":null,\"auditFirstTrialTime\":null,\"auditFirstTrialDept\":null,\"auditStaffName\":null,\"auditName\":null,\"auditStatus\":\"未稽核\"},{\"caseCode\":null,\"transportId\":\"104307620\",\"custName\":\"经营公积金贷\",\"processNode\":\"等待签订合同\",\"productName\":null,\"revolveFlag\":\"个贷\",\"submitDeptName\":\"个贷-北京朝阳区铜牛大厦营业部\",\"firstTrialStaffName\":\"测试\",\"firstTrialTime\":\"2021-01-04 10:12:13.0\",\"firstTrialDepartment\":null,\"finalTrialStaffName\":null,\"finalTrialTime\":null,\"finalTrialDepartment\":null,\"auditFirstTrialName\":null,\"auditFirstTrialTime\":null,\"auditFirstTrialDept\":null,\"auditStaffName\":null,\"auditName\":null,\"auditStatus\":\"未稽核\"},{\"caseCode\":null,\"transportId\":\"100390265\",\"custName\":\"经营信贷\",\"processNode\":\"等待签订合同\",\"productName\":null,\"revolveFlag\":\"个贷\",\"submitDeptName\":\"个贷-北京朝阳区铜牛大厦营业部\",\"firstTrialStaffName\":\"王卉\",\"firstTrialTime\":\"2020-07-30 10:12:09.0\",\"firstTrialDepartment\":\"政策组\",\"finalTrialStaffName\":null,\"finalTrialTime\":null,\"finalTrialDepartment\":null,\"auditFirstTrialName\":null,\"auditFirstTrialTime\":null,\"auditFirstTrialDept\":null,\"auditStaffName\":null,\"auditName\":null,\"auditStatus\":\"未稽核\"}],\"pageInfo\":{\"pageNum\":60,\"pageSize\":20,\"total\":1187,\"pages\":60}}}";
        data = objectMapper.readTree(deliverySearchRes).get("data");
        int pages = data.get("pageInfo").get("pages").intValue();
        ArrayNode resultList = (ArrayNode) data.get("resultList");

        s = "";
        JsonNode blnakStr = objectMapper.readTree(s);
        System.out.println("s = \"\" 时 = " + blnakStr);

        String s1 = " ";
        JsonNode blnakStr1 = objectMapper.readTree(s1);
        System.out.println( "s1的长度为： " + s1.length() + ", s1 = \" \" 时 = " + blnakStr1);

        boolean flag1 = StringUtils.isEmpty(s1); //是否为null 或为空字符串“”
        boolean flag2 = StringUtils.isBlank(s1); // 是否内容为空，

    }


    @Test  // 时间操作
    public void localDatetimeTest(){

        System.out.println(LocalDateTime.now());
        // String --> LocalDate
        LocalDate localDate = LocalDate.parse("2019-12-07");
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        System.out.println("localdate = " + localDate.format(pattern));

        // String --> LocalTime
        LocalTime localTime = LocalTime.parse("00:00:00");

        // String -->LocalDateTime
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"); // 12小时
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // 24小时
        String localDateTime = LocalDateTime.of(localDate,localTime).format(formatter);
        System.out.println("localDateTime = " + localDateTime);

        // 日期运算
        LocalDate localDate1 = LocalDate.now();
        DateTimeFormatter dateTimeFormatter =DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String endDateTime = LocalDateTime.of(localDate1,LocalTime.parse("23:59:59")).plusDays(2).format(dateTimeFormatter);
        String startDataTime = LocalDateTime.of(localDate1.minusDays(10),LocalTime.parse("00:00:00")).format(dateTimeFormatter);
        System.out.println("localDate1 = " + localDate1);
        System.out.println("endDateTime = " + endDateTime);
        System.out.println("startDataTime = " + startDataTime);
    }
}
