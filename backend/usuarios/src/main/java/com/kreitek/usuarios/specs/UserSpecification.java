package com.kreitek.usuarios.specs;


import com.kreitek.usuarios.domain.entity.User;
import com.kreitek.usuarios.specs.shared.EntitySpecification;
import com.kreitek.usuarios.specs.shared.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class UserSpecification extends EntitySpecification<User> implements Specification<User> {


    public UserSpecification(List<SearchCriteria> criteria) {
        this.criteria = criteria;
    }



}
