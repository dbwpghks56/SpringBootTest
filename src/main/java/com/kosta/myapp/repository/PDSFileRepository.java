package com.kosta.myapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.kosta.myapp.vo.relation.PDSFile;

public interface PDSFileRepository extends PagingAndSortingRepository<PDSFile, Long> {

}
