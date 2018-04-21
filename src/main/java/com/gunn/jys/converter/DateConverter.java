package com.gunn.jys.converter;

import com.gunn.jys.constant.common.DatePattern;
import com.gunn.jys.util.DateUtils;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateConverter implements Converter<String,Date> {

    private static final List<DatePattern> LIST = new ArrayList<>();


    static {
        LIST.add(DatePattern.LONG_DASH);
        LIST.add(DatePattern.SHORT_DASH);
    }

    public Date recursiveAnalysisDate(String source, int i) {
        if (i == LIST.size()) {
            return null;
        }
        try {
            Date date = DateUtils.parse(source, LIST.get(i));
            return date;
        } catch (Exception e) {
            return recursiveAnalysisDate(source, ++i);
        }
    }


    public Date convert(String source) {
        return recursiveAnalysisDate(source, 0);
    }
}
