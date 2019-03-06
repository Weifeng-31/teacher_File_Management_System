package com.cqy.util;

import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/**
 * 身份证工具类
 *
 * @author June
 * @version 1.0, 2010-06-17
 */
public class IdCardUtil {
    // 省份
    private String province;
    // 城市
    // 年份
    private int year;
    // 月份
    private int month;
    // 日期
    private int day;
    // 性别
    private String gender;
    // 出生日期
    private Date birthday;
    //年龄
    private int age;

    private Map<String, String> provinceCodeMap = new HashMap<String, String>() {
        {
            this.put("11", "北京市");
            this.put("12", "天津市");
            this.put("13", "河北省");
            this.put("14", "山西省");
            this.put("15", "内蒙古自治区");
            this.put("21", "辽宁省");
            this.put("22", "吉林省");
            this.put("23", "黑龙江省");
            this.put("31", "上海市");
            this.put("32", "江苏省");
            this.put("33", "浙江省");
            this.put("34", "安徽省");
            this.put("35", "福建省");
            this.put("36", "江西省");
            this.put("37", "山东省");
            this.put("41", "河南省");
            this.put("42", "湖北省");
            this.put("43", "湖南省");
            this.put("44", "广东省");
            this.put("45", "广西省");
            this.put("46", "海南省");
            this.put("50", "重庆省");
            this.put("51", "四川省");
            this.put("52", "贵州省");
            this.put("53", "云南省");
            this.put("54", "西藏自治区");
            this.put("61", "陕西省");
            this.put("62", "甘肃省");
            this.put("63", "青海省");
            this.put("64", "宁夏回族自治区");
            this.put("65", "新疆");
            this.put("71", "台湾");
            this.put("81", "香港");
            this.put("82", "澳门");
            this.put("91", "国外");
        }
    };
    public static Map<String,String> cityNames = new HashMap<String,String>();
    static {
        HttpServletRequest request = ServletActionContext.getRequest();
        String filePath = request.getSession().getServletContext().getRealPath("source")+ "/idcard.txt";
        File file = new File(filePath);
        if(file.exists()){
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8"));
                String tempString = null;

                while ((tempString = reader.readLine()) != null) {
                    String[] arr = tempString.split("[|]");
                    if(arr.length==2 && arr[1]!=null && arr[0]!=null){
                        cityNames.put(arr[1].trim(),arr[0].trim());
                    }
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e1) {
                    }
                }
            }

        }
    }
    public static String getCityNameByCityCode(String idCardFirst6Code)throws Exception{
        if(!cityNames.containsKey(idCardFirst6Code))
            throw new Exception("没有这个编码对应的地区");
        return cityNames.get(idCardFirst6Code);
    }

    public static String getCityNames(String idCardFirst6Code) throws Exception{
        if(idCardFirst6Code==null || idCardFirst6Code.trim().length()<6){
            throw new Exception("需要身份证的前六位,请正确输入!");
        }
        String result = "";
        String s = idCardFirst6Code.trim();
        try{
            result = "";
            result = getCityNameByCityCode(s.substring(0, 2)+"0000")+" ";
            result = getCityNameByCityCode(s.substring(0, 4)+"00")+" ";
            result = getCityNameByCityCode(s.substring(0,6));
        }catch(Exception e){
            result = "";
            result = getCityNameByCityCode(s.substring(0, 3)+"000")+" ";
            result = getCityNameByCityCode(s.substring(0,6));
        }

        return result;
    }
    private IdCardValidator validator = null;

    /**
     * 通过构造方法初始化各个成员属性
     */
    public IdCardUtil(String idcard) {
        try {
            validator = new IdCardValidator();
            if (validator.isValidatedAllIdcard(idcard)) {
                if (idcard.length() == 15) {
                    idcard = validator.convertIdcarBy15bit(idcard);
                }
                // 获取省份
                String provinceId = idcard.substring(0, 2);
                Set<String> key = this.provinceCodeMap.keySet();
                for (String id : key) {
                    if (id.equals(provinceId)) {
                        this.province = this.provinceCodeMap.get(id);
                        break;
                    }
                }

                // 获取性别
                String id17 = idcard.substring(16, 17);
                if (Integer.parseInt(id17) % 2 != 0) {
                    this.gender = "男";
                } else {
                    this.gender = "女";
                }

                // 获取出生日期
                String birthday = idcard.substring(6, 14);
                Date birthdate = new SimpleDateFormat("yyyyMMdd").parse(birthday);
                this.birthday = birthdate;
                GregorianCalendar currentDay = new GregorianCalendar();
                currentDay.setTime(birthdate);
                this.year = currentDay.get(Calendar.YEAR);
                this.month = currentDay.get(Calendar.MONTH) + 1;
                this.day = currentDay.get(Calendar.DAY_OF_MONTH);

                //获取年龄
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy");
                String year=simpleDateFormat.format(new Date());
                this.age=Integer.parseInt(year)-this.year;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the province
     */
    public String getProvince() {
        return province;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @return the month
     */
    public int getMonth() {
        return month;
    }

    /**
     * @return the day
     */
    public int getDay() {
        return day;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @return the birthday
     */

    public Date getBirthday() {
        return birthday;
    }

    @Override
    public String toString() {
        return "省份：" + this.province + ",性别：" + this.gender + ",出生日期："
                + this.birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}