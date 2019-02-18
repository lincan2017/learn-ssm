package learn.ssm.spring;

import learn.ssm.mapper.MyBatisUtil;
import learn.ssm.mapper.PetStoreMapper;
import learn.ssm.pojo.Dog;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * @author : Lin Can
 * @date : 2019/1/29 16:34
 */
public class MyBatisTest {
    private SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();

    @Test
    public void testMybatisSaveAndQuery() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            PetStoreMapper petStoreMapper = sqlSession.getMapper(PetStoreMapper.class);
            Dog dog = new Dog();
            Date date = new Date();
            dog.setName(date.toString());
            dog.setAge(5);
            dog.setOwner("Mary" + date.toString());
            petStoreMapper.insertDog(dog);
            sqlSession.commit();// 这里一定要提交，不然数据进不去数据库中

            Dog queryDog = petStoreMapper.getDogByName(dog.getName());
            Assert.assertEquals("name: " + dog.getName() + "|age: " + dog.getAge() + "|owner:" + dog.getOwner(),
                    "name: " + queryDog.getName() + "|age: " + queryDog.getAge() + "|owner:" + queryDog.getOwner());
        }
    }
}
