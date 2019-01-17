package learn.ssm.spring;

import learn.ssm.config.DataBaseConnect;
import learn.ssm.pojo.Cat;
import learn.ssm.service.PetStoreService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.util.List;

/**
 * @author : Lin Can
 * @date : 2019/1/17 12:39
 */
public class PetStoreDaoTest extends BaseJunitTest {
    @Autowired
    private PetStoreService storeService;

    @Test
    public void testDriver() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
    }

    @Test
    public void testGetConnect() {
        Connection connection = DataBaseConnect.getConnection();
        Assert.assertNotNull(connection);
        DataBaseConnect.close(connection);
    }

    @Test
    public void testCount() {
        Assert.assertEquals(3, storeService.countCats());
    }

    @Test
    public void testQuery() throws Exception {
        List<Cat> cats = storeService.getCats();
        Assert.assertEquals(3, cats.size());
        for (Cat cat : cats) {
            String name = cat.getName();
            String owner = cat.getOwner();

            switch (name) {
                case "tom":

                    Assert.assertEquals("tomy", owner);

                    break;
                case "Kity":

                    Assert.assertEquals("Mary", owner);

                    break;
                case "Jerry":

                    Assert.assertEquals("John", owner);

                    break;
                default:

                    Assert.fail("This cat is not query from database. It is not a good cat!");
                    break;
            }
        }
    }

    @Test
    public void testUpdate() throws Exception {
        Cat updateCat = new Cat();
        updateCat.setId(1);
        updateCat.setName("Tom");
        updateCat.setAge(2);
        updateCat.setOwner("Tomi");

        int updateRows = storeService.updateCatById(updateCat);
        Assert.assertEquals(1, updateRows);

        List<Cat> cats = storeService.getCats();
        Assert.assertEquals(3, cats.size());
        for (Cat cat : cats) {
            String name = cat.getName();
            String owner = cat.getOwner();

            switch (name) {
                case "Tom":

                    Assert.assertEquals("Tomi", owner);

                    break;
                case "Kity":

                    Assert.assertEquals("Mary", owner);

                    break;
                case "Jerry":

                    Assert.assertEquals("John", owner);

                    break;
                default:

                    Assert.fail("This cat is not query from database. It is not a good cat!");
                    break;
            }
        }
    }

    @Test
    public void testIsA() {
        Assert.assertTrue(Number.class.isAssignableFrom(Integer.class));
        Assert.assertFalse(Integer.class.isAssignableFrom(Number.class));
        Assert.assertTrue(Integer.class.isInstance(1));
        Assert.assertFalse(String.class.isInstance(2));
    }
}
