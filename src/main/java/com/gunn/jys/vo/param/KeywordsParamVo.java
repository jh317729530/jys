package com.gunn.jys.vo.param;

import java.util.List;

public class KeywordsParamVo {

   private List<Integer> selectedKeywordIds;

    private List<Integer> noSelectedKeywordIds;

    public List<Integer> getSelectedKeywordIds() {
        return selectedKeywordIds;
    }

    public void setSelectedKeywordIds(List<Integer> selectedKeywordIds) {
        this.selectedKeywordIds = selectedKeywordIds;
    }

    public List<Integer> getNoSelectedKeywordIds() {
        return noSelectedKeywordIds;
    }

    public void setNoSelectedKeywordIds(List<Integer> noSelectedKeywordIds) {
        this.noSelectedKeywordIds = noSelectedKeywordIds;
    }
}
