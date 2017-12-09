//
//package com.emcloud.loc.web.rest;
//
//import com.emcloud.loc.EmCloudLocApp;
//
//import com.emcloud.loc.config.SecurityBeanOverrideConfiguration;
//
//import com.emcloud.loc.domain.Area;
//import com.emcloud.loc.repository.AreaRepository;
//import com.emcloud.loc.web.rest.errors.ExceptionTranslator;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import java.time.Instant;
//import java.time.temporal.ChronoUnit;
//import java.util.List;
//
//import static com.emcloud.loc.web.rest.TestUtil.createFormattingConversionService;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.hamcrest.Matchers.hasItem;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//
///**
// * Test class for the AreaResource REST controller.
// *
// * @see AreaResource
// */
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {EmCloudLocApp.class, SecurityBeanOverrideConfiguration.class})
//public class AreaResourceIntTest {
//
//    private static final String DEFAULT_AREA_CODE = "AAAAAAAAAA";
//    private static final String UPDATED_AREA_CODE = "BBBBBBBBBB";
//
//    private static final String DEFAULT_AREA_NAME = "AAAAAAAAAA";
//    private static final String UPDATED_AREA_NAME = "BBBBBBBBBB";
//
//    private static final String DEFAULT_ZIP_CODE = "AAAAAAAAAA";
//    private static final String UPDATED_ZIP_CODE = "BBBBBBBBBB";
//
//    private static final String DEFAULT_PARENT_ID = "AAAAAAAAAA";
//    private static final String UPDATED_PARENT_ID = "BBBBBBBBBB";
//
//    private static final String DEFAULT_PARENT_NAME = "AAAAAAAAAA";
//    private static final String UPDATED_PARENT_NAME = "BBBBBBBBBB";
//
//    private static final String DEFAULT_DEPTH = "AAAAAAAAAA";
//    private static final String UPDATED_DEPTH = "BBBBBBBBBB";
//
//    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
//    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";
//
//    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
//    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);
//
//    private static final String DEFAULT_UPDATED_BY = "AAAAAAAAAA";
//    private static final String UPDATED_UPDATED_BY = "BBBBBBBBBB";
//
//    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
//    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);
//
//    @Autowired
//    private AreaRepository areaRepository;
//
//    @Autowired
//    private MappingJackson2HttpMessageConverter jacksonMessageConverter;
//
//    @Autowired
//    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;
//
//    @Autowired
//    private ExceptionTranslator exceptionTranslator;
//
//    @Autowired
//    private EntityManager em;
//
//    private MockMvc restAreaMockMvc;
//
//    private Area area;
//
//    @Before
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//        final AreaResource areaResource = new AreaResource(areaRepository);
//        this.restAreaMockMvc = MockMvcBuilders.standaloneSetup(areaResource)
//            .setCustomArgumentResolvers(pageableArgumentResolver)
//            .setControllerAdvice(exceptionTranslator)
//            .setConversionService(createFormattingConversionService())
//            .setMessageConverters(jacksonMessageConverter).build();
//    }
//
//
///**
//     * Create an entity for this test.
//     *
//     * This is a static method, as tests for other entities might also need it,
//     * if they test an entity which requires the current entity.
//     */
//
//    public static Area createEntity(EntityManager em) {
//        Area area = new Area()
//            .areaCode(DEFAULT_AREA_CODE)
//            .areaName(DEFAULT_AREA_NAME)
//            .zipCode(DEFAULT_ZIP_CODE)
//            .parentId(DEFAULT_PARENT_ID)
//            .parentName(DEFAULT_PARENT_NAME)
//            .depth(DEFAULT_DEPTH)
//            .createdBy(DEFAULT_CREATED_BY)
//            .createTime(DEFAULT_CREATE_TIME)
//            .updatedBy(DEFAULT_UPDATED_BY)
//            .updateTime(DEFAULT_UPDATE_TIME);
//        return area;
//    }
//
//    @Before
//    public void initTest() {
//        area = createEntity(em);
//    }
//
//    @Test
//    @Transactional
//    public void createArea() throws Exception {
//        int databaseSizeBeforeCreate = areaRepository.findAll().size();
//
//        // Create the Area
//        restAreaMockMvc.perform(post("/api/areas")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(area)))
//            .andExpect(status().isCreated());
//
//        // Validate the Area in the database
//        List<Area> areaList = areaRepository.findAll();
//        assertThat(areaList).hasSize(databaseSizeBeforeCreate + 1);
//        Area testArea = areaList.get(areaList.size() - 1);
//        assertThat(testArea.getAreaCode()).isEqualTo(DEFAULT_AREA_CODE);
//        assertThat(testArea.getAreaName()).isEqualTo(DEFAULT_AREA_NAME);
//        assertThat(testArea.getZipCode()).isEqualTo(DEFAULT_ZIP_CODE);
//        assertThat(testArea.getParentId()).isEqualTo(DEFAULT_PARENT_ID);
//        assertThat(testArea.getParentName()).isEqualTo(DEFAULT_PARENT_NAME);
//        assertThat(testArea.getDepth()).isEqualTo(DEFAULT_DEPTH);
//        assertThat(testArea.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
//        assertThat(testArea.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
//        assertThat(testArea.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
//        assertThat(testArea.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
//    }
//
//    @Test
//    @Transactional
//    public void createAreaWithExistingId() throws Exception {
//        int databaseSizeBeforeCreate = areaRepository.findAll().size();
//
//        // Create the Area with an existing ID
//        area.setId(1L);
//
//        // An entity with an existing ID cannot be created, so this API call must fail
//        restAreaMockMvc.perform(post("/api/areas")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(area)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the Area in the database
//        List<Area> areaList = areaRepository.findAll();
//        assertThat(areaList).hasSize(databaseSizeBeforeCreate);
//    }
//
//    @Test
//    @Transactional
//    public void checkAreaCodeIsRequired() throws Exception {
//        int databaseSizeBeforeTest = areaRepository.findAll().size();
//        // set the field null
//        area.setAreaCode(null);
//
//        // Create the Area, which fails.
//
//        restAreaMockMvc.perform(post("/api/areas")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(area)))
//            .andExpect(status().isBadRequest());
//
//        List<Area> areaList = areaRepository.findAll();
//        assertThat(areaList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    public void checkZipCodeIsRequired() throws Exception {
//        int databaseSizeBeforeTest = areaRepository.findAll().size();
//        // set the field null
//        area.setZipCode(null);
//
//        // Create the Area, which fails.
//
//        restAreaMockMvc.perform(post("/api/areas")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(area)))
//            .andExpect(status().isBadRequest());
//
//        List<Area> areaList = areaRepository.findAll();
//        assertThat(areaList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    public void checkParentIdIsRequired() throws Exception {
//        int databaseSizeBeforeTest = areaRepository.findAll().size();
//        // set the field null
//        area.setParentId(null);
//
//        // Create the Area, which fails.
//
//        restAreaMockMvc.perform(post("/api/areas")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(area)))
//            .andExpect(status().isBadRequest());
//
//        List<Area> areaList = areaRepository.findAll();
//        assertThat(areaList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    public void checkDepthIsRequired() throws Exception {
//        int databaseSizeBeforeTest = areaRepository.findAll().size();
//        // set the field null
//        area.setDepth(null);
//
//        // Create the Area, which fails.
//
//        restAreaMockMvc.perform(post("/api/areas")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(area)))
//            .andExpect(status().isBadRequest());
//
//        List<Area> areaList = areaRepository.findAll();
//        assertThat(areaList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    public void checkCreatedByIsRequired() throws Exception {
//        int databaseSizeBeforeTest = areaRepository.findAll().size();
//        // set the field null
//        area.setCreatedBy(null);
//
//        // Create the Area, which fails.
//
//        restAreaMockMvc.perform(post("/api/areas")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(area)))
//            .andExpect(status().isBadRequest());
//
//        List<Area> areaList = areaRepository.findAll();
//        assertThat(areaList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    public void checkCreateTimeIsRequired() throws Exception {
//        int databaseSizeBeforeTest = areaRepository.findAll().size();
//        // set the field null
//        area.setCreateTime(null);
//
//        // Create the Area, which fails.
//
//        restAreaMockMvc.perform(post("/api/areas")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(area)))
//            .andExpect(status().isBadRequest());
//
//        List<Area> areaList = areaRepository.findAll();
//        assertThat(areaList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    public void checkUpdatedByIsRequired() throws Exception {
//        int databaseSizeBeforeTest = areaRepository.findAll().size();
//        // set the field null
//        area.setUpdatedBy(null);
//
//        // Create the Area, which fails.
//
//        restAreaMockMvc.perform(post("/api/areas")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(area)))
//            .andExpect(status().isBadRequest());
//
//        List<Area> areaList = areaRepository.findAll();
//        assertThat(areaList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    public void checkUpdateTimeIsRequired() throws Exception {
//        int databaseSizeBeforeTest = areaRepository.findAll().size();
//        // set the field null
//        area.setUpdateTime(null);
//
//        // Create the Area, which fails.
//
//        restAreaMockMvc.perform(post("/api/areas")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(area)))
//            .andExpect(status().isBadRequest());
//
//        List<Area> areaList = areaRepository.findAll();
//        assertThat(areaList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    public void getAllAreas() throws Exception {
//        // Initialize the database
//        areaRepository.saveAndFlush(area);
//
//        // Get all the areaList
//        restAreaMockMvc.perform(get("/api/areas?sort=id,desc"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(area.getId().intValue())))
//            .andExpect(jsonPath("$.[*].areaCode").value(hasItem(DEFAULT_AREA_CODE.toString())))
//            .andExpect(jsonPath("$.[*].areaName").value(hasItem(DEFAULT_AREA_NAME.toString())))
//            .andExpect(jsonPath("$.[*].zipCode").value(hasItem(DEFAULT_ZIP_CODE.toString())))
//            .andExpect(jsonPath("$.[*].parentId").value(hasItem(DEFAULT_PARENT_ID.toString())))
//            .andExpect(jsonPath("$.[*].parentName").value(hasItem(DEFAULT_PARENT_NAME.toString())))
//            .andExpect(jsonPath("$.[*].depth").value(hasItem(DEFAULT_DEPTH.toString())))
//            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
//            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
//            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY.toString())))
//            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())));
//    }
//
//    @Test
//    @Transactional
//    public void getArea() throws Exception {
//        // Initialize the database
//        areaRepository.saveAndFlush(area);
//
//        // Get the area
//        restAreaMockMvc.perform(get("/api/areas/{id}", area.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.id").value(area.getId().intValue()))
//            .andExpect(jsonPath("$.areaCode").value(DEFAULT_AREA_CODE.toString()))
//            .andExpect(jsonPath("$.areaName").value(DEFAULT_AREA_NAME.toString()))
//            .andExpect(jsonPath("$.zipCode").value(DEFAULT_ZIP_CODE.toString()))
//            .andExpect(jsonPath("$.parentId").value(DEFAULT_PARENT_ID.toString()))
//            .andExpect(jsonPath("$.parentName").value(DEFAULT_PARENT_NAME.toString()))
//            .andExpect(jsonPath("$.depth").value(DEFAULT_DEPTH.toString()))
//            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
//            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
//            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY.toString()))
//            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()));
//    }
//
//    @Test
//    @Transactional
//    public void getNonExistingArea() throws Exception {
//        // Get the area
//        restAreaMockMvc.perform(get("/api/areas/{id}", Long.MAX_VALUE))
//            .andExpect(status().isNotFound());
//    }
//
//    @Test
//    @Transactional
//    public void updateArea() throws Exception {
//        // Initialize the database
//        areaRepository.saveAndFlush(area);
//        int databaseSizeBeforeUpdate = areaRepository.findAll().size();
//
//        // Update the area
//        Area updatedArea = areaRepository.findOne(area.getId());
//        updatedArea
//            .areaCode(UPDATED_AREA_CODE)
//            .areaName(UPDATED_AREA_NAME)
//            .zipCode(UPDATED_ZIP_CODE)
//            .parentId(UPDATED_PARENT_ID)
//            .parentName(UPDATED_PARENT_NAME)
//            .depth(UPDATED_DEPTH)
//            .createdBy(UPDATED_CREATED_BY)
//            .createTime(UPDATED_CREATE_TIME)
//            .updatedBy(UPDATED_UPDATED_BY)
//            .updateTime(UPDATED_UPDATE_TIME);
//
//        restAreaMockMvc.perform(put("/api/areas")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(updatedArea)))
//            .andExpect(status().isOk());
//
//        // Validate the Area in the database
//        List<Area> areaList = areaRepository.findAll();
//        assertThat(areaList).hasSize(databaseSizeBeforeUpdate);
//        Area testArea = areaList.get(areaList.size() - 1);
//        assertThat(testArea.getAreaCode()).isEqualTo(UPDATED_AREA_CODE);
//        assertThat(testArea.getAreaName()).isEqualTo(UPDATED_AREA_NAME);
//        assertThat(testArea.getZipCode()).isEqualTo(UPDATED_ZIP_CODE);
//        assertThat(testArea.getParentId()).isEqualTo(UPDATED_PARENT_ID);
//        assertThat(testArea.getParentName()).isEqualTo(UPDATED_PARENT_NAME);
//        assertThat(testArea.getDepth()).isEqualTo(UPDATED_DEPTH);
//        assertThat(testArea.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
//        assertThat(testArea.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
//        assertThat(testArea.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
//        assertThat(testArea.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
//    }
//
//    @Test
//    @Transactional
//    public void updateNonExistingArea() throws Exception {
//        int databaseSizeBeforeUpdate = areaRepository.findAll().size();
//
//        // Create the Area
//
//        // If the entity doesn't have an ID, it will be created instead of just being updated
//        restAreaMockMvc.perform(put("/api/areas")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(area)))
//            .andExpect(status().isCreated());
//
//        // Validate the Area in the database
//        List<Area> areaList = areaRepository.findAll();
//        assertThat(areaList).hasSize(databaseSizeBeforeUpdate + 1);
//    }
//
//    @Test
//    @Transactional
//    public void deleteArea() throws Exception {
//        // Initialize the database
//        areaRepository.saveAndFlush(area);
//        int databaseSizeBeforeDelete = areaRepository.findAll().size();
//
//        // Get the area
//        restAreaMockMvc.perform(delete("/api/areas/{id}", area.getId())
//            .accept(TestUtil.APPLICATION_JSON_UTF8))
//            .andExpect(status().isOk());
//
//        // Validate the database is empty
//        List<Area> areaList = areaRepository.findAll();
//        assertThat(areaList).hasSize(databaseSizeBeforeDelete - 1);
//    }
//
//    @Test
//    @Transactional
//    public void equalsVerifier() throws Exception {
//        TestUtil.equalsVerifier(Area.class);
//        Area area1 = new Area();
//        area1.setId(1L);
//        Area area2 = new Area();
//        area2.setId(area1.getId());
//        assertThat(area1).isEqualTo(area2);
//        area2.setId(2L);
//        assertThat(area1).isNotEqualTo(area2);
//        area1.setId(null);
//        assertThat(area1).isNotEqualTo(area2);
//    }
//}
//
