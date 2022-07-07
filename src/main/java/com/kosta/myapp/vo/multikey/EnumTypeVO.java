package com.kosta.myapp.vo.multikey;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.kosta.myapp.vo.MemberRoleEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_enum")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnumTypeVO {
	
	@Id
	private String mid;
	private String mpassword;
	private String mname;
	
	@ElementCollection(targetClass = MemberRoleEnum.class, fetch = FetchType.EAGER)
	@CollectionTable(name = "tbl_enum_mroles", joinColumns = @JoinColumn(name = "mid"))
	private Set<MemberRoleEnum> mrole;
}
