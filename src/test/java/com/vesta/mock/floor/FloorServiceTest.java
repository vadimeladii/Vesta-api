package com.vesta.mock.floor;

import com.vesta.common.FloorUtilData;
import com.vesta.exception.NotFoundException;
import com.vesta.repository.FloorRepository;
import com.vesta.repository.entity.FloorEntity;
import com.vesta.service.FloorService;
import com.vesta.service.converter.FloorConverter;
import com.vesta.service.dto.FloorDto;
import com.vesta.service.impl.FloorServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Transactional
@RunWith(MockitoJUnitRunner.class)
public class FloorServiceTest {

    private FloorService floorService;

    @Mock
    private FloorRepository floorRepository;

    private FloorConverter floorConverter = new FloorConverter();

    @Before
    public void setUp() {
        floorService = new FloorServiceImpl(floorRepository, floorConverter);
    }

    @Test
    public void test_findById_validId() {
        // given
        FloorEntity floorEntity = FloorUtilData.floorEntity();

        // when
        when(floorRepository.findById(floorEntity.getId()))
                .thenReturn(Optional.of(floorEntity));

        // then
        FloorDto returnDto = floorService.getById(floorEntity.getId());

        assertNotNull(returnDto);
        assertThat(floorEntity.getId(), is(returnDto.getId()));
        assertThat(floorEntity.getName(), is(returnDto.getName()));

        verify(floorRepository).findById(floorEntity.getId());
    }

    @Test(expected = NotFoundException.class)
    public void test_findById_invalidId() {
        floorService.getById(null);
    }

    @Test
    public void findAllID_valid() {
        //given
        FloorEntity floorEntity = FloorUtilData.floorEntity();

        //when
        when(floorRepository.findById(floorEntity.getId()))
                .thenReturn(Optional.of(floorEntity));

        //then
        FloorDto dto = floorService.getById(floorEntity.getId());

        verify(floorRepository).findById(floorEntity.getId());
        assertEquals(dto.getName(), floorEntity.getName());
        assertEquals(dto.getCompanyId(), floorEntity.getCompanyId());
    }
}