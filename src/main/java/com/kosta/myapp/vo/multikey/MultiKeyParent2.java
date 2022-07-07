package com.kosta.myapp.vo.multikey;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class MultiKeyParent2 implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer orderId;
	private Integer orderSeq;
}
