package com.ci.project.web.rest;

import com.ci.project.CiProjectApp;
import com.ci.project.domain.Project;
import com.ci.project.repository.ProjectRepository;
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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.ci.project.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ProjectResource} REST controller.
 */
@SpringBootTest(classes = CiProjectApp.class)
public class ProjectResourceIT {

    private static final String DEFAULT_PROJECT_NO = "AAAAAAAAAA";
    private static final String UPDATED_PROJECT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_CIS_PROGRAM_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_CIS_PROGRAM_TYPE = "BBBBBBBBBB";

    private static final Instant DEFAULT_PROJ_EFF_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PROJ_EFF_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CUST_NO = "AAAAAAAAAA";
    private static final String UPDATED_CUST_NO = "BBBBBBBBBB";

    private static final String DEFAULT_CUST_CONTACT_ID = "AAAAAAAAAA";
    private static final String UPDATED_CUST_CONTACT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PROJ_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PROJ_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PROJ_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_PROJ_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_PROJ_ADDL_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_PROJ_ADDL_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_PROJ_CITY = "AAAAAAAAAA";
    private static final String UPDATED_PROJ_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_PROJ_STATE = "AAAAAAAAAA";
    private static final String UPDATED_PROJ_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_PROJ_ZIP_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PROJ_ZIP_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PROJ_COUNTY = "AAAAAAAAAA";
    private static final String UPDATED_PROJ_COUNTY = "BBBBBBBBBB";

    private static final Instant DEFAULT_PROJ_EST_START_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PROJ_EST_START_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_PROJ_EST_COMPLETION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PROJ_EST_COMPLETION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_PROJ_EST_DRAWDOWN_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PROJ_EST_DRAWDOWN_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_PROJ_CUST_INT_IND = "AAAAAAAAAA";
    private static final String UPDATED_PROJ_CUST_INT_IND = "BBBBBBBBBB";

    @Autowired
    private ProjectRepository projectRepository;

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

    private MockMvc restProjectMockMvc;

    private Project project;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProjectResource projectResource = new ProjectResource(projectRepository);
        this.restProjectMockMvc = MockMvcBuilders.standaloneSetup(projectResource)
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
    public static Project createEntity(EntityManager em) {
        Project project = new Project()
            .projectNo(DEFAULT_PROJECT_NO)
            .cisProgramType(DEFAULT_CIS_PROGRAM_TYPE)
            .projEffDate(DEFAULT_PROJ_EFF_DATE)
            .custNo(DEFAULT_CUST_NO)
            .custContactId(DEFAULT_CUST_CONTACT_ID)
            .projName(DEFAULT_PROJ_NAME)
            .projAddress(DEFAULT_PROJ_ADDRESS)
            .projAddlAddress(DEFAULT_PROJ_ADDL_ADDRESS)
            .projCity(DEFAULT_PROJ_CITY)
            .projState(DEFAULT_PROJ_STATE)
            .projZipCode(DEFAULT_PROJ_ZIP_CODE)
            .projCounty(DEFAULT_PROJ_COUNTY)
            .projEstStartDate(DEFAULT_PROJ_EST_START_DATE)
            .projEstCompletionDate(DEFAULT_PROJ_EST_COMPLETION_DATE)
            .projEstDrawdownDate(DEFAULT_PROJ_EST_DRAWDOWN_DATE)
            .projCustIntInd(DEFAULT_PROJ_CUST_INT_IND);
        return project;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Project createUpdatedEntity(EntityManager em) {
        Project project = new Project()
            .projectNo(UPDATED_PROJECT_NO)
            .cisProgramType(UPDATED_CIS_PROGRAM_TYPE)
            .projEffDate(UPDATED_PROJ_EFF_DATE)
            .custNo(UPDATED_CUST_NO)
            .custContactId(UPDATED_CUST_CONTACT_ID)
            .projName(UPDATED_PROJ_NAME)
            .projAddress(UPDATED_PROJ_ADDRESS)
            .projAddlAddress(UPDATED_PROJ_ADDL_ADDRESS)
            .projCity(UPDATED_PROJ_CITY)
            .projState(UPDATED_PROJ_STATE)
            .projZipCode(UPDATED_PROJ_ZIP_CODE)
            .projCounty(UPDATED_PROJ_COUNTY)
            .projEstStartDate(UPDATED_PROJ_EST_START_DATE)
            .projEstCompletionDate(UPDATED_PROJ_EST_COMPLETION_DATE)
            .projEstDrawdownDate(UPDATED_PROJ_EST_DRAWDOWN_DATE)
            .projCustIntInd(UPDATED_PROJ_CUST_INT_IND);
        return project;
    }

    @BeforeEach
    public void initTest() {
        project = createEntity(em);
    }

    @Test
    @Transactional
    public void createProject() throws Exception {
        int databaseSizeBeforeCreate = projectRepository.findAll().size();

        // Create the Project
        restProjectMockMvc.perform(post("/api/projects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(project)))
            .andExpect(status().isCreated());

        // Validate the Project in the database
        List<Project> projectList = projectRepository.findAll();
        assertThat(projectList).hasSize(databaseSizeBeforeCreate + 1);
        Project testProject = projectList.get(projectList.size() - 1);
        assertThat(testProject.getProjectNo()).isEqualTo(DEFAULT_PROJECT_NO);
        assertThat(testProject.getCisProgramType()).isEqualTo(DEFAULT_CIS_PROGRAM_TYPE);
        assertThat(testProject.getProjEffDate()).isEqualTo(DEFAULT_PROJ_EFF_DATE);
        assertThat(testProject.getCustNo()).isEqualTo(DEFAULT_CUST_NO);
        assertThat(testProject.getCustContactId()).isEqualTo(DEFAULT_CUST_CONTACT_ID);
        assertThat(testProject.getProjName()).isEqualTo(DEFAULT_PROJ_NAME);
        assertThat(testProject.getProjAddress()).isEqualTo(DEFAULT_PROJ_ADDRESS);
        assertThat(testProject.getProjAddlAddress()).isEqualTo(DEFAULT_PROJ_ADDL_ADDRESS);
        assertThat(testProject.getProjCity()).isEqualTo(DEFAULT_PROJ_CITY);
        assertThat(testProject.getProjState()).isEqualTo(DEFAULT_PROJ_STATE);
        assertThat(testProject.getProjZipCode()).isEqualTo(DEFAULT_PROJ_ZIP_CODE);
        assertThat(testProject.getProjCounty()).isEqualTo(DEFAULT_PROJ_COUNTY);
        assertThat(testProject.getProjEstStartDate()).isEqualTo(DEFAULT_PROJ_EST_START_DATE);
        assertThat(testProject.getProjEstCompletionDate()).isEqualTo(DEFAULT_PROJ_EST_COMPLETION_DATE);
        assertThat(testProject.getProjEstDrawdownDate()).isEqualTo(DEFAULT_PROJ_EST_DRAWDOWN_DATE);
        assertThat(testProject.getProjCustIntInd()).isEqualTo(DEFAULT_PROJ_CUST_INT_IND);
    }

    @Test
    @Transactional
    public void createProjectWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = projectRepository.findAll().size();

        // Create the Project with an existing ID
        project.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectMockMvc.perform(post("/api/projects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(project)))
            .andExpect(status().isBadRequest());

        // Validate the Project in the database
        List<Project> projectList = projectRepository.findAll();
        assertThat(projectList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProjects() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList
        restProjectMockMvc.perform(get("/api/projects?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(project.getId().intValue())))
            .andExpect(jsonPath("$.[*].projectNo").value(hasItem(DEFAULT_PROJECT_NO)))
            .andExpect(jsonPath("$.[*].cisProgramType").value(hasItem(DEFAULT_CIS_PROGRAM_TYPE)))
            .andExpect(jsonPath("$.[*].projEffDate").value(hasItem(DEFAULT_PROJ_EFF_DATE.toString())))
            .andExpect(jsonPath("$.[*].custNo").value(hasItem(DEFAULT_CUST_NO)))
            .andExpect(jsonPath("$.[*].custContactId").value(hasItem(DEFAULT_CUST_CONTACT_ID)))
            .andExpect(jsonPath("$.[*].projName").value(hasItem(DEFAULT_PROJ_NAME)))
            .andExpect(jsonPath("$.[*].projAddress").value(hasItem(DEFAULT_PROJ_ADDRESS)))
            .andExpect(jsonPath("$.[*].projAddlAddress").value(hasItem(DEFAULT_PROJ_ADDL_ADDRESS)))
            .andExpect(jsonPath("$.[*].projCity").value(hasItem(DEFAULT_PROJ_CITY)))
            .andExpect(jsonPath("$.[*].projState").value(hasItem(DEFAULT_PROJ_STATE)))
            .andExpect(jsonPath("$.[*].projZipCode").value(hasItem(DEFAULT_PROJ_ZIP_CODE)))
            .andExpect(jsonPath("$.[*].projCounty").value(hasItem(DEFAULT_PROJ_COUNTY)))
            .andExpect(jsonPath("$.[*].projEstStartDate").value(hasItem(DEFAULT_PROJ_EST_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].projEstCompletionDate").value(hasItem(DEFAULT_PROJ_EST_COMPLETION_DATE.toString())))
            .andExpect(jsonPath("$.[*].projEstDrawdownDate").value(hasItem(DEFAULT_PROJ_EST_DRAWDOWN_DATE.toString())))
            .andExpect(jsonPath("$.[*].projCustIntInd").value(hasItem(DEFAULT_PROJ_CUST_INT_IND)));
    }
    
    @Test
    @Transactional
    public void getProject() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get the project
        restProjectMockMvc.perform(get("/api/projects/{id}", project.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(project.getId().intValue()))
            .andExpect(jsonPath("$.projectNo").value(DEFAULT_PROJECT_NO))
            .andExpect(jsonPath("$.cisProgramType").value(DEFAULT_CIS_PROGRAM_TYPE))
            .andExpect(jsonPath("$.projEffDate").value(DEFAULT_PROJ_EFF_DATE.toString()))
            .andExpect(jsonPath("$.custNo").value(DEFAULT_CUST_NO))
            .andExpect(jsonPath("$.custContactId").value(DEFAULT_CUST_CONTACT_ID))
            .andExpect(jsonPath("$.projName").value(DEFAULT_PROJ_NAME))
            .andExpect(jsonPath("$.projAddress").value(DEFAULT_PROJ_ADDRESS))
            .andExpect(jsonPath("$.projAddlAddress").value(DEFAULT_PROJ_ADDL_ADDRESS))
            .andExpect(jsonPath("$.projCity").value(DEFAULT_PROJ_CITY))
            .andExpect(jsonPath("$.projState").value(DEFAULT_PROJ_STATE))
            .andExpect(jsonPath("$.projZipCode").value(DEFAULT_PROJ_ZIP_CODE))
            .andExpect(jsonPath("$.projCounty").value(DEFAULT_PROJ_COUNTY))
            .andExpect(jsonPath("$.projEstStartDate").value(DEFAULT_PROJ_EST_START_DATE.toString()))
            .andExpect(jsonPath("$.projEstCompletionDate").value(DEFAULT_PROJ_EST_COMPLETION_DATE.toString()))
            .andExpect(jsonPath("$.projEstDrawdownDate").value(DEFAULT_PROJ_EST_DRAWDOWN_DATE.toString()))
            .andExpect(jsonPath("$.projCustIntInd").value(DEFAULT_PROJ_CUST_INT_IND));
    }

    @Test
    @Transactional
    public void getNonExistingProject() throws Exception {
        // Get the project
        restProjectMockMvc.perform(get("/api/projects/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProject() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        int databaseSizeBeforeUpdate = projectRepository.findAll().size();

        // Update the project
        Project updatedProject = projectRepository.findById(project.getId()).get();
        // Disconnect from session so that the updates on updatedProject are not directly saved in db
        em.detach(updatedProject);
        updatedProject
            .projectNo(UPDATED_PROJECT_NO)
            .cisProgramType(UPDATED_CIS_PROGRAM_TYPE)
            .projEffDate(UPDATED_PROJ_EFF_DATE)
            .custNo(UPDATED_CUST_NO)
            .custContactId(UPDATED_CUST_CONTACT_ID)
            .projName(UPDATED_PROJ_NAME)
            .projAddress(UPDATED_PROJ_ADDRESS)
            .projAddlAddress(UPDATED_PROJ_ADDL_ADDRESS)
            .projCity(UPDATED_PROJ_CITY)
            .projState(UPDATED_PROJ_STATE)
            .projZipCode(UPDATED_PROJ_ZIP_CODE)
            .projCounty(UPDATED_PROJ_COUNTY)
            .projEstStartDate(UPDATED_PROJ_EST_START_DATE)
            .projEstCompletionDate(UPDATED_PROJ_EST_COMPLETION_DATE)
            .projEstDrawdownDate(UPDATED_PROJ_EST_DRAWDOWN_DATE)
            .projCustIntInd(UPDATED_PROJ_CUST_INT_IND);

        restProjectMockMvc.perform(put("/api/projects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedProject)))
            .andExpect(status().isOk());

        // Validate the Project in the database
        List<Project> projectList = projectRepository.findAll();
        assertThat(projectList).hasSize(databaseSizeBeforeUpdate);
        Project testProject = projectList.get(projectList.size() - 1);
        assertThat(testProject.getProjectNo()).isEqualTo(UPDATED_PROJECT_NO);
        assertThat(testProject.getCisProgramType()).isEqualTo(UPDATED_CIS_PROGRAM_TYPE);
        assertThat(testProject.getProjEffDate()).isEqualTo(UPDATED_PROJ_EFF_DATE);
        assertThat(testProject.getCustNo()).isEqualTo(UPDATED_CUST_NO);
        assertThat(testProject.getCustContactId()).isEqualTo(UPDATED_CUST_CONTACT_ID);
        assertThat(testProject.getProjName()).isEqualTo(UPDATED_PROJ_NAME);
        assertThat(testProject.getProjAddress()).isEqualTo(UPDATED_PROJ_ADDRESS);
        assertThat(testProject.getProjAddlAddress()).isEqualTo(UPDATED_PROJ_ADDL_ADDRESS);
        assertThat(testProject.getProjCity()).isEqualTo(UPDATED_PROJ_CITY);
        assertThat(testProject.getProjState()).isEqualTo(UPDATED_PROJ_STATE);
        assertThat(testProject.getProjZipCode()).isEqualTo(UPDATED_PROJ_ZIP_CODE);
        assertThat(testProject.getProjCounty()).isEqualTo(UPDATED_PROJ_COUNTY);
        assertThat(testProject.getProjEstStartDate()).isEqualTo(UPDATED_PROJ_EST_START_DATE);
        assertThat(testProject.getProjEstCompletionDate()).isEqualTo(UPDATED_PROJ_EST_COMPLETION_DATE);
        assertThat(testProject.getProjEstDrawdownDate()).isEqualTo(UPDATED_PROJ_EST_DRAWDOWN_DATE);
        assertThat(testProject.getProjCustIntInd()).isEqualTo(UPDATED_PROJ_CUST_INT_IND);
    }

    @Test
    @Transactional
    public void updateNonExistingProject() throws Exception {
        int databaseSizeBeforeUpdate = projectRepository.findAll().size();

        // Create the Project

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProjectMockMvc.perform(put("/api/projects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(project)))
            .andExpect(status().isBadRequest());

        // Validate the Project in the database
        List<Project> projectList = projectRepository.findAll();
        assertThat(projectList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProject() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        int databaseSizeBeforeDelete = projectRepository.findAll().size();

        // Delete the project
        restProjectMockMvc.perform(delete("/api/projects/{id}", project.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Project> projectList = projectRepository.findAll();
        assertThat(projectList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
