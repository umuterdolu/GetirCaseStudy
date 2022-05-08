package com.readingisgood.controller.book;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * @author Umut Ismet Erdolu
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/com/readingisgood/controller/book")
public class BookControllerRunner {
}
