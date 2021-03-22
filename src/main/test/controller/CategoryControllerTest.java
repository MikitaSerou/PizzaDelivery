package controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ExtendedModelMap;
import org.study.PizzaDelivery.controller.CategoryController;
import org.study.PizzaDelivery.model.Base;
import org.study.PizzaDelivery.model.Category;
import org.study.PizzaDelivery.model.Product;
import org.study.PizzaDelivery.service.BaseService;
import org.study.PizzaDelivery.service.CategoryService;
import org.study.PizzaDelivery.service.ProductService;

import java.util.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CategoryControllerTest {

    List<Base> basesList = new ArrayList<>();
    List<Category> standardCategoriesList = new ArrayList<>();
    Category customCategory = new Category();
    List<Product> cheaperProductsList = new ArrayList<>();
    Map<String, Product> top3Names = new HashMap<>();


    @Before
    public void initCategoryPage() {

        Base smallBase = new Base();
        smallBase.setId((short) 1);
        smallBase.setName("SmallBase");
        smallBase.setPriceMultiplier(1.0);

        Base mediumBase = new Base();
        mediumBase.setId((short) 2);
        mediumBase.setName("MediumBase");

        Base bigBase = new Base();
        bigBase.setId((short) 3);
        bigBase.setName("BigBase");
        bigBase.setPriceMultiplier(2.0);

        basesList.add(smallBase);
        basesList.add(mediumBase);
        basesList.add(bigBase);

        Category classicCategory = new Category();
        classicCategory.setId((short) 1);
        classicCategory.setName("Classic");
        classicCategory.setPrice(15.0);

        Category lovelyCategory = new Category();
        lovelyCategory.setId((short) 2);
        lovelyCategory.setName("Lovely");
        lovelyCategory.setPrice(17.0);

        Category premiumCategory = new Category();
        premiumCategory.setId((short) 3);
        premiumCategory.setName("Premium");
        premiumCategory.setPrice(19.0);

        customCategory.setId((short) 4);
        customCategory.setName("Custom");
        customCategory.setPrice(16.0);

        standardCategoriesList.add(classicCategory);
        standardCategoriesList.add(lovelyCategory);
        standardCategoriesList.add(premiumCategory);

        Product product0 = new Product();
        Product product1 = new Product();
        Product product2 = new Product();

        cheaperProductsList.add(product0);
        cheaperProductsList.add(product1);
        cheaperProductsList.add(product2);

        int count = 0;
        for (Product product : cheaperProductsList) {
            product.setId(count + 1);
            product.setName("product" + (count));
            product.setCategory(standardCategoriesList.get(count));
            product.setBase(basesList.get(count));
            basesList.get(count).setProducts(Collections.singletonList(product));
            product.setPrice(count + 1);
            product.setDescription("description" + (count));
            count++;
            top3Names.put(product.getName(), product);
        }
    }

    @Test
    public void categoryPageTest() throws Exception {
        CategoryService categoryService = mock(CategoryService.class);
        when(categoryService.findByName("Своя")).thenReturn(customCategory);
        when(categoryService.getAllStandardCategories()).thenReturn(standardCategoriesList);

        BaseService baseService = mock(BaseService.class);
        when(baseService.findAll()).thenReturn(basesList);
        when(baseService.findCheapest()).thenReturn(basesList.get(0));

        ProductService productService = mock(ProductService.class);
        when(productService.findAllByBase(baseService.findCheapest()))
                .thenReturn(cheaperProductsList);
        when(productService.findTop3Products()).thenReturn(top3Names);

        CategoryController categoryController = new CategoryController();

        ReflectionTestUtils.setField(categoryController, "categoryService", categoryService);
        ReflectionTestUtils.setField(categoryController, "baseService", baseService);
        ReflectionTestUtils.setField(categoryController, "productService", productService);

        ExtendedModelMap uiModel = new ExtendedModelMap();

       // TODO uiModel.addAttribute("categoryList", standardCategoriesList);

//        uiModel.addAttribute("categories", standardCategoriesList);
//        uiModel.addAttribute("bases", basesList);
//        uiModel.addAttribute("cheapestProducts", cheaperProductsList);
//        uiModel.addAttribute("top3Names", top3Names.keySet());

        List<Category> modelCategories = (List<Category>) uiModel.get("categoryList");

        assertEquals(3, modelCategories.size());
    }
}
