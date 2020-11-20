package com.example.study.config;

import com.example.study.model.entity.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpec {

    public static Specification<User> userCode(String userCode) {
        return (userCode!=null)
                ? (Specification<User>) ((root, query, builder) -> builder.equal(root.get("userCode"), userCode) )
                : (Specification<User>) ((root, query, builder) -> builder.and() );
    }

    public static Specification<User> userName(String name) {
        return (name!=null)
                ? (Specification<User>) ((root, query, builder) -> builder.equal(root.get("name"), name) )
                : (Specification<User>) ((root, query, builder) -> builder.and() );
    }

    public static Specification<User> department_idx(Integer department_idx) {
        return (department_idx!=null)
                ? (Specification<User>) ((root, query, builder) -> builder.equal(root.get("department_idx"), department_idx) )
                : (Specification<User>) ((root, query, builder) -> builder.and() );
    }

    public static Specification<User> team_idx(Integer team_idx) {
        return (team_idx!=null)
                ? (Specification<User>) ((root, query, builder) -> builder.equal(root.get("team_idx"), team_idx) )
                : (Specification<User>) ((root, query, builder) -> builder.and() );
    }

    public static Specification<User> rank_idx(Integer rank_idx) {
        return (rank_idx!=null)
                ? (Specification<User>) ((root, query, builder) -> builder.equal(root.get("rank_idx"), rank_idx) )
                : (Specification<User>) ((root, query, builder) -> builder.and() );
    }


}
