package github.snailclimb.jpademo.repository;

import github.snailclimb.jpademo.model.dto.UserDTO;
import github.snailclimb.jpademo.model.po.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PersonRepositoryTest2 {
    @Autowired
    private PersonRepository personRepository;

    @Sql(scripts = {"classpath:/init.sql"})
    @Test
    public void find_person_age_older_than_18() {
        List<Person> personList = personRepository.findByAgeGreaterThan(18);
        assertEquals(1, personList.size());
    }

    @Sql(scripts = {"classpath:/init.sql"})
    @Test
    public void should_get_user_info() {
        Optional<UserDTO> userInformation = personRepository.getUserInformation(1L);
        System.out.println(userInformation.get().toString());
    }

    @Sql(scripts = {"classpath:/init.sql"})
    @Test
    public void should_get_user_info_list() {
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.Direction.DESC, "age");
        Page<UserDTO> userInformationList = personRepository.getUserInformationList(pageRequest);
        //查询结果总数
        System.out.println(userInformationList.getTotalElements());// 6
        //按照当前分页大小，总页数
        System.out.println(userInformationList.getTotalPages());// 2
        System.out.println(userInformationList.getContent());
    }
}
