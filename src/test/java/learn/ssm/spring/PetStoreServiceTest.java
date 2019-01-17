package learn.ssm.spring;

import learn.ssm.service.PetStoreService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author : Lin Can
 * @date : 2019/1/17 9:47
 */
public class PetStoreServiceTest extends BaseJunitTest{

    @Autowired
    private PetStoreService petStoreService;

    @Test
    public void testAutowired() {
        Assert.assertNotNull(petStoreService);
        Assert.assertNotNull(petStoreService.getCat());
        Assert.assertNotNull(petStoreService.getPetStoreDao());
    }
}
