package learn.ssm.mapper;

import learn.ssm.pojo.Dog;

/**
 * @author : Lin Can
 * @date : 2019/1/21 17:19
 */
public interface PetStoreMapper {

    /**
     * add a dog information into db;
     * @param dog dog object
     */
    void insertDog(Dog dog);

    /**
     * get dog according to the identified id
     * @param id dog ID
     * @return Dog object
     */
    Dog getDogById(Integer id);

    /**
     * get dog according to the identified dog name
     * @param name dog name
     * @return dog object
     */
    Dog getDogByName (String name);
}
