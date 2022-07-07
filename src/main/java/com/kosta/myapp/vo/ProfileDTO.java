package com.kosta.myapp.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_profile")
@EqualsAndHashCode(of = "fno")
public class ProfileDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long fno;
	private String fname;
	private boolean current_yn;
	
	@ManyToOne
	private MemberDTO member;
}
