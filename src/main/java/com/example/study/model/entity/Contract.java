package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Accessors(chain = true)
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long            idx;

    private String          code;
    private String          name;
    private Integer         contractTypeIdx;
    private String          userCode;
    private Integer         departmentIdx;
    private String          content;

    private String          ownerName;
    private String          ownerBusinessNumber;
    private String          ownerAddress;

    private String          otherName;
    private String          otherBusinessNumber;
    private String          otherAddress;

    private LocalDate       startDate;
    private LocalDate       endDate;

    private String          contractAmount;
    private String          contractQty;

    private LocalDate       registerDate;
    private String          registerUser;
    private LocalDate       updateDate;
    private String          updateUser;


}
