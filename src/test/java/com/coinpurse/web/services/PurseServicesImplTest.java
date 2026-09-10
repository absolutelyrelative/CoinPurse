package com.coinpurse.web.services;

import com.coinpurse.web.model.Purse;
import com.coinpurse.web.repository.PurseRepository;
import com.coinpurse.web.repository.UserRepository;
import com.coinpurse.web.services.impl.PurseServicesImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PurseServicesImplTest {
    @Mock
    private PurseRepository purseRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PurseServicesImpl purseServices;

    private Purse purse;

    @BeforeEach
    void setUp() {
        purse = new Purse();   // assigns the field, not a new local var
        purse.setId(1L);
    }

    @Test
    void savePurse_savesAndReturnsPurse() {
        // Set up default object to return on save
        when(purseRepository.save(any(Purse.class))).thenReturn(purse);

        // Try to save result
        Purse result = purseServices.savePurse(purse);
        assertNotNull(result); // Saved result must not be null
        assertEquals(purse.getId(), result.getId()); // The id must match the stubbed obj
        verify(purseRepository).save(purse); // Make sure save was actually called at least once
    }

    @Test
    void findPurseById_findsAPurse() {
        // Set up default object to return on findById
        when(purseRepository.findById(any(Long.class))).thenReturn(Optional.of(purse));

        // Try to find purse
        Purse result = purseServices.findPurseById(1L);
        assertNotNull(result);
        assertEquals(purse, result);
    }

}
