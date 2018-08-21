package com.example.demo.controller;


import com.example.demo.DemoBooksApplicationApi;
import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoBooksApplicationApi.class)
@AutoConfigureMockMvc
@Transactional
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;
    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters){
        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();
        Assert.assertNotNull("the JSON message converter must not be null", this.mappingJackson2HttpMessageConverter);
    }

    @SuppressWarnings("unchecked")
    protected String json(Object o) throws IOException{
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

    // ----------create book-----------
    @Test
    public void shood_create_valid_book_and_return_created_status() throws Exception {
        Book book = new Book("123-1234567890","My new book","Publisher");
        book.addAuthor(new Author("Judas", "Priest"));
        mockMvc.perform(post("/api/books/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json(book)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", is("http://localhost/api/books/123-1234567890")))
                .andExpect(content().string(""))
                .andDo(MockMvcResultHandlers.print());
    }

}
