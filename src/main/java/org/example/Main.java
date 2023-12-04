package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EdgeOptions options = new EdgeOptions();
        //options.addArguments("--headless=new");

        System.out.println("Enter the expression to integrate with respect to x: ");
        String expression = scanner.nextLine();

        System.out.println();

        WebDriver driver = new EdgeDriver(options);
        driver.get("https://www.integral-calculator.com/");
        System.out.println("Trying to load page...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("go")));
        System.out.println("Page loaded.");

        System.out.println();

        driver.findElement(By.id("expression")).sendKeys(expression);
        driver.findElement(By.id("go")).click();
        System.out.println("Expression entered.");

        System.out.println();

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#manual-antiderivative > .export-button img")));
        System.out.println("Solution found.");

        WebElement expressionButton = driver.findElement(By.cssSelector("#manual-antiderivative > .export-button img"));
        expressionButton.click();

        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
        System.out.println("Result:");
        System.out.println(driver.findElement(By.xpath("/html/body/pre[3]")).getText());

        driver.quit();

    }
}