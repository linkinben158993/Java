package com.myclass.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myclass.entity.Videos;

@Controller
@RequestMapping("videos")
public class VideoController {
	private List<Videos> videos;
	
	public VideoController() {
		videos = new ArrayList<Videos>();
	}
	
	@GetMapping("")
	public String index(ModelMap model) {
		model.addAttribute("videos",videos);
		return "videos/index";
	}
}
