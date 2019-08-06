package com.modules.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * @Author 李非凡
 * @Description:
 * 系统设置实体类
 * @Date 2019/7/25 16:18
 * @Version 1.0
 */
public class SystemSetting implements Serializable {

    private static final long serialVersionUID = 6059575427819108325L;

    /**
     * 唯一id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 系统代号
     */
    @TableField("systemCode")
    private String systemCode;

    /**
     * 网站名称
     */
    private String name;

    /**
     * 门户地址根路径
     */
    @TableField("httpPath")
    private String httpPath;

    /**
     * 后台地址根路径
     */
    @TableField("manageHttp")
    private String manageHttp;

    /**
     * 网站门户的log图标地址
     */
    private String log;

    /**
     * 网站标题
     */
    private String title;

    /**
     * 网站描述
     */
    private String description;

    /**
     * 网站关键字
     */
    private String keywords;

    /**
     * 网站图标
     */
    private String shortcuticon;

    /**
     * 联系地址
     */
    private String address;

    /**
     * 联系电话
     */
    private String tel;

    /**
     * 邮箱
     */
    private String email;

    /**
     * qq的html页面内容
     */
    @TableField("qqHelpHtml")
    private String qqHelpHtml;

    /**
     * 备案号
     */
    private String icp;

    /**
     * 是否开放网站 y:开放 n:不开放
     */
    private String isopen;

    /**
     * 网站关闭信息
     */
    @TableField("closeMsg")
    private String closeMsg;

    /**
     * qq号码
     */
    private String qq;

    /**
     * 站长统计代码
     */
    @TableField("statisticsCode")
    private String statisticsCode;

    /**
     * 系统版本相关信息
     */
    private String version;

    /**
     * y：启动响应式;n:禁用响应式 默认y
     */
    @TableField("openResponsive")
    private String openResponsive;

    /**
     * 图片根路径
     */
    @TableField("imageRootPath")
    private String imageRootPath;

    /**
     * 商品的默认图片
     */
    @TableField("defaultProductImg")
    private String defaultProductImg;

    /**
     * 图集
     */
    private String images;

    /**
     * 叶子节点图标
     */
    @TableField("manageLeftTreeLeafIcon")
    private String manageLeftTreeLeafIcon;

    public Integer getId() {
        return id;
    }

    public SystemSetting setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public SystemSetting setSystemCode(String systemCode) {
        this.systemCode = systemCode;
        return this;
    }

    public String getName() {
        return name;
    }

    public SystemSetting setName(String name) {
        this.name = name;
        return this;
    }

    public String getHttpPath() {
        return httpPath;
    }

    public SystemSetting setHttpPath(String httpPath) {
        this.httpPath = httpPath;
        return this;
    }

    public String getManageHttp() {
        return manageHttp;
    }

    public SystemSetting setManageHttp(String manageHttp) {
        this.manageHttp = manageHttp;
        return this;
    }

    public String getLog() {
        return log;
    }

    public SystemSetting setLog(String log) {
        this.log = log;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public SystemSetting setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SystemSetting setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getKeywords() {
        return keywords;
    }

    public SystemSetting setKeywords(String keywords) {
        this.keywords = keywords;
        return this;
    }

    public String getShortcuticon() {
        return shortcuticon;
    }

    public SystemSetting setShortcuticon(String shortcuticon) {
        this.shortcuticon = shortcuticon;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public SystemSetting setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getTel() {
        return tel;
    }

    public SystemSetting setTel(String tel) {
        this.tel = tel;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public SystemSetting setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getQqHelpHtml() {
        return qqHelpHtml;
    }

    public SystemSetting setQqHelpHtml(String qqHelpHtml) {
        this.qqHelpHtml = qqHelpHtml;
        return this;
    }

    public String getIcp() {
        return icp;
    }

    public SystemSetting setIcp(String icp) {
        this.icp = icp;
        return this;
    }

    public String getIsopen() {
        return isopen;
    }

    public SystemSetting setIsopen(String isopen) {
        this.isopen = isopen;
        return this;
    }

    public String getCloseMsg() {
        return closeMsg;
    }

    public SystemSetting setCloseMsg(String closeMsg) {
        this.closeMsg = closeMsg;
        return this;
    }

    public String getQq() {
        return qq;
    }

    public SystemSetting setQq(String qq) {
        this.qq = qq;
        return this;
    }

    public String getStatisticsCode() {
        return statisticsCode;
    }

    public SystemSetting setStatisticsCode(String statisticsCode) {
        this.statisticsCode = statisticsCode;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public SystemSetting setVersion(String version) {
        this.version = version;
        return this;
    }

    public String getOpenResponsive() {
        return openResponsive;
    }

    public SystemSetting setOpenResponsive(String openResponsive) {
        this.openResponsive = openResponsive;
        return this;
    }

    public String getImageRootPath() {
        return imageRootPath;
    }

    public SystemSetting setImageRootPath(String imageRootPath) {
        this.imageRootPath = imageRootPath;
        return this;
    }

    public String getDefaultProductImg() {
        return defaultProductImg;
    }

    public SystemSetting setDefaultProductImg(String defaultProductImg) {
        this.defaultProductImg = defaultProductImg;
        return this;
    }

    public String getImages() {
        return images;
    }

    public SystemSetting setImages(String images) {
        this.images = images;
        return this;
    }

    public String getManageLeftTreeLeafIcon() {
        return manageLeftTreeLeafIcon;
    }

    public SystemSetting setManageLeftTreeLeafIcon(String manageLeftTreeLeafIcon) {
        this.manageLeftTreeLeafIcon = manageLeftTreeLeafIcon;
        return this;
    }

    @Override
    public String toString() {
        return "SystemSetting{" +
                "id=" + id +
                ", systemCode='" + systemCode + '\'' +
                ", name='" + name + '\'' +
                ", httpPath='" + httpPath + '\'' +
                ", manageHttp='" + manageHttp + '\'' +
                ", log='" + log + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", keywords='" + keywords + '\'' +
                ", shortcuticon='" + shortcuticon + '\'' +
                ", address='" + address + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", qqHelpHtml='" + qqHelpHtml + '\'' +
                ", icp='" + icp + '\'' +
                ", isopen='" + isopen + '\'' +
                ", closeMsg='" + closeMsg + '\'' +
                ", qq='" + qq + '\'' +
                ", statisticsCode='" + statisticsCode + '\'' +
                ", version='" + version + '\'' +
                ", openResponsive='" + openResponsive + '\'' +
                ", imageRootPath='" + imageRootPath + '\'' +
                ", defaultProductImg='" + defaultProductImg + '\'' +
                ", images='" + images + '\'' +
                ", manageLeftTreeLeafIcon='" + manageLeftTreeLeafIcon + '\'' +
                '}';
    }
}
