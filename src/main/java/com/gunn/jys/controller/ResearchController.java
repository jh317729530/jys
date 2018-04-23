package com.gunn.jys.controller;

import com.gunn.jys.annotation.Anon;
import com.gunn.jys.base.BaseController;
import com.gunn.jys.elasticsearch.dao.ArticleSearchRepository;
import com.gunn.jys.elasticsearch.entity.Article;
import com.gunn.jys.service.ResearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/research")
public class ResearchController extends BaseController {

    @Resource
    private ResearchService researchService;

    @Autowired
    private ArticleSearchRepository articleSearchRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    @RequestMapping("importData")
    @Anon
    public void importData(@RequestParam("batchArticle")MultipartFile batchArticle) {
        try {
            InputStream inputStream = batchArticle.getInputStream();
            List<Article> articles = researchService.batchLoad(inputStream);
            articles.forEach(article -> {
                IndexQuery indexQuery = new IndexQueryBuilder().withIndexName("research").withType("article").withObject(article).build();
                elasticsearchTemplate.index(indexQuery);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
