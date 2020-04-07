package com.aoher.controller;

import com.aoher.domain.ProductEntity;
import com.aoher.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CartControllerTest {

    @InjectMocks
    private CartController controller;

    @Mock
    private ProductService service;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        controller.init();
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(
                get("/cart/get")
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void testAddCartOperations() throws Exception {
        ProductEntity p = new ProductEntity(1L,"","","",10d,null);
        when(service.findById(1L)).thenReturn(java.util.Optional.of(p));

        mockMvc.perform(
                get("/cart/updateProductAmountInCart/1/1/false")
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

        mockMvc.perform(
                get("/cart/updateProductAmountInCart/1/1/false")
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].amount", is(2)));

        mockMvc.perform(
                get("/cart/updateProductAmountInCart/1/3/true")
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].amount", is(3)));
    }

    @Test
    public void testSubtractionOperations() throws Exception{
        ProductEntity p = new ProductEntity(1L,"","","",10d,null);
        when(service.findById(1L)).thenReturn(java.util.Optional.of(p));

        mockMvc.perform(
                get("/cart/updateProductAmountInCart/1/3/true")
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].amount", is(3)));

        mockMvc.perform(
                get("/cart/subtractProductFromCart/1")
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].amount", is(2)));

        mockMvc.perform(
                get("/cart/subtractProductFromCart/1").
                        accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].amount", is(1)));


        //PRODUCT EXPECTED TO BE REMOVED
        mockMvc.perform(
                get("/cart/subtractProductFromCart/1")
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void testEmtyCart() throws Exception{
        ProductEntity p = new ProductEntity(1L,"","","",10d,null);
        when(service.findById(1L)).thenReturn(java.util.Optional.of(p));

        mockMvc.perform(
                get("/cart/updateProductAmountInCart/1/3/true")
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].amount", is(3)));

        //EXPECT EMPTY CART
        mockMvc.perform(
                delete("/cart/emtyCart")
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
}