package com.nttdata.stepsdefinitions;
import com.nttdata.steps.ProductStoStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.nttdata.core.DriverManager.getDriver;
import static com.nttdata.core.DriverManager.screenShot;
import static com.nttdata.page.ProductStoPage.*;

public class ProductStoStepdefs {

    private WebDriver driver;

    private ProductStoStep productPage(WebDriver driver){
        return new ProductStoStep(driver);
    }

    @Given("dado que estoy en la página de la tienda")
    public void dadoQueEstoyEnLaPaginaDeLaTienda() {
        driver = getDriver();
        driver.get("https://qalab.bensg.com/store");
        screenShot();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        WebElement btnclickInicio = wait.until(ExpectedConditions.elementToBeClickable(iniciarSesion));
        btnclickInicio.click();

    }

    @And("me logueo con mi usuario {string} y clave {string}")
    public void meLogueoConMiUsuarioYClave(String user, String password) {
        ProductStoStep loginPage = new ProductStoStep(driver);
        loginPage.typeUser(user);
        loginPage.typePassword(password);
        loginPage.login();

        // Espera explícita a que el nombre esté visible (ejemplo usando WebDriverWait)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        wait.until(ExpectedConditions.visibilityOfElementLocated(txtnombre));
        String nombre = productPage(driver).getNombre();
        Assertions.assertEquals("Evelyn Flores Rafael", nombre, "ERROR: El texto no coincide con la palabra esperada.");
        screenShot();
    }


    @When("navego a la categoria {string} y subcategoria {string}")
    public void navegoALaCategoriaYSubcategoria(String categoria, String subcategoria) {
        driver.findElement(getCategoria(categoria)).click();
        screenShot();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        WebElement btnSubcategoria = wait.until(ExpectedConditions.elementToBeClickable(getSubCategoria(subcategoria)));
        btnSubcategoria.click();
        screenShot();

    }

    @And("agrego {int} unidades del primer producto al carrito")
    public void agregoUnidadesDelPrimerProductoAlCarrito(int unidades) {
        driver.findElement(product1).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        WebElement btnSubcategoria = wait.until(ExpectedConditions.elementToBeClickable(anadiUnidades));

        for (int i = 1; i < unidades; i++) {
            btnSubcategoria.click();
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        driver.findElement(anadircarrito).click();
        screenShot();

    }

    @Then("valido en el popup la confirmación del producto agregado")
    public void validoEnElPopupLaConfirmacionDelProductoAgregado() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(txtTitulo));
        wait.until(ExpectedConditions.textToBePresentInElement(popup, "Producto añadido correctamente a su carrito de compra"));
        String title = productPage(driver).getTitulo();
        screenShot();
        Assertions.assertTrue(title.contains("Producto añadido correctamente"),"No se encontro el texto del titulo");
    }

    public double convertirStringaDouble(String precio) {
        precio = precio.replaceAll("[^0-9.,]", "").replace(",", ".");
        double precioConvertido = Double.parseDouble(precio);
        return Math.round(precioConvertido * 100.0) / 100.0;
    }


    @And("valido en el popup que el monto total sea calculado correctamente")
    public void validoEnElPopupQueElMontoTotalSeaCalculadoCorrectamente() {

        String preciosolo = productPage(driver).getPrecioUnidad();
        double precioUnidad = convertirStringaDouble(preciosolo);

        String cantidad = productPage(driver).getCantidad();
        double cantidadPrenda = convertirStringaDouble(cantidad);

        String precioTotal = productPage(driver).getPrecioTotal();
        double precioTotal2 = convertirStringaDouble(precioTotal);

        double totalCalculado = precioUnidad * cantidadPrenda;

        Assertions.assertEquals(totalCalculado, precioTotal2, 0.01, "No coinicide el monto esperado.");
        screenShot();

        System.out.println(precioUnidad);
        System.out.println(cantidadPrenda);
        System.out.println(precioTotal2);

    }

    @When("finalizo la compra")
    public void finalizoLaCompra() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        WebElement btnFinCompra= wait.until(ExpectedConditions.elementToBeClickable(btnFinalizarCompra));
        btnFinCompra.click();
        screenShot();
    }

    @Then("valido el titulo de la pagina del carrito")
    public void validoElTituloDeLaPaginaDelCarrito() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        WebElement tituloElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(titulocarrito));

        String tituloCarrito = tituloElemento.getText();
        Assertions.assertEquals("CARRITO", tituloCarrito, "No coincide la palabra esperada.");
        screenShot();

    }

    @And("vuelvo a validar el calculo de precios en el carrito")
    public void vuelvoAValidarElCalculoDePreciosEnElCarrito() {

        String precio = productPage(driver).getPrecioUnidad2();
        double precioUnidad = convertirStringaDouble(precio);

        String cantidad = productPage(driver).getCantidad2();
        double cantidadPrenda = convertirStringaDouble(cantidad);

        String precioTotal = productPage(driver).getPrecioTotal2();
        double precioTotal2 = convertirStringaDouble(precioTotal);

        double totalCalculado = precioUnidad * cantidadPrenda;

        Assertions.assertEquals(totalCalculado, precioTotal2, 0.01, "No coinicide el monto esperado.");
        screenShot();

        System.out.println(precioUnidad);
        System.out.println(cantidadPrenda);
        System.out.println(precioTotal2);
    }
}
