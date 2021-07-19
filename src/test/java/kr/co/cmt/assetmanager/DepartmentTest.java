package kr.co.cmt.assetmanager;

import kr.co.cmt.assetmanager.model.Department;
import kr.co.cmt.assetmanager.repository.DepartmentRepository;
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
public class DepartmentTest {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    @Order(1)
    public void save() {
        Department department = new Department();
        department.setCode("코드용");
        department.setName("개발팀");
        department.setRemark("비고");
        departmentRepository.save(department);

        Department entity = departmentRepository.findById(Long.parseLong("1")).get();

        department.setCode("코드용");

//        Assert.assertThat(authority.getCode(), is(entity.getCode()));
//        assertSame(authority, authorityRepository.findById(Long.parseLong("1")));
        Assert.assertEquals(department.getCode(), entity.getCode());
    }
}
