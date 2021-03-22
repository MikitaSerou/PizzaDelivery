import org.junit.Test;
import org.study.PizzaDelivery.model.Base;
import org.study.PizzaDelivery.model.Category;
import org.study.PizzaDelivery.model.Product;

import static org.junit.Assert.assertEquals;


public class ProductTest {

/*    @Autowired
    BaseService baseService;

    @Autowired
    CategoryService categoryService;*/


    @Test
    public void prodTest() {
        Product product = Product.builder()
                .base(new Base())
                .category(new Category())
                .describe("test")
                .name("TestProduct")
                .price(20.5)
                .build();
        assertEquals("test", product.getDescription());
        assertEquals("TestProduct", product.getName());
    }
}
