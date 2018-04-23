package com.gunn.jys.controller;

import com.gunn.jys.annotation.Anon;
import com.gunn.jys.base.BaseController;
import com.gunn.jys.bo.InfoResult;
import com.gunn.jys.bo.MapResult;
import com.gunn.jys.bo.Result;
import com.gunn.jys.elasticsearch.dao.ArticleSearchRepository;
import com.gunn.jys.elasticsearch.entity.Article;
import com.gunn.jys.entity.Keyword;
import com.gunn.jys.service.KeywordService;
import com.gunn.jys.service.ResearchService;
import com.gunn.jys.vo.param.KeywordsParamVo;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.*;
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

    @Resource
    private KeywordService keywordService;

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

    @RequestMapping("getKeywords")
    public Result getKeywords() {
        MapResult result = new MapResult();
        result.getInfo().put("selectedKeywords", keywordService.getListBySelected(1));
        result.getInfo().put("noSelectedKeywords", keywordService.getListBySelected(0));
        return result;
    }

    @RequestMapping("setKeywords")
    public Result setKeywords(KeywordsParamVo keywordsParamVo) {
        keywordService.updateKeywords(keywordsParamVo.getSelectedKeywordIds(), keywordsParamVo.getNoSelectedKeywordIds());
        return new Result();
    }

    @RequestMapping("getResearch")
    public Result getResearch(String title) {
        InfoResult<List<Article>> result = new InfoResult<>();
        List<Keyword> keywords = keywordService.getListBySelected(1);
        StringBuffer keywordsStr = new StringBuffer();
        if (StringUtils.isBlank(title)) {
            keywords.forEach(keyword -> {
                keywordsStr.append(" " + keyword.getKeyword());
            });
        } else {
            keywordsStr.append(title);
        }
//        qb.must(QueryBuilders.matchQuery("title", keywordsStr.toString()));
//        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(qb).build();
        String queryString = "{\n" +
                "\t\"query\" : {\n" +
                "\t\t\"match\" : {\n" +
                "\t\t\t\"title\" : \""+keywordsStr.toString()+"\"\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}";
        StringQuery sq = new StringQuery(queryString);
        List<Article> articles = elasticsearchTemplate.queryForList(sq,Article.class);
        result.setInfo(articles);
        return result;
    }

//    @RequestMapping
//    public Result list(String title) {
//        if (StringUtils.isNotBlank(title)) {
////            articleSearchRepository.search()
//        }
//    }
}
