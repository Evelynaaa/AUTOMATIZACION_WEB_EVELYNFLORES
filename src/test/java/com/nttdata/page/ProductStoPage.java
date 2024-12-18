package com.nttdata.page;

import org.openqa.selenium.By;

public class ProductStoPage {

    public static By email = By.id("field-email");
    public static By password = By.id("field-password");
    public static By login = By.id("submit-login");
    public static By iniciarSesion = By.xpath("//div[@class=\"user-info\"]/a");
    public static By txtnombre = By.xpath("//*[@id=\"_desktop_user_info\"]/div/a[2]/span");
    public static By product1 = By.xpath("//*[@id=\"js-product-list\"]/div[1]/div/article/div/div[1]/a/picture/img");
    public static By anadiUnidades = By.xpath("//*[@id=\"add-to-cart-or-refresh\"]/div[2]/div/div[1]/div/span[3]/button[1]");
    public static By anadircarrito = By.xpath("//*[@id=\"add-to-cart-or-refresh\"]/div[2]/div/div[2]/button");
    public static By txtTitulo = By.xpath("//*[@id=\"myModalLabel\"]");
    public static By preciounidadlbl1 = By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[1]/div/div[2]/p");
    public static By cantidadlbl = By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[1]/div/div[2]/span[3]/strong");
    public static By totaltext = By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[2]/div/p[4]/span[2]");
    public static By btnFinalizarCompra = By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[2]/div/div/a");
    public static By titulocarrito = By.xpath("//*[@id=\"main\"]/div/div[1]/div/div[1]/h1");
    public static By txtPrecioUnidad2 = By.xpath("//*[@id=\"main\"]/div/div[1]/div/div[2]/ul/li/div/div[2]/div[2]/div[2]/span");
    public static By txtCantidad2 = By.xpath("//*[@id=\"cart-subtotal-products\"]/span[1]");
    public static By txtPrecioTotal2 = By.xpath("//*[@id=\"main\"]/div/div[1]/div/div[2]/ul/li/div/div[3]/div/div[2]/div/div[2]/span/strong");

    public static By getCategoria(String Categoria) {
        String cate="";
        if(Categoria.equals("Clothes")){
            cate = "//*[@id=\"category-3\"]/a";
        }
        return By.xpath(cate);
    }

    public static By getSubCategoria(String subCategoria){
        String subcate="";
        if (subCategoria.equals("Men")){
            subcate="//*[@id=\"subcategories\"]/ul/li[1]/div[1]/a";
        }
        return By.xpath(subcate);
    }


}
