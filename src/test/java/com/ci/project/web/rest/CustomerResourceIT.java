package com.ci.project.web.rest;

import com.ci.project.CiProjectApp;
import com.ci.project.domain.Customer;
import com.ci.project.repository.CustomerRepository;
import com.ci.project.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.ci.project.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link CustomerResource} REST controller.
 */
@SpringBootTest(classes = CiProjectApp.class)
public class CustomerResourceIT {

    private static final String DEFAULT_CUST_NO = "AAAAAAAAAA";
    private static final String UPDATED_CUST_NO = "BBBBBBBBBB";

    private static final String DEFAULT_CUST_CONTACT_ID = "AAAAAAAAAA";
    private static final String UPDATED_CUST_CONTACT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CUST_CONTACT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CUST_CONTACT_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CUST_CONTACT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CUST_CONTACT_FIRST_NAME = "BBBBBBBBBB";

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restCustomerMockMvc;

    private Customer customer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CustomerResource customerResource = new CustomerResource(customerRepository);
        this.restCustomerMockMvc = MockMvcBuilders.standaloneSetup(customerResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Customer createEntity(EntityManager em) {
        Customer customer = new Customer()
            .custNo(DEFAULT_CUST_NO)
            .custContactId(DEFAULT_CUST_CONTACT_ID)
            .custContactLastName(DEFAULT_CUST_CONTACT_LAST_NAME)
            .custContactFirstName(DEFAULT_CUST_CONTACT_FIRST_NAME);
        return customer;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Customer createUpdatedEntity(EntityManager em) {
        Customer customer = new Customer()
            .custNo(UPDATED_CUST_NO)
            .custContactId(UPDATED_CUST_CONTACT_ID)
            .custContactLastName(UPDATED_CUST_CONTACT_LAST_NAME)
            .custContactFirstName(UPDATED_CUST_CONTACT_FIRST_NAME);
        return customer;
    }

    @BeforeEach
    public void initTest() {
        customer = createEntity(em);
    }

    @Test
    @Transactional
    public void createCustomer() throws Exception {
        int databaseSizeBeforeCreate = customerRepository.findAll().size();

        // Create the Customer
        restCustomerMockMvc.perform(post("/api/customers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customer)))
            .andExpect(status().isCreated());

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeCreate + 1);
        Customer testCustomer = customerList.get(customerList.size() - 1);
        assertThat(testCustomer.getCustNo()).isEqualTo(DEFAULT_CUST_NO);
        assertThat(testCustomer.getCustContactId()).isEqualTo(DEFAULT_CUST_CONTACT_ID);
        assertThat(testCustomer.getCustContactLastName()).isEqualTo(DEFAULT_CUST_CONTACT_LAST_NAME);
        assertThat(testCustomer.getCustContactFirstName()).isEqualTo(DEFAULT_CUST_CONTACT_FIRST_NAME);
    }

    @Test
    @Transactional
    public void createCustomerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = customerRepository.findAll().size();

        // Create the Customer with an existing ID
        customer.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCustomerMockMvc.perform(post("/api/customers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customer)))
            .andExpect(status().isBadRequest());

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCustomers() throws Exception {
        // Initialize the database
        customerRepository.saveAndFlush(customer);

        // Get all the customerList
        restCustomerMockMvc.perform(get("/api/customers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(customer.getId().intValue())))
            .andExpect(jsonPath("$.[*].custNo").value(hasItem(DEFAULT_CUST_NO)))
            .andExpect(jsonPath("$.[*].custContactId").value(hasItem(DEFAULT_CUST_CONTACT_ID)))
            .andExpect(jsonPath("$.[*].custContactLastName").value(hasItem(DEFAULT_CUST_CONTACT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].custContactFirstName").value(hasItem(DEFAULT_CUST_CONTACT_FIRST_NAME)));
    }
    
    @Test
    @Transactional
    public void getCustomer() throws Exception {
        // Initialize the database
        customerRepository.saveAndFlush(customer);

        // Get the customer
        restCustomerMockMvc.perform(get("/api/customers/{id}", customer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(customer.getId().intValue()))
            .andExpect(jsonPath("$.custNo").value(DEFAULT_CUST_NO))
            .andExpect(jsonPath("$.custContactId").value(DEFAULT_CUST_CONTACT_ID))
            .andExpect(jsonPath("$.custContactLastName").value(DEFAULT_CUST_CONTACT_LAST_NAME))
            .andExpect(jsonPath("$.custContactFirstName").value(DEFAULT_CUST_CONTACT_FIRST_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingCustomer() throws Exception {
        // Get the customer
        restCustomerMockMvc.perform(get("/api/customers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCustomer() throws Exception {
        // Initialize the database
        customerRepository.saveAndFlush(customer);

        int databaseSizeBeforeUpdate = customerRepository.findAll().size();

        // Update the customer
        Customer updatedCustomer = customerRepository.findById(customer.getId()).get();
        // Disconnect from session so that the updates on updatedCustomer are not directly saved in db
        em.detach(updatedCustomer);
        updatedCustomer
            .custNo(UPDATED_CUST_NO)
            .custContactId(UPDATED_CUST_CONTACT_ID)
            .custContactLastName(UPDATED_CUST_CONTACT_LAST_NAME)
            .custContactFirstName(UPDATED_CUST_CONTACT_FIRST_NAME);

        restCustomerMockMvc.perform(put("/api/customers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCustomer)))
            .andExpect(status().isOk());

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeUpdate);
        Customer testCustomer = customerList.get(customerList.size() - 1);
        assertThat(testCustomer.getCustNo()).isEqualTo(UPDATED_CUST_NO);
        assertThat(testCustomer.getCustContactId()).isEqualTo(UPDATED_CUST_CONTACT_ID);
        assertThat(testCustomer.getCustContactLastName()).isEqualTo(UPDATED_CUST_CONTACT_LAST_NAME);
        assertThat(testCustomer.getCustContactFirstName()).isEqualTo(UPDATED_CUST_CONTACT_FIRST_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingCustomer() throws Exception {
        int databaseSizeBeforeUpdate = customerRepository.findAll().size();

        // Create the Customer

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustomerMockMvc.perform(put("/api/customers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customer)))
            .andExpect(status().isBadRequest());

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCustomer() throws Exception {
        // Initialize the database
        customerRepository.saveAndFlush(customer);

        int databaseSizeBeforeDelete = customerRepository.findAll().size();

        // Delete the customer
        restCustomerMockMvc.perform(delete("/api/customers/{id}", customer.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
