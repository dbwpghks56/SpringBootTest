package com.kosta.myapp.vo.multikey;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MultiKeyParent implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer orderId;
	private Integer orderSeq;
}
