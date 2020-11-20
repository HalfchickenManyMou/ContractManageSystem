package com.example.study.config;

import com.example.study.model.entity.Contract;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ContractSpec {

    public static Specification<Contract> idx(Long idx) {
        return (idx!=null)
                ? (Specification<Contract>) ((root, query, builder) -> builder.equal(root.get("idx"), idx) )
                : (Specification<Contract>) ((root, query, builder) -> builder.and() );
    }

    public static Specification<Contract> code(String code) {
        return (code!=null)
                ? (Specification<Contract>) ((root, query, builder) -> builder.equal(root.get("code"), code) )
                : (Specification<Contract>) ((root, query, builder) -> builder.and() );
    }

    public static Specification<Contract> contractName(String name) {
        return (name!=null)
                ? (Specification<Contract>) ((root, query, builder) -> builder.equal(root.get("name"), name) )
                : (Specification<Contract>) ((root, query, builder) -> builder.and() );
    }

    public static Specification<Contract> register_user(String register_user) {
        return (register_user!=null)
                ? (Specification<Contract>) ((root, query, builder) -> builder.equal(root.get("registerUser"), register_user) )
                : (Specification<Contract>) ((root, query, builder) -> builder.and() );
    }

    public static Specification<Contract> owner_name(String owner_name) {
        return (owner_name!=null)
                ? (Specification<Contract>) ((root, query, builder) -> builder.equal(root.get("ownerName"),  owner_name ) )
                : (Specification<Contract>) ((root, query, builder) -> builder.and() );
    }

    public static Specification<Contract> owner_business_number(String owner_business_number) {
        return (owner_business_number!=null)
                ? (Specification<Contract>) ((root, query, builder) -> builder.equal(root.get("ownerBusinessNumber"),  owner_business_number ) )
                : (Specification<Contract>) ((root, query, builder) -> builder.and() );
    }

    public static Specification<Contract> owner_address(String owner_address) {
        return (owner_address!=null)
                ? (Specification<Contract>) ((root, query, builder) -> builder.equal(root.get("ownerAddress"),  owner_address ) )
                : (Specification<Contract>) ((root, query, builder) -> builder.and() );
    }

    public static Specification<Contract> ohter_name(String other_name) {
        return (other_name!=null)
                ? (Specification<Contract>) ((root, query, builder) -> builder.equal(root.get("otherName"),  other_name ) )
                : (Specification<Contract>) ((root, query, builder) -> builder.and() );
    }

    public static Specification<Contract> other_business_number(String other_business_number) {
        return (other_business_number!=null)
                ? (Specification<Contract>) ((root, query, builder) -> builder.equal(root.get("otherBusinessNumber"),  other_business_number ) )
                : (Specification<Contract>) ((root, query, builder) -> builder.and() );
    }

    public static Specification<Contract> other_address(String other_address) {
        return (other_address!=null)
                ? (Specification<Contract>) ((root, query, builder) -> builder.equal(root.get("otherAddress"),  other_address ) )
                : (Specification<Contract>) ((root, query, builder) -> builder.and() );
    }

    public static Specification<Contract> start_date(String start_date) {
        return (start_date!=null)
                ? (Specification<Contract>) ((root, query, builder) -> builder.equal(root.get("startDate"),  LocalDate.parse( start_date, DateTimeFormatter.ISO_DATE)) )
                : (Specification<Contract>) ((root, query, builder) -> builder.and() );
    }

    public static Specification<Contract> end_date(String end_date) {
        return (end_date!=null)
                ? (Specification<Contract>) ((root, query, builder) -> builder.equal(root.get("endDate"),  LocalDate.parse( end_date, DateTimeFormatter.ISO_DATE)) )
                : (Specification<Contract>) ((root, query, builder) -> builder.and() );
    }
    
}
