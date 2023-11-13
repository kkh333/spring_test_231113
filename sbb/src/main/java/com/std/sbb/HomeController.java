package com.std.sbb;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.Cluster;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

//개발자가 스프링부트에게 말한다.
//아래 있는 HomeController는 컨트롤러이다.
@Controller
public class HomeController {

    //전역변수가 필요할 때 이렇게 빈껍데기만 생성해주고, 생성자 함수를 이용해서 넣어주기
    private int num;
    private List<Person> peopleList;

    HomeController () {
        num = 0;
        peopleList = new ArrayList<>();
    }

    //개발자가 스프링부트에게 말한다.
    //만약에 /home/main 이런 요청이 오면 아래 메서드를 실행시켜줘
    @GetMapping("/home/main")
    @ResponseBody
    public String ShowMain() {
        return "안녕하세요!";
    }

    @GetMapping("/home/main2")
    @ResponseBody
    public String ShowMain2() {
        return "반갑습니다";
    }

    @GetMapping("/home/main3")
    @ResponseBody
    public String ShowMain3() {
        return "즐거웠습니다.";
    }

    @GetMapping("/home/response")
    @ResponseBody
    public String response() {
        num++;
        return "응답 : " + num;
    }

    @GetMapping("/home/plus")
    @ResponseBody
    //@RequestParam(defaultValue = "0")는 값이 주어지지 않을 때 정해놓은 값이다. 무조건 값이 들어간다면 없어도 된다.
    //정수를 써줄 때도 ""를 써야한다.
    public int plus(@RequestParam(defaultValue = "0") int a, @RequestParam(defaultValue = "0")int b) {
        return a + b;
    }

    @GetMapping("/home/returnBoolean")
    @ResponseBody
    public boolean returnBoolean() {
        return true;
    }

    @GetMapping("/home/returnDouble")
    @ResponseBody
    public double returnDouble() {
        return Math.PI;
    }

    @GetMapping("/home/returnIntArray")
    @ResponseBody
    public int[] returnIntArray() {
        int[] arr = new int[]{1, 2, 3};
        return arr;
    }

    @GetMapping("/home/returnIntList")
    @ResponseBody
    public List<Integer> returnIntList() {
        List<Integer> list = new ArrayList<>() {{
           add(10);
           add(20);
           add(30);
        }};
        return list;
    }

    @GetMapping("/home/returnMap")
    @ResponseBody
    public Map<String, Object> showReturnMap() {
        Map<String, Object> map = new LinkedHashMap<>() {{
            put("id", 1);
            put("speed", 100);
            put("name", "카니발");
            put("relatedIds", new ArrayList<>(){{
                add(2);
                add(3);
                add(4);
            }});
        }};
        return map;
    }

    @GetMapping("/home/returnCarV2")
    @ResponseBody
    public CarV2 showReturnCarV2() {
        CarV2 carV2 = new CarV2(1, 100, "카니발", new ArrayList<>() {{
            add(2);
            add(3);
            add(4);
        }});

        carV2.setName(carV2.getName() + "V2");

        return carV2;
    }

    @GetMapping("/home/addPerson")
    @ResponseBody
    public String addPerson (String name, int age) {
        Person person = new Person(name, age);
        peopleList.add(person);
        return person.getId() + "번 사람이 추가되었습니다.";
    };

    @GetMapping("/home/viewPerson")
    @ResponseBody
    public List<Person> viewPerson () {
        return peopleList;
    };

    @Getter
    @AllArgsConstructor
    class CarV2 {
        private final int id;
        private final int speed;
        @Setter
        private String name;
        private final List<Integer> relatedIds;
    }

    @Getter
    @AllArgsConstructor
    class Person {
        private static int lastId;
        private final int id;
        private final String name;
        private final int age;

        static {
            lastId = 0;
        }

        Person(String name, int age) {
            this.id = ++lastId;
            this.name = name;
            this.age = age;
        }
    }




}
