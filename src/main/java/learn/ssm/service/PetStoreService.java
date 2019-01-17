package learn.ssm.service;

import learn.ssm.config.DataBaseConnect;
import learn.ssm.dao.PetStoreDao;
import learn.ssm.pojo.Cat;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    private DataBaseConnect connect;
    @Autowired
    private Cat cat;

    public List<Cat> getCats () throws SQLException {
        List<Map> list = petStoreDao.query(Cat.class);
        List<Cat> catList = new ArrayList();
        for (Map map : list) {
            Cat cat = new Cat();
            cat.setName((String)map.get("name"));
            cat.setOwner((String)map.get("owner"));
            catList.add(cat);
        }
        return catList;
    }

    public long countCats() {
        return petStoreDao.count(Cat.class);
    }

    public int updateCatById(Cat updateCat) throws IllegalAccessException{
        return petStoreDao.updateById(Cat.class,updateCat);
    }
}
