package org.example;

import org.example.db.ConnectJDBC;
import org.example.models.Car;
import org.example.repository.CarRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ApplJPARepositoryTests {

    @Autowired
    private CarRepository carRepository;

    @BeforeAll
    static void impDB() throws FileNotFoundException {
        ConnectJDBC conn = new ConnectJDBC("postgres", "123456");
        //conn.DropDB("db_car");
        conn.CreatDB("db_car");
        conn.connToDB("db_car", "");
        conn.exeScript("imp_db_car.sql");
        conn.connClose();
    }

    @Test
    public void testFindByModel(){
        List<Car> cars =carRepository.findByModel("Ярис");
        assertNotNull(cars);
        assertEquals("Ярис", cars.getFirst().getModel());
        assertEquals(2, cars.size());
    }
    @Test
    public void testCount(){
        var count = carRepository.count();
        assertEquals(15,count);

    }
//    @AfterAll
//    static void dropDB(){
//        ConnectJDBC conn = new ConnectJDBC("postgres", "123456");
//        conn.DropDB("db_car");
//        conn.connClose();
//    }




}
