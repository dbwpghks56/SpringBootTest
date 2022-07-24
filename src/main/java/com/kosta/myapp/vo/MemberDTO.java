package com.kosta.myapp.vo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "mid")
@Entity
@Table(name = "tbl_members")
public class MemberDTO {
	@Id
	private String mid;
	private String mpassword;
	private String mname;
	
	@Enumerated(EnumType.STRING)
	private MemberRoleEnum mrole;
	
	@OneToMany(cascade = CascadeType.ALL,
			fetch = FetchType.LAZY )
    @JoinColumn(name = "mid")
	private List<ChatRoomDTO> rooms;
}
