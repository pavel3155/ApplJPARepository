package org.example;

import org.example.db.ConnectJDBC;
import org.example.models.Body;
import org.example.models.Car;
import org.example.repository.BodyRepository;
import org.example.repository.CarRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.io.FileNotFoundException;
import java.math.BigDecimal;
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
        conn.DropDB("db_car");
        conn.CreatDB("db_car");
        conn.connToDB("db_car", "");
        conn.exeScript("imp_db_car.sql");
        conn.connClose();
    }

    @Test
    public void testFindCarsByModel(){
        List<Car> cars =carRepository.findByModel("Ярис");
        assertNotNull(cars);
        assertEquals("Ярис", cars.getFirst().getModel());
        assertEquals(2, cars.size());
        System.out.println("количество машин удовлетворяющих запросу: " +cars.size());
    }
    @Test
    public void testFindByModelAndPriceLessThanEqual(){
        List<Car> cars =carRepository.findByModelAndPriceLessThanEqual("Королла", BigDecimal.valueOf(1550000.0));
        assertNotNull(cars);
        assertEquals(3, cars.size());
        System.out.println("model: " +cars.getFirst().getModel()+
                           " price: <="+cars.getFirst().getPrice()+
                           " count: "+cars.size());
    }
    @Test
    public void testCarsCount(){
        var count = carRepository.count();
        assertEquals(15,count);

    }
    @Autowired
    private BodyRepository bodyRepository;
    @Test
    public void testFindBodyByType(){
        List<Body> bodyList= bodyRepository.findByType("Универсал");
        assertNotNull(bodyList);
        assertEquals("Универсал", bodyList.getFirst().getType());
    }
    @Test
    public void testFindBodyById(){
        Body body= bodyRepository.findBodyById(2);
        assertNotNull(body);
        assertEquals("Седан", body.getType());
    }

}
