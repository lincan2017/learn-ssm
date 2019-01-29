package learn.ssm.spring;

import learn.ssm.mapper.MyBatisUtil;
import learn.ssm.mapper.PetStoreMapper;
import learn.ssm.pojo.Dog;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

/**
 * @author : Lin Can
 * @date : 2019/1/29 16:34
 */
public class MyBatisTest {
    private SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();

    @Test
    public void test1() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            PetStoreMapper petStoreMapper = sqlSession.getMapper(PetStoreMapper.class);
            Dog dog = new Dog();
            dog.setName("funny");
            dog.setAge(5);
            dog.setOwner("Mary");
            petStoreMapper.insertDog(dog);
            sqlSession.commit();// 这里一定要提交，不然数据进不去数据库中
        }
    }

    @Test
    public void test2() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            PetStoreMapper petStoreMapper = sqlSession.getMapper(PetStoreMapper.class);
            Dog dog = petStoreMapper.getDogByName("funny");
            System.out.println("name: " + dog.getName() + "|age: "
                    + dog.getAge());
        }
    }
}
