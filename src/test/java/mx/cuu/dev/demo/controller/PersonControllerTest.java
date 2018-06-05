package mx.cuu.dev.demo.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import mx.cuu.dev.demo.model.Person;
import mx.cuu.dev.demo.service.PersonService;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private PersonService personService;

    private JacksonTester<Person> jsonPerson;

    @Before
    public void init() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void getAllPersons_Success() throws Exception {
        List<Person> persons = Arrays.asList(new Person(1, "Person test", 30));
        Mockito.when(personService.getAllPersons()).thenReturn(persons);
        mvc.perform(get("/person").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].fullName", is("Person test")));
    }

    @Test
    public void findById_Success() throws Exception {
        Person person = new Person(1, "Person test", 30);
        Mockito.when(personService.findPersonById(1)).thenReturn(person);
        mvc.perform(get("/person/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName", is("Person test")));
    }

    @Test
    public void savePerson_Success() throws Exception {
        Person person = new Person(1, "Person test", 30);
        Mockito.when(personService.savePerson(person)).thenReturn(person);
        mvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON).content(jsonPerson.write(person).getJson()))
                .andExpect(status().isOk()).andExpect(jsonPath("$.fullName", is("Person test")));
    }
    
    @Test
    public void updatePerson_Success() throws Exception {
        Person person = new Person(1, "Person test", 30);
        Mockito.when(personService.savePerson(person)).thenReturn(person);
        mvc.perform(put("/person").contentType(MediaType.APPLICATION_JSON).content(jsonPerson.write(person).getJson()))
                .andExpect(status().isOk()).andExpect(jsonPath("$.fullName", is("Person test")));
    }
    
    @Test
    public void deletePerson_Success() throws Exception {
        doNothing().when(personService).deletePersonById(1);
        mvc.perform(delete("/person/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
    }

}
