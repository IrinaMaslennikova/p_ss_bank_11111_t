package com.bank.publicinfo.validator;

import com.bank.publicinfo.dto.BranchDTO;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

/**
 * Service валидатора {@link ValidPhoneNumber}
 */

@Service
public class ProgrammaticallyValidatingService {
    private Validator validator;

    public ProgrammaticallyValidatingService(Validator validator) {
        this.validator = validator;
    }

    public void validateInputWithInjectedValidator(BranchDTO branchDTO) {
        Set<ConstraintViolation<BranchDTO>> violations = validator.validate(branchDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}

