package com.codigo.msregister.service.impl;

import com.codigo.msregister.aggregates.request.RequestPersons;
import com.codigo.msregister.aggregates.response.ResponseBase;
import com.codigo.msregister.aggregates.response.ResponseReniec;
import com.codigo.msregister.config.RedisService;
import com.codigo.msregister.constants.Constants;
import com.codigo.msregister.entity.PersonsEntity;
import com.codigo.msregister.feignClient.ReniecClient;
import com.codigo.msregister.repository.PersonsRepository;
import com.codigo.msregister.util.PersonsValidations;
import com.codigo.msregister.util.Util;
import net.bytebuddy.dynamic.DynamicType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.swing.text.html.Option;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PersonsServiceImplTest {
    @Mock
    ReniecClient reniecClient;
    @Mock
    PersonsValidations personsValidations;
    @Mock
    PersonsRepository personsRepository;
    @Mock
    RedisService redisService;
    @Mock
    Util util;
    @InjectMocks
    PersonsServiceImpl personsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        personsService = new PersonsServiceImpl(reniecClient, personsValidations, personsRepository, redisService, util);
    }

    @Test
    void getInfoReniec() {
    }

    @Test
    void createPersons() {
        boolean validatePersons = true;
        ResponseReniec responseReniec = new ResponseReniec("JOSE", "PEREZ", "PEREZ","1","12345678","");
        RequestPersons requestPersons = new RequestPersons("12345678","email@email.com", "9999999", 1, 1);
        PersonsEntity personsEntity = new PersonsEntity(1,"12345678",
                "JOSE",
                "PEREZ",
                "email@email.com",
                "99999999",
                1, null, null);
        ResponseBase responseBaseEsperado = new ResponseBase(Constants.CODE_SUCCESS,Constants.MESS_SUCCESS,Optional.of(personsEntity));

        PersonsServiceImpl spy = Mockito.spy(personsService);

        String numero = "12345678";


        Mockito.when(personsValidations.validateInput(Mockito.any(RequestPersons.class))).thenReturn(validatePersons);

        Mockito.doReturn(responseReniec).when(spy).getExecutionReniec(Mockito.anyString());
        Mockito.when(personsRepository.save(Mockito.any(PersonsEntity.class))).thenReturn(personsEntity);

        ResponseBase responseBaseObtenido = spy.createPersons(requestPersons);

        assertEquals(responseBaseObtenido.getCode(), responseBaseEsperado.getCode());
        assertEquals(responseBaseObtenido.getMessage(), responseBaseEsperado.getMessage());
        assertEquals(responseBaseObtenido.getData(), responseBaseEsperado.getData());
    }

    @Test
    void findOneExitoso() {
        int id = 1;
        Optional<PersonsEntity> optional = Optional.of(new PersonsEntity(id,
                "12345678",
                "Jose",
                "Fernandez",
                "email@email.com",
                "99999999",
                1, null, null));

        ResponseBase responseBase = new ResponseBase(Constants.CODE_SUCCESS, Constants.MESS_SUCCESS, optional);

        Mockito.when(personsRepository.findById(Mockito.anyInt())).thenReturn(optional);

        ResponseBase responseBase2 = personsService.findOne(id);

        assertEquals(responseBase.getCode(), responseBase2.getCode());
        assertEquals(responseBase.getMessage(), responseBase2.getMessage());
        assertEquals(responseBase.getData(), responseBase2.getData());
    }
    @Test
    void findOneError()
    {
        int id = 1;
        Optional<PersonsEntity> optionalEmpty = Optional.empty();
        ResponseBase responseBaseEsperado = new ResponseBase(Constants.CODE_ERROR,Constants.MESS_ZERO_ROWS,Optional.empty());

        Mockito.when(personsRepository.findById(Mockito.anyInt())).thenReturn(optionalEmpty);

        ResponseBase responseBaseObtenido = personsService.findOne(id);

        assertEquals(responseBaseObtenido.getCode(), responseBaseEsperado.getCode());


    }

    @Test
    void findAll() {
    }

    @Test
    void updatePersons() {
    }

    @Test
    void getExecutionReniec() {
    }
}