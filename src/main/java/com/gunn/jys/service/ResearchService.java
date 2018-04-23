package com.gunn.jys.service;

import com.gunn.jys.elasticsearch.entity.Article;

import java.io.InputStream;
import java.util.List;

public interface ResearchService {

    List<Article> batchLoad(InputStream inputStream);
}
