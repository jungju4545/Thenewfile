package com.cos.crud.repository;

import java.util.List;

import com.cos.crud.model.Post;

// 원래 방식은 @Repository 해야 메모리가 뜬다
// 그런데 DataAccessConfig에서 MapperScan할 때
// 메모리(스프링 컨테이너)에 로드됩니다.
//싱글톤으로 관리
public interface PostRepository {
	List<Post> findAll(); //return타입은 컬랙션이다.
	//delete+save = update
	void save(Post post);// 글쓰기
	void update(Post post); //수정하기, post자체가 필요하다
	Post findById(int id);//상세보기
	void delete(int id); // 삭제하기
}
