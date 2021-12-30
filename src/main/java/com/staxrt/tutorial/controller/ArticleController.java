
package com.staxrt.tutorial.controller;

import com.staxrt.tutorial.model.Article;
import com.staxrt.tutorial.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

@RestController
@RequestMapping("/api/v1")
public class ArticleController {
    
    @Autowired
    private ArticleRepository ArticleRepository;

    /**
     * Get articles pageable.
     *
     * @return the list
     */
    @GetMapping("/articles")
    public Page<Article> articlePageable(Pageable pageable) {
        return ArticleRepository.findAll(pageable);
    }

    /**
     * Create article.
     *
     * @param article the article
     * @return the article
     */
    @PostMapping("/articles")
    public Article createArticle(@Valid @RequestBody Article article) {
        return ArticleRepository.save(article);
    }
    
    /**
     * Get count of published articles on daily bases for the 7 days.
     *
     * @return the list
     * @throws ParseException
     */
    @GetMapping("/articles/statistics")
    public List<Long> getCountOfPublishedArticles(@RequestParam("startDate") String startDate) throws ParseException {
        List<Long> countList = new ArrayList<Long>();

        int days = 0;
        while (days < 7) {
            countList.add(ArticleRepository.countByPublishDate(startDate));

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            c.setTime(sdf.parse(startDate));
            c.add(Calendar.DATE, 1);  // 1 day to add
            startDate = sdf.format(c.getTime());  // startDate is now the new date

            days ++;
        }
        return countList;
    }

}
