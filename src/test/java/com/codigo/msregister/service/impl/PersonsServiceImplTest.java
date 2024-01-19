package com.codigo.msregister.service.impl;

import com.codigo.msregister.aggregates.response.ResponseBase;
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