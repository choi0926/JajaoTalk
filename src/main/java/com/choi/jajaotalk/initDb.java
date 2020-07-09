package com.choi.jajaotalk;

import com.choi.jajaotalk.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class initDb {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.initCategory();
    }

    @Component
    @RequiredArgsConstructor
    @Transactional
    static class InitService{
        private final EntityManager em;

        public void initCategory(){
            Category category1 = new Category();
            category1.setCategoryName("자유");
            em.persist(category1);

            Category category2 = new Category();
            category2.setCategoryName("취미");
            em.persist(category2);

            Category category3 = new Category();
            category3.setCategoryName("연애");
            em.persist(category3);

            Category category4 = new Category();
            category4.setCategoryName("게임");
            em.persist(category4);

            Category category5 = new Category();
            category5.setCategoryName("고민");
            em.persist(category5);

        }
    }
}
