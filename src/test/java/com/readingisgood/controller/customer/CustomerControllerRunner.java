package com.readingisgood.controller.customer;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * @author Umut Ismet Erdolu
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/com/readingisgood/controller/customer")
public class CustomerControllerRunner {
}
