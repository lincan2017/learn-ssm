package learn.ssm.service;

import learn.ssm.dao.PetStoreDao;
import learn.ssm.pojo.Cat;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : Lin Can
 * @date : 2019/1/17 9:36
 */
@Service
@Getter
public class PetStoreService {
    @Autowired
    private PetStoreDao petStoreDao;
    @Autowired
    private Cat cat;
}
