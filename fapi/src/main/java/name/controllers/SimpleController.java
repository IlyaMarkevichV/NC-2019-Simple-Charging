package name.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    @RequestMapping(value = "/get-simple-object")
    SimpleObject getSimpleString()
    { SimpleObject simpleObject=new SimpleObject();
        simpleObject.setId("1");
        simpleObject.setName("name");
        return simpleObject;

    }

    class SimpleObject {
        String id; String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }}