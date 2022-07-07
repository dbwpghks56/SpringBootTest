package com.kosta.myapp.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.kosta.myapp.vo.multikey.EnumTypeVO;

public interface EnumTypeRepository extends PagingAndSortingRepository<EnumTypeVO, String> {

}
