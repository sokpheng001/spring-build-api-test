package com.example.api.mbanking.api.user.validator.role;

import com.example.api.mbanking.api.user.UserMapper;
import com.example.api.mbanking.api.user.validator.role.RoleIdConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RoleIdConstraintValidator implements ConstraintValidator<RoleIdConstraint, List<Integer>> {
    private final UserMapper userMapper;
    @Override
    public boolean isValid(List<Integer> roleId, ConstraintValidatorContext context) {
        for (Integer role:roleId){
            if(!userMapper.isExistedRoleById(role)){
                return false;
            }
        }
        return true;
    }
}
