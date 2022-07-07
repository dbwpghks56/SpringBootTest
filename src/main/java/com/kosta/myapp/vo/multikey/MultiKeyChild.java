package com.kosta.myapp.vo.multikey;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "thl_order_detail")
@IdClass(MultiKeyParent.class)
public class MultiKeyChild {
	@Id
	private Integer orderId;
	
	@Id
	private Integer orderSeq;
	
	private String orderGoods;
	private String orderUser;
	
	@CreationTimestamp
	private Timestamp orderDate;
}
