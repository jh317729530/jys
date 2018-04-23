package com.gunn.jys.service.impl;

import com.gunn.jys.base.impl.BaseServiceImpl;
import com.gunn.jys.elasticsearch.entity.Article;
import com.gunn.jys.service.ResearchService;
import com.gunn.jys.util.PoiUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResearchServiceImpl implements ResearchService {

    @Override
    public List<Article> batchLoad(InputStream inputStream) {
        Workbook workbook = null;
        List<Article> articles = new ArrayList<>();
        try {
            workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            for (int i=1;i<= lastRowNum;i++) {
                Row row = sheet.getRow(i);
                ArticleLoadResult alr = new ArticleLoadResult(i + 1, true);
                Article article = parseRow(row, alr);
                if (null == article) {
                    continue;
                }
                alr.setArticle(article);
                // 判断是否已经有解析性错误, 有则不继续
                articles.add(article);
            }
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return articles;
    }

    private Article parseRow(Row row, ArticleLoadResult alr) {
        int i = 0;
        String title = PoiUtil.getStringValue(row.getCell(i++));
        String shortContent = PoiUtil.getStringValue(row.getCell(i++));
        String url = PoiUtil.getStringValue(row.getCell(i++));
        Article article = new Article();
        article.setTitle(title);
        article.setShortContent(shortContent);
        article.setUrl(url);
        return article;
    }

    public static class ArticleLoadResult {
        private int row;
        private boolean status;
        private List<String> errMsgs;
        private Article article;

        public ArticleLoadResult(int row, boolean status) {
            super();
            this.row = row;
            this.status = status;
        }

        public void addErrMsg(String errMsg) {
            if (StringUtils.isBlank(errMsg)) {
                return ;
            }
            if (errMsgs == null) {
                errMsgs = new ArrayList<String>();
            }
            errMsgs.add(errMsg);
            status = false;
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public List<String> getErrMsgs() {
            return errMsgs;
        }

        public void setErrMsgs(List<String> errMsgs) {
            this.errMsgs = errMsgs;
        }

        public Article getArticle() {
            return article;
        }

        public void setArticle(Article article) {
            this.article = article;
        }
    }
}
