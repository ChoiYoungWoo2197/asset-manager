package kr.co.cmt.assetmanager;


import kr.co.cmt.assetmanager.model.Authority;
import kr.co.cmt.assetmanager.repository.AuthorityRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;


@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@Transactional
public class AuthorityTest {
    @Autowired
    private AuthorityRepository authorityRepository;

    @Test
    @Order(1)
    public void save() {
        Authority authority = new Authority();
        authority.setCode("코드용");
        authority.setName("관리자");
        authority.setRemark("비고");
        authorityRepository.save(authority);

        Authority entity = authorityRepository.findById(1L).get();

        authority.setCode("코드용");

//        Assert.assertThat(authority.getCode(), is(entity.getCode()));
//        assertSame(authority, authorityRepository.findById(Long.parseLong("1")));
        Assert.assertEquals(authority.getCode(), entity.getCode());
    }
}
