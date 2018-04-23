package com.gunn.jys.elasticsearch.dao;

import com.gunn.jys.elasticsearch.entity.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleSearchRepository extends ElasticsearchRepository<Article, Long> {
}
