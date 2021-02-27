package com.example.gdkim.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After // Junit 에서 단위테스트 끝날때마다 수행되는 메소드 지정, 배포전 전체 테스트 수행시 테스트간 데이터 침범 방지
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        String title = "테스트 게시글";
        String content = "게시글 본문";

        //save : insert/update 쿼리 실행 id값이 있다면 update, 없다면 insert 쿼리 실행
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("rlejr3000@naver.com")
                .build());
        //when
        //findAll : 테이블 posts에 있는 모든 데이터 조회
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);


    }

    //JPA Auditing 테스크 코드 작성하기
    @Test
    public void BaseTimeEntity_등록(){

        //given
        LocalDateTime now = LocalDateTime.of(2021,2,4,0,0,0);
        postsRepository.save(Posts.builder().title("title").content("content").author("author").build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        System.out.println(">>>>>>>>>>>>>> createData = "+posts.getCreateDate()+", modifiedDate = "+posts.getModifiedDate());

        assertThat(posts.getCreateDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);

    }


}
