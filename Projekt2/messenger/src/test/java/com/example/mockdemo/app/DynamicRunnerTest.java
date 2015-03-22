package com.example.mockdemo.app;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;




@RunWith(Suite.class)
@Suite.SuiteClasses({
	   DynamicProxyServiceTest.class,
	   DynamicProxyServiceTest2.class
	})

public class DynamicRunnerTest {
	//klasa uruchamiajaca klasy z rodziny DynamicProxyService
}
