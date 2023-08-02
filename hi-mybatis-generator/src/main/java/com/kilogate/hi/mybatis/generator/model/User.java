package com.kilogate.hi.mybatis.generator.model;

/**
 * Database Table Remarks:
 *   用户表
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table user
 */
public class User {
    /**
     * Database Column Remarks:
     *   数据库主键id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.id
     *
     * @mbg.generated Wed Aug 02 19:06:11 CST 2023
     */
    private Integer id;

    /**
     * Database Column Remarks:
     *   姓名
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.name
     *
     * @mbg.generated Wed Aug 02 19:06:11 CST 2023
     */
    private String name;

    /**
     * Database Column Remarks:
     *   密码
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.pwd
     *
     * @mbg.generated Wed Aug 02 19:06:11 CST 2023
     */
    private String pwd;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.id
     *
     * @return the value of user.id
     *
     * @mbg.generated Wed Aug 02 19:06:11 CST 2023
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.id
     *
     * @param id the value for user.id
     *
     * @mbg.generated Wed Aug 02 19:06:11 CST 2023
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.name
     *
     * @return the value of user.name
     *
     * @mbg.generated Wed Aug 02 19:06:11 CST 2023
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.name
     *
     * @param name the value for user.name
     *
     * @mbg.generated Wed Aug 02 19:06:11 CST 2023
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.pwd
     *
     * @return the value of user.pwd
     *
     * @mbg.generated Wed Aug 02 19:06:11 CST 2023
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.pwd
     *
     * @param pwd the value for user.pwd
     *
     * @mbg.generated Wed Aug 02 19:06:11 CST 2023
     */
    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }
}