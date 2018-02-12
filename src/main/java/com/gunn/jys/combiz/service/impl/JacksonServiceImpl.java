package com.gunn.jys.combiz.service.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.pagehelper.Page;
import com.gunn.jys.combiz.service.JacksonService;
import com.gunn.jys.constant.common.DatePattern;
import com.gunn.jys.util.PageUtil;
import com.sun.imageio.plugins.common.ImageUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class JacksonServiceImpl<T> implements JacksonService<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(JacksonServiceImpl.class);

//    @Resource
//    private OssService ossService;

    @Override
    public ObjectMapper getObjectMapper(boolean isPage) {

        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();


        builder.serializerByType(String.class, new JsonSerializer<String>() {

            @Override
            public void serialize(String value, JsonGenerator jgen, SerializerProvider serializers) throws IOException {
//                if (value != null && value.startsWith(Sign.SLASH + ossService.getSoPathPic()) && ImageUtil.isImage(value)) {
//                    jgen.writeString(ossService.getPicDomain() + value); // 压缩图片
//                    return;
//                }
//                if (StringUtils.contains(value, "<img")) {
//                    Pattern pattern = Pattern.compile(RegConst.SO_IMG);
//                    Matcher matcher = pattern.matcher(value);
//                    StringBuffer sb = new StringBuffer();
//                    while (matcher.find()) {
//                        String group = matcher.group();
//                        matcher.appendReplacement(sb, "\"" + ossService.getPicDomain() + group.substring(1, group.length() - 1));
//                    }
//                    matcher.appendTail(sb);
//                    jgen.writeString(sb.toString());
//                    return;
//                }
                jgen.writeString(value);
            }
        });

        if (isPage) {
            builder.serializerByType(Page.class, new JsonSerializer<Page>() {
                @Override
                public void serialize(Page value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                    com.gunn.jys.bo.Page page = PageUtil.page2page(value);
                    gen.writeRawValue(getObjectMapper(false).writeValueAsString(page));
                    return;
                }
            });
        }

        ObjectMapper objectMapper = builder.build();
        objectMapper.setDateFormat(new SimpleDateFormat(DatePattern.LONG_DASH.toString()));

        return objectMapper;
    }


    @Override
    public ObjectMapper getDefaultMapper() {
        return getObjectMapper(false);
    }

    @Override
    public T readValue(String content, Class<T> clazz) {
        try {
            return getDefaultMapper().readValue(content, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.info("jackson反序列化错误");
        }
        return null;
    }

    @Override
    public List<T> getListByType(String jsonStr, Class<?> collectionClass, Class<T> elementClass) {
        JavaType javaType = getCollectionType(collectionClass, elementClass);
        try {
            return getDefaultMapper().readValue(jsonStr, javaType);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.info("jackson反序列化错误");
        }
        return null;
    }


    @Override
    public String writeValueAsString(Object object) {
        String value = null;
        try {
            value = getObjectMapper(false).writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 获取泛型的Collection Type
     * @param collectionClass 泛型的Collection
     * @param elementClasses 元素类
     * @return JavaType Java类型
     */
    public JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return getDefaultMapper().getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }
}
