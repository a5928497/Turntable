package com.yukoon.turntablegames.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class UserExcel {
    private String headTextName; //列头（标题）名
    private String propertyName; //对应字段名
    private Integer cols; //合并单元格数
//    private XSSFCellStyle cellStyle;
}
