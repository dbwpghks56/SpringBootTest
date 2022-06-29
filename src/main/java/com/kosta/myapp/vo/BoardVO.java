package com.kosta.myapp.vo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
@Builder @RequiredArgsConstructor
@Entity @Table(name = "t_boards")
public class BoardVO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long bno;
	
	@NonNull // 이건 lombok, new 해서 할당할 때 꼭 있어야하는 값
	@Column(nullable = false) // 이건 DB
	private String title;
	private String writer;
	
	@Column(length = 1000)
	private String content;
	
	@CreationTimestamp
	private Timestamp regDate;
	
	@UpdateTimestamp
	private Timestamp updateDate;
}












