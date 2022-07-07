package com.kosta.myapp.vo.multikey;

import java.sql.Timestamp;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "thl_order_detail2")
public class MultiKeyChild2 {
	
	@EmbeddedId
	MultiKeyParent2 orderId;
	
	private String orderGoods;
	private String orderUser;
	
	@CreationTimestamp
	private Timestamp orderDate;
}
