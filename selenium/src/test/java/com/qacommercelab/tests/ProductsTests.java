package com.qacommercelab.tests;

import com.qacommercelab.base.BaseTest;
import com.qacommercelab.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Epic("QA Commerce Lab")
@Feature("Product Catalog")
public class ProductsTests extends BaseTest {

    @Test(groups = { "smoke", "products" }, description = "Product catalog displays all products")
    public void productCatalogLoads() {

        ProductsPage productsPage = new ProductsPage(driver);

        productsPage.open();

        Assert.assertEquals(
                productsPage.getVisibleProductCount(),
                12,
                "Expected 12 products in the catalog.");

        Assert.assertEquals(
                productsPage.getResultCountText(),
                "12 results");
    }

    @Test(groups = { "regression", "products" }, description = "Search finds product by name")
    public void searchByProductName() {

        ProductsPage productsPage = new ProductsPage(driver);

        productsPage.open();
        productsPage.search("keyboard");

        Assert.assertEquals(
                productsPage.getVisibleProductCount(),
                1);

        Assert.assertTrue(
                productsPage.isProductDisplayed(2));

        Assert.assertTrue(
                productsPage
                        .getProductCardText(2)
                        .contains("Pulse 75 Mechanical Keyboard"));
    }

    @Test(groups = { "regression", "products" }, description = "Product search is case insensitive")
    public void searchIsCaseInsensitive() {

        ProductsPage productsPage = new ProductsPage(driver);

        productsPage.open();
        productsPage.search("KEYBOARD");

        Assert.assertEquals(
                productsPage.getVisibleProductCount(),
                1);

        Assert.assertTrue(
                productsPage.isProductDisplayed(2));
    }

    @Test(groups = { "regression", "products" }, description = "Search trims leading and trailing whitespace")
    public void searchTrimsWhitespace() {

        ProductsPage productsPage = new ProductsPage(driver);

        productsPage.open();
        productsPage.search("   keyboard   ");

        Assert.assertEquals(
                productsPage.getVisibleProductCount(),
                1);

        Assert.assertTrue(
                productsPage.isProductDisplayed(2));
    }

    @Test(groups = { "regression", "products" }, description = "No-match search displays empty state")
    public void noResultsSearchDisplaysEmptyState() {

        ProductsPage productsPage = new ProductsPage(driver);

        productsPage.open();
        productsPage.search("this-product-does-not-exist");

        Assert.assertEquals(
                productsPage.getVisibleProductCount(),
                0);

        Assert.assertTrue(
                productsPage.isEmptyStateDisplayed());

        Assert.assertEquals(
                productsPage.getResultCountText(),
                "0 results");
    }

    @Test(groups = { "regression", "products" }, description = "Category filtering displays matching products")
    public void filterByAccessoriesCategory() {

        ProductsPage productsPage = new ProductsPage(driver);

        productsPage.open();
        productsPage.selectCategory("Accessories");

        Assert.assertEquals(
                productsPage.getVisibleProductCount(),
                3);

        Assert.assertEquals(
                productsPage.getResultCountText(),
                "3 results");
    }

    @Test(groups = { "regression", "products" }, description = "Products sort by price ascending")
    public void sortByPriceAscending() {

        ProductsPage productsPage = new ProductsPage(driver);

        productsPage.open();
        productsPage.selectSort("Price ↑");

        List<Double> actual = productsPage.getVisiblePrices();

        List<Double> expected = new ArrayList<>(actual);

        expected.sort(Comparator.naturalOrder());

        Assert.assertEquals(
                actual,
                expected,
                "Products are not sorted by ascending price.");

        Assert.assertEquals(
                actual.getFirst(),
                0.0,
                "Zero-price product should appear first.");
    }

    @Test(groups = { "regression", "products" }, description = "Products sort by price descending")
    public void sortByPriceDescending() {

        ProductsPage productsPage = new ProductsPage(driver);

        productsPage.open();
        productsPage.selectSort("Price ↓");

        List<Double> actual = productsPage.getVisiblePrices();

        List<Double> expected = new ArrayList<>(actual);

        expected.sort(Comparator.reverseOrder());

        Assert.assertEquals(
                actual,
                expected,
                "Products are not sorted by descending price.");

        Assert.assertEquals(
                actual.getFirst(),
                99999.99);
    }

    @Test(groups = { "regression", "products" }, description = "Unicode product name renders correctly")
    public void unicodeProductIsDisplayedCorrectly() {

        ProductsPage productsPage = new ProductsPage(driver);

        productsPage.open();

        String productText = productsPage.getProductCardText(4);

        Assert.assertTrue(
                productText.contains(
                        "Café Élan Travel Mug — إصدار محدود"));
    }

    @Test(groups = { "regression", "products" }, description = "Out-of-stock product cannot be added to cart")
    public void outOfStockProductCannotBeAdded() {

        ProductsPage productsPage = new ProductsPage(driver);

        productsPage.open();

        Assert.assertTrue(
                productsPage
                        .getProductCardText(3)
                        .contains("Out of stock"));

        Assert.assertFalse(
                productsPage.isAddToCartEnabled(3),
                "Out-of-stock Add to Cart button should be disabled.");
    }

    @Test(groups = { "regression", "products" }, description = "Zero-price product is displayed correctly")
    public void zeroPriceProductIsDisplayed() {

        ProductsPage productsPage = new ProductsPage(driver);

        productsPage.open();

        String productText = productsPage.getProductCardText(7);

        Assert.assertTrue(
                productText.contains("$0.00"));

        Assert.assertTrue(
                productsPage.isAddToCartEnabled(7));
    }

    @Test(groups = { "smoke", "products", "cart" }, description = "In-stock product can be added to cart")
    public void addProductToCart() {

        ProductsPage productsPage = new ProductsPage(driver);

        productsPage.open();

        Assert.assertEquals(
                productsPage.getCartCount(),
                0);

        productsPage.addProductToCart(10);

        Assert.assertEquals(
                productsPage.getCartCount(),
                1,
                "Cart count did not increase after adding product.");
    }
}