package com.gunn.jys.util;

import com.gunn.jys.constant.common.DatePattern;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.math.BigDecimal;
import java.util.Date;

public class PoiUtil {

    /**
     * 方法名: getStringValue</br>
     * 详述: 获取表格中的字符串描述 </br>
     * 开发人员：yuanbao</br>
     * 创建时间：2017年5月5日</br>
     *
     * @param cell
     * @return
     */
    public static String getStringValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        int cellType = cell.getCellType();
        switch (cellType) {
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            case Cell.CELL_TYPE_NUMERIC:
                String value = null;
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    Date date = cell.getDateCellValue();
                    value = DateFormatUtils.format(date, "yyyy/MM/dd");
                } else {
                    value = new BigDecimal(cell.getNumericCellValue()).toString();
                }
                return value;
            case Cell.CELL_TYPE_BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case Cell.CELL_TYPE_FORMULA:
                return cell.getCellFormula();
            case Cell.CELL_TYPE_BLANK:
                return "";
            case Cell.CELL_TYPE_ERROR:
                return String.valueOf(cell.getErrorCellValue());
            default:
                return "";
        }
    }

    public static BigDecimal getBigDecimal(Cell cell) {
        String value = getStringValue(cell);
        if (StringUtils.isBlank(value)) {
            return null;
        }
        return new BigDecimal(value);
    }

    public static Date getDate(Cell cell, DatePattern pattern) {
        String value = getStringValue(cell);
        if (StringUtils.isBlank(value)) {
            return null;
        }
        return DateUtils.parse(value, pattern);
    }

    public static Integer getInteger(Cell cell) {
        String value = getStringValue(cell);
        if (StringUtils.isBlank(value)) {
            return null;
        }
        return Integer.parseInt(value);
    }

    public static CellRangeAddress getRangeAddressIfMerged(Sheet sheet, int row, int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if (row >= firstRow && row <= lastRow) {
                if (column >= firstColumn && column <= lastColumn) {
                    return range;
                }
            }
        }
        return null;
    }
}
