package com.kosta.myapp.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.kosta.myapp.vo.ChatMessageDTO;

@Repository
public interface ChatMessageRepository extends PagingAndSortingRepository<ChatMessageDTO, Long> {

}
