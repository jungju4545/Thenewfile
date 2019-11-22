package com.cos.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.crud.model.Post;
import com.cos.crud.repository.PostRepository;

@Controller
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostRepository postrepository;

	// Model은 데이터를 Controller에서 Presentation계층까지 들고간다.(jsp파일까지 들고감)
	// Get >> http://localhost8080/post/
	// Get >> http://localhost8080/post
	@GetMapping("")
	public String postList(Model model) {
		List<Post> posts = postrepository.findAll();
		model.addAttribute("posts", posts); // "posts"는 jsp에서 items와 매칭이 된다
		// webapp/WEB-INF/view/post/list.jsp
		return "/post/list";
	}

	////////////////// 수정하기////////////////////////

	// Get >> http://localhost:8080/post/writeForm
	@GetMapping("/writeForm")
	public String writeForm() {
		return "/post/writeForm";
	}

	/////////////////////// 업데이트/////////////////////////////

	// post >> http://localhost8080/post/update
	// 1. form에 넣어서 전송, 2. json으로 전송
	@PostMapping("/update")
	public String update(Post post) {
	
		try {
			postrepository.update(post);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/post";
	}

	////////////////////////// 삭제////////////////////////////////

	// /delete >> form, get방식 id=1@RequestParam("id")
	// @ResponseBody >> json
	// post >> http://localhost8080/post/delete/1
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		try {
			postrepository.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/post";
	}

	//////////////////////// 저장/////////////////////////////

	// post >> http://localhost8080/post/save
	@PostMapping("/save")
	public String save(Post post) { // id =0, createDate=null, userId=0
		try {
			postrepository.save(post);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/post";
	}

	//////////////////////////// detail///////////////////////////////////

	// Get >> http://localhost8080/post/1
	@GetMapping("/{id}")
	public String post(@PathVariable int id, Model model) {
		Post post = postrepository.findById(id);
		model.addAttribute("post", post);
		return "post/detail";
	}

	////////////////////////// 업데이트 양식//////////////////////////////////////

	@GetMapping("/updateForm/{id}")
	public String updateForm(@PathVariable int id, Model model) {
		Post post = postrepository.findById(id);
		model.addAttribute("post", post);
		return "post/updateForm";
	}

}
