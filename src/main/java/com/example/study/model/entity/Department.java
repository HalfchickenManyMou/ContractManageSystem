package com.example.study.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String department;

    @ToString.Exclude
    @JsonManagedReference //직렬화 수행o
    @OneToMany(fetch = FetchType.EAGER ,mappedBy = "department", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Team> TeamList = new ArrayList<>();

    public void addTeam(Team team){
        team.setDepartment(this);
        getTeamList().add(team);
    }

    public void deleteTeamAll(){
        TeamList.clear();
    }
}
