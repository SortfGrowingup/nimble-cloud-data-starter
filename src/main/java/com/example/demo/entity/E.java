package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author SortfGrowingup
 * @version 1.0
 * @date 2021/1/21 12:31
 */
@Data
@Entity
@Table(name = "sys_article")
public class E implements Serializable {
    public static final String DELFLAG_YES = "1";
    public static final String DELFLAG_NO = "0";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy="uuid2")
    //@GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(columnDefinition = "VARCHAR(36) COMMENT 'uuid'")
    private String uuid;

    @Column(columnDefinition = "BIGINT(13) COMMENT '创建时间'")
    private long createTime;

    @Column(columnDefinition = "VARCHAR(36) COMMENT '创建人UUID'")
    private String createOper;

    @Column(columnDefinition = "BIGINT(13) COMMENT '修改时间'")
    private long updateTime;

    @Column(columnDefinition = "VARCHAR(36) COMMENT '修改人UUID'")
    private String updateOper;

    @Column(columnDefinition = "VARCHAR(1) COMMENT '逻辑删除' DEFAULT '1'")
    private String delFlag;

    @Column(columnDefinition = "INT(11) COMMENT '排序' DEFAULT 0")
    private int sort;

    @Column(columnDefinition = "VARCHAR(36) COMMENT '姓名'")
    private String name;
}