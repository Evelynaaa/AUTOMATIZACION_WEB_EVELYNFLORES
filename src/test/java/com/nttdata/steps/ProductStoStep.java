package com.nttdata.steps;

import com.nttdata.page.ProductStoPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductStoStep {
    private WebDriver driver;

    public ProductStoStep(WebDriver driver) {
        this.driver = driver;
    }

    public void typeUser (String user){
        WebElement userInputElement = driver.findElement(ProductStoPage.email);
        userInputElement.sendKeys(user);
    }

    public void typePassword(String password){
        WebElement userInputElement = driver.findElement(ProductStoPage.password);
        userInputElement.sendKeys(password);
    }

    public void login() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(ProductStoPage.login));

        driver.findElement(ProductStoPage.login).click();
    }

    public String getNombre(){
        return this.driver.findElement(ProductStoPage.txtnombre).getText();
    }


    public String getTitulo() {
        return this.driver.findElement(ProductStoPage.txtTitulo).getText();
    }



    public String getPrecioUnidad() {
        return this.driver.findElement(ProductStoPage.preciounidadlbl1).getText();
    }

    public String getCantidad() {
        return this.driver.findElement(ProductStoPage.cantidadlbl).getText();
    }

    public String getPrecioTotal() {
        return this.driver.findElement(ProductStoPage.totaltext).getText();
    }


    public String getTituloCarrito(){
        return this.driver.findElement(ProductStoPage.titulocarrito).getText();
    }


    public String getPrecioUnidad2() {
        return this.driver.findElement(ProductStoPage.txtPrecioUnidad2).getText();
    }

    public String getCantidad2() {
        return this.driver.findElement(ProductStoPage.txtCantidad2).getText();
    }

    public String getPrecioTotal2() {
        return this.driver.findElement(ProductStoPage.txtPrecioTotal2).getText();
    }


}
